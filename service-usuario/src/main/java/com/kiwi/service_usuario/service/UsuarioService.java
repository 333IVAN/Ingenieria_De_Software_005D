package com.kiwi.service_usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.service_usuario.model.Usuario;
import com.kiwi.service_usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usRep;

    public List<Usuario> listarTodos(){
        return usRep.findAll();
    }

    public Usuario crear(Usuario us){
        if(usRep.existsByRut(us.getRut())){
            throw new RuntimeException("El usuario ya existe");
        }
        return usRep.save(us);
    }

    public Optional<Usuario> buscarPorId(Long id){
    return usRep.findById(id);
    }

    public Optional<Usuario> buscarPorRut(String rut){
        return usRep.findByRut(rut);
    }

    public Optional<Usuario> actualizar(Long id, Usuario datosActualizados) {
        Optional<Usuario> usuarioOpcional = usRep.findById(id);

        if (usuarioOpcional.isPresent()) {
            Usuario usuarioExistente = usuarioOpcional.get();

            if (!usuarioExistente.getRut().equals(datosActualizados.getRut()) && 
                usRep.existsByRut(datosActualizados.getRut())) {
                throw new RuntimeException("El RUT ingresado ya está en uso por otro usuario");
            }

            usuarioExistente.setRut(datosActualizados.getRut());
            usuarioExistente.setDv(datosActualizados.getDv());
            usuarioExistente.setPnombre(datosActualizados.getPnombre());
            usuarioExistente.setSnombre(datosActualizados.getSnombre());
            usuarioExistente.setAppaterno(datosActualizados.getAppaterno());
            usuarioExistente.setApmaterno(datosActualizados.getApmaterno());
            usuarioExistente.setTelefono(datosActualizados.getTelefono());
            usuarioExistente.setCorreo(datosActualizados.getCorreo());
            usuarioExistente.setDireccionId(datosActualizados.getDireccionId());
            
            if (datosActualizados.getContrasena() != null && !datosActualizados.getContrasena().isBlank()) {
                usuarioExistente.setContrasena(datosActualizados.getContrasena());
            }

            Usuario usuarioGuardado = usRep.save(usuarioExistente);

            return Optional.of(usuarioGuardado);
        } else {
            return Optional.empty();
    }
    }

    public void eliminar(Long id) {
        usRep.deleteById(id);
    }

}
