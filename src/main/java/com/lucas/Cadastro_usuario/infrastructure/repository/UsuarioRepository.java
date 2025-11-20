package com.lucas.Cadastro_usuario.infrastructure.repository;

import com.lucas.Cadastro_usuario.infrastructure.entitys.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Query Method (Metodo de Consulta Derivada)
    // O Spring analisa o nome do metodo "findByEmail" e gera o SQL automaticamente:
    // "SELECT * FROM usuario WHERE email = ?"
    // Retorna um Optional para evitar NullPointerException se o email não existir.
    Optional<Usuario> findByEmail(String email);

    // @Transactional
    // Obrigatória para operações de escrita/modificação customizadas (como delete ou update).
    // Garante que, se houver erro durante a exclusão, a transação é revertida (rollback)
    // e nada é apagado pela metade.
    @Transactional
    // Delete Method (metodo de Exclusão Derivado)
    // O Spring gera o SQL: "DELETE FROM usuario WHERE email = ?"
    void deleteByEmail(String email);


}
