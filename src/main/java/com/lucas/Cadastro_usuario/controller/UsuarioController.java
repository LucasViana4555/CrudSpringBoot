package com.lucas.Cadastro_usuario.controller;

import com.lucas.Cadastro_usuario.business.UsuarioService;
import com.lucas.Cadastro_usuario.infrastructure.entitys.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    //metodo crud CRIAR (post)
    @PostMapping // Mapeia requisições HTTP POST para este metodo (usado para salvar dados)
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario) {
        usuarioService.salvarUsuario(usuario); // Chama a lógica de negócio para salvar
        return ResponseEntity.ok().build(); // Retorna HTTP 200 (OK) sem corpo de resposta
    }
    // ^ @RequestBody: Pega o JSON enviado no corpo da requisição e converte para o objeto Usuario


    //metodo crud LER (get)
    @GetMapping // Mapeia requisições HTTP GET para este método (usado para buscar dados)
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam("email") String email) {
        // Retorna HTTP 200 (OK) e o objeto Usuario encontrado no corpo da resposta
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }
    // ^ @RequestParam: Pega o valor passado na URL (ex: /usuario?email=teste@teste.com)


    //metodo crud DELETAR (delete)
    @DeleteMapping // Mapeia requisições HTTP DELETE
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam("email") String email) {
        usuarioService.deletarUsuarioPorEmail(email); // Chama o serviço para remover o usuário
        return ResponseEntity.ok().build(); // Retorna HTTP 200 (OK) confirmando a exclusão
    }

    //metodo crud ATUALIZAR (put)
    @PutMapping // Mapeia requisições HTTP PUT (usado para atualização completa)
    public ResponseEntity<Void> atualizarUsuarioPorId(@RequestParam Integer id ,@RequestBody Usuario usuario) {
        // Recebe o ID via URL param e os novos dados do usuário via JSON no corpo (Body)
        usuarioService.atualizarUsuarioPorId(id, usuario);
        return ResponseEntity.ok().build(); // Retorna HTTP 200 (OK) confirmando a atualização
    }

}
