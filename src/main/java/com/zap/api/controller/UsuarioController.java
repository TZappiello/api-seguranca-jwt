package com.zap.api.controller;

import com.zap.api.entity.Usuario;
import com.zap.api.repository.UsuarioRepository;
import com.zap.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public void postUser(@RequestBody Usuario usuario) {
        service.createUser(usuario);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public Optional<Usuario> usuario(@PathVariable Long id){

        return Optional.ofNullable(usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("")));
    }
}