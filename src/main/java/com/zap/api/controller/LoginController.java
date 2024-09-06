package com.zap.api.controller;

import com.zap.api.dtos.Login;
import com.zap.api.dtos.Sessao;
import com.zap.api.entity.Usuario;
import com.zap.api.repository.UsuarioRepository;
import com.zap.api.security.JWTCreator;
import com.zap.api.security.JWTObject;
import com.zap.api.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin()
@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login) {
        Usuario user = repository.findByUsername(login.getUsuario());
        if (user != null) {
            boolean passwordOk = encoder.matches(login.getSenha(), user.getSenha());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsuario());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsuario());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        } else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
