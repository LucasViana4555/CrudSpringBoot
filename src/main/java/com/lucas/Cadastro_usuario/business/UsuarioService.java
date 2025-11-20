package com.lucas.Cadastro_usuario.business;


import com.lucas.Cadastro_usuario.infrastructure.entitys.Usuario;
import com.lucas.Cadastro_usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;


    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    // metodo p salva usuario
    public void salvarUsuario(Usuario usuario) {
        repository.saveAndFlush(usuario);
    }
    // saveAndFlush: Salva a entidade no banco e força a sincronização imediata (flush)
    // Diferente do .save(), que pode atrasar a escrita até o fim da transação.

   // metodo p buscar por email
    public Usuario buscarUsuarioPorEmail(String email) {
        // O metodo findByEmail retorna um 'Optional' (pode ter algo ou ser vazio)
        // .orElseThrow(): Se encontrar o usuário, retorna ele.
        // Se NÃO encontrar (vazio), lança a exceção RuntimeException parando o código.
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException ("Email não encontrado")
        );

    }

    // metodo p deletar
    // Primeiro, reutiliza o metodo acima para buscar o usuário.
    // Se o email não existir, o metodo 'buscarUsuarioPorEmail' já vai estourar o erro,
    // garantindo que só tentamos deletar algo que realmente existe.
    public void deletarUsuarioPorEmail(String email) {
        repository.delete(buscarUsuarioPorEmail(email));
    }

    //metodo p atualizar
    public void atualizarUsuarioPorId(Integer id, Usuario usuario) {
        // Busca o usuário antigo no banco pelo ID. Se não achar, lança erro.
        Usuario usuarioEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        //Cria um NOVO objeto (usuarioAtualizado) mesclando dados novos e antigos usando o Builder.
        Usuario usuarioAtualizado = Usuario.builder()
                // Lógica Ternária: (Condição) ? (Valor se verdadeiro) : (Valor se falso)
                // Se o novo email veio preenchido, usa ele. Se veio nulo, mantém o email antigo (do banco).
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                // Mesma lógica para o nome: Se veio novo, atualiza. Senão, mantém o antigo.
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                // O ID nunca muda, então pegamos o do usuário original do banco.
                .id(usuarioEntity.getId())
                .build(); // Finaliza a construção do objeto

        // 3. Salva o objeto atualizado no banco (o Hibernate entende que é atualização porque o ID já existe).
        repository.saveAndFlush(usuarioAtualizado);
    }
}
