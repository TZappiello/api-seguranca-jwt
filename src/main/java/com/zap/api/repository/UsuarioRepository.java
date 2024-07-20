package com.zap.api.repository;

import com.zap.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("FROM Usuario usuario JOIN FETCH usuario.papeis WHERE usuario.usuario= (:usuario)")
    Usuario findByUsername(@Param("usuario") String usuario);

    boolean existsByUsuario(String usuario);
}

