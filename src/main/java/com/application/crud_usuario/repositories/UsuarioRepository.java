package com.application.crud_usuario.repositories;

import com.application.crud_usuario.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
//    @Query(value = "SELECT * FROM USERS u WHERE u.status = 1",   nativeQuery = true)Collection<User>
//    findAllActiveUsersNative();
    Optional<Usuario> findByEmail(String email);
}
