package com.zap.api.service;

import com.zap.api.entity.Usuario;
import com.zap.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public void createUser(Usuario usuario){

        String pass = usuario.getSenha();
        //criptografando antes de salvar no banco

        usuario.setSenha(encoder.encode(pass));

        repository.save(usuario);
    }
}

