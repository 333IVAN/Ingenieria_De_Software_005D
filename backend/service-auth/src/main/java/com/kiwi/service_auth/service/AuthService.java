package com.kiwi.service_auth.service; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiwi.service_auth.dto.RegisterRequest;
import com.kiwi.service_auth.model.Usuario;
import com.kiwi.service_auth.repository.RolRepository;
import com.kiwi.service_auth.repository.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String rut, String contrasena) {
        Usuario usuario = usuarioRepository.findByRut(rut)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el RUT: " + rut));

        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        List<String> roles = usuario.getUsuarioRoles().stream()
                .map(usuarioRol -> usuarioRol.getRol().getDescripcion())
                .collect(Collectors.toList());

        return jwtService.generateToken(usuario.getRut(), roles);
    }

    public String register(RegisterRequest request) {
    if (usuarioRepository.findByRut(request.getRut()).isPresent()) {
        throw new RuntimeException("El RUT ya se encuentra registrado");
    }
    
    Usuario nuevoUsuario = new Usuario();
    nuevoUsuario.setRut(request.getRut());
    nuevoUsuario.setCorreo(request.getCorreo());
    
    String contraseñaEncriptada = passwordEncoder.encode(request.getContrasena());
    nuevoUsuario.setContrasena(contraseñaEncriptada);

    com.kiwi.service_auth.model.Rol rolPredeterminado = rolRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Error: El rol predeterminado no existe en la base de datos."));

    com.kiwi.service_auth.model.UsuarioRol usuarioRol = new com.kiwi.service_auth.model.UsuarioRol();
    usuarioRol.setUsuario(nuevoUsuario);
    usuarioRol.setRol(rolPredeterminado);

    if (nuevoUsuario.getUsuarioRoles() == null) {
        nuevoUsuario.setUsuarioRoles(new java.util.ArrayList<>());
    }
    nuevoUsuario.getUsuarioRoles().add(usuarioRol);
    usuarioRepository.save(nuevoUsuario);
    
    return "Usuario registrado exitosamente con contraseña encriptada y rol asignado";
}
}