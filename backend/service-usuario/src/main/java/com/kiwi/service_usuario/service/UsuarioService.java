package com.kiwi.service_usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.service_usuario.model.Usuario;
import com.kiwi.service_usuario.model.UsuarioRol;
import com.kiwi.service_usuario.repository.UsuarioRepository;
import com.kiwi.service_usuario.repository.UsuarioRolRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usRep;
    @Autowired
    private UsuarioRolRepository uRolRep;

    public List<Usuario> listarTodos(){
        return usRep.findAll();
    }

    @Transactional
    public Usuario crear(Usuario us) {
        Optional<Usuario> usuarioOpt = usRep.findByRut(us.getRut());
        
        if (usuarioOpt.isPresent()) {
            Usuario usuarioExistente = usuarioOpt.get();
            
            if (usuarioExistente.getContrasena() == null && us.getContrasena() != null && !us.getContrasena().trim().isEmpty()) {
                usuarioExistente.setContrasena(us.getContrasena());
                usuarioExistente.setSnombre(us.getSnombre());
                usuarioExistente.setApmaterno(us.getApmaterno());
                
                UsuarioRol ascensoRol = new UsuarioRol();
                ascensoRol.setUsuarioId(usuarioExistente.getId());
                ascensoRol.setRolId(3L); 
                uRolRep.save(ascensoRol);
            } else {
                usuarioExistente.setTelefono(us.getTelefono());
                usuarioExistente.setCorreo(us.getCorreo());
            }
            return usRep.save(usuarioExistente);
        }
        
        // 2. SI EL USUARIO ES 100% NUEVO (Tu lógica original intacta)
        Usuario usuarioGuardado = usRep.save(us);
        
        UsuarioRol nuevoUsuarioRol = new UsuarioRol();
        nuevoUsuarioRol.setUsuarioId(usuarioGuardado.getId());
        
        if (us.getContrasena() != null && !us.getContrasena().trim().isEmpty()) {
            nuevoUsuarioRol.setRolId(3L); // Rol 3: Registro formal
        } else {
            nuevoUsuarioRol.setRolId(4L); // Rol 4: Invitado
        }
        uRolRep.save(nuevoUsuarioRol);
        
        return usuarioGuardado;
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
