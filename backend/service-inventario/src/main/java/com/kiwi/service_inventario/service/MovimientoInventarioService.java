package com.kiwi.service_inventario.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kiwi.service_inventario.model.DonacionDTO;
import com.kiwi.service_inventario.model.InsumoDTO;
import com.kiwi.service_inventario.model.Inventario;
import com.kiwi.service_inventario.model.MovimientoInventario;
import com.kiwi.service_inventario.repository.InventarioRepository;
import com.kiwi.service_inventario.repository.MovimientoInventarioRepository;

@Service
public class MovimientoInventarioService {
    @Autowired
    private MovimientoInventarioRepository moRep;
    @Autowired
    private InventarioRepository inRep;
    @Autowired
    private WebClient web;

    public List<MovimientoInventario> listarTodos(){
        return moRep.findAll();
    }

    public MovimientoInventario registrarDonacion(Long donacionId) {
        DonacionDTO donacion = web.get()
            .uri("/{id}", donacionId)
            .retrieve()
            .bodyToMono(DonacionDTO.class)
            .block();

        if (donacion == null || donacion.getInsumoId() == null) {
            throw new RuntimeException("La donacion no tiene insumo asociado");
        }

        Inventario inventario = inRep.findByInsumoId(donacion.getInsumoId())
            .orElseGet(() -> crearInventarioDesdeDonacion(donacion));

        inventario.setStock(inventario.getStock() + donacion.getCantidad());
        Inventario inventarioGuardado = inRep.save(inventario);

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setTipo("ENTRADA");
        movimiento.setCantidad(donacion.getCantidad());
        movimiento.setFechaMovimiento(LocalDate.now());
        movimiento.setDescripcion(donacion.getDescripcion());
        movimiento.setInventarioId(inventarioGuardado.getId());
        movimiento.setInventario(inventarioGuardado);
        movimiento.setDonacionId(donacion.getId());
        movimiento.setDonacion(donacion);

        return moRep.save(movimiento);
    }

    public MovimientoInventario crear(MovimientoInventario mov) {
        Inventario inventario = inRep.findById(mov.getInventarioId())
            .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        if ("SALIDA".equalsIgnoreCase(mov.getTipo())) {
            if (inventario.getStock() < mov.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para registrar salida");
            }
            inventario.setStock(inventario.getStock() - mov.getCantidad());
        } else {
            inventario.setStock(inventario.getStock() + mov.getCantidad());
            mov.setTipo("ENTRADA");
        }

        if (mov.getFechaMovimiento() == null) {
            mov.setFechaMovimiento(LocalDate.now());
        }

        Inventario inventarioGuardado = inRep.save(inventario);
        mov.setInventario(inventarioGuardado);
        return moRep.save(mov);
    }

    public Optional<MovimientoInventario> buscarPorId(Long id){
        return moRep.findById(id);
    }

    public void eliminar(Long id) {
        moRep.deleteById(id);
    }

    public List<MovimientoInventario> listarPorInventario(Long inventarioId){
        return moRep.listarPorInventario(inventarioId);
    }

    public List<MovimientoInventario> listarPorTipo(String tipo){
        return moRep.listarPorTipo(tipo);
    }

    public List<MovimientoInventario> listarPorDonacion(Long donacionId){
        return moRep.listarPorDonacion(donacionId);
    }

    private Inventario crearInventarioDesdeDonacion(DonacionDTO donacion) {
        Inventario inventario = new Inventario();
        inventario.setInsumoId(donacion.getInsumoId());
        inventario.setDescripcion(obtenerDescripcionInsumo(donacion));
        inventario.setUnidad(obtenerUnidadInsumo(donacion));
        inventario.setStock(0L);
        return inventario;
    }

    private String obtenerDescripcionInsumo(DonacionDTO donacion) {
        InsumoDTO insumo = donacion.getInsumo();
        if (insumo != null && insumo.getDescripcion() != null && !insumo.getDescripcion().isBlank()) {
            return insumo.getDescripcion();
        }
        return "Insumo " + donacion.getInsumoId();
    }

    private String obtenerUnidadInsumo(DonacionDTO donacion) {
        InsumoDTO insumo = donacion.getInsumo();
        if (insumo != null) {
            return insumo.getUnidad();
        }
        return null;
    }
}
