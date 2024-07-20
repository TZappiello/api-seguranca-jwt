package com.zap.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tab_usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(length = 50, nullable = false)
    private String nome;
    @Column(length = 20, nullable = false)
    private String usuario;
    @Column(length = 100, nullable = false)
    private String senha;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tab_usuario_papel",
            joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "papel_id")
    private List<String> papeis = new ArrayList<>();
}
