package com.libras.backend.backend.repository;

import com.libras.backend.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aqui você pode definir métodos de consulta adicionais, se quiser
    // exemplo: Optional<Usuario> findByEmail(String email);
}
