📚 Projeto de Estudos: CRUD de Usuários com Spring Boot

Este é um projeto simples de CRUD (Create, Read, Update, Delete) desenvolvido para fins didáticos. O objetivo principal é praticar os conceitos fundamentais do framework Spring Boot, arquitetura em camadas, injeção de dependência e manipulação de banco de dados com Spring Data JPA.

🚀 Tecnologias Utilizadas

Java 24: Linguagem de programação.

Spring Boot: Framework para agilizar o desenvolvimento web.

Spring Data JPA: Para persistência de dados e abstração de SQL.

Lombok: Para redução de código repetitivo (Getters, Setters, Construtores).

H2 Database / MySQL: (Dependendo da sua configuração) Banco de dados relacional.

🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas clássica, promovendo a separação de responsabilidades (Separation of Concerns). Abaixo, explicamos detalhadamente cada parte do código:

1. Entidade (Usuario.java)

Local: infrastructure/entitys/Usuario.java

Representa a tabela no banco de dados. É o "molde" dos dados.

@Entity & @Table: Informam ao JPA que essa classe deve se tornar uma tabela chamada "usuario".

@Id & @GeneratedValue: Gerenciam a chave primária (ID) automaticamente.

Lombok (@Getter, @Setter, @Builder): Cria automaticamente métodos de acesso e padrões de construção de objetos, limpando o código visualmente.

2. Repositório (UsuarioRepository.java)

Local: infrastructure/repository/UsuarioRepository.java

É a camada de acesso aos dados (Data Access Layer).

extends JpaRepository: Herda métodos prontos como save(), delete(), findAll(). Não precisamos escrever SQL puro.

Query Methods: O método findByEmail(String email) é um exemplo onde o Spring monta o SQL automaticamente baseado apenas no nome do método.

@Transactional: Usado no método de delete customizado para garantir integridade da transação no banco.

3. Serviço (UsuarioService.java)

Local: business/UsuarioService.java

É o coração da aplicação. Contém as Regras de Negócio.

Validações: Verifica se um email existe antes de buscar ou deletar (orElseThrow).

Lógica de Atualização: No método atualizarUsuarioPorId, contém a lógica para não apagar dados existentes caso o campo venha nulo na requisição (uso de operadores ternários).

Isolamento: O Service não sabe "como" os dados chegam (HTTP), ele apenas sabe o que fazer com eles.

4. Controlador (UsuarioController.java)

Local: controller/UsuarioController.java

É a porta de entrada da API REST. O "Garçom" que recebe os pedidos.

@RestController: Define que a classe responde requisições HTTP com dados (JSON).

Endpoints:

POST /usuario: Cria um usuário.

GET /usuario: Busca por email.

DELETE /usuario: Remove por email.

PUT /usuario: Atualiza dados pelo ID.

ResponseEntity: Controla a resposta HTTP completa (Status Code, Corpo, Headers).

🔌 Como testar a API (Endpoints)

Se você usar o Postman ou Insomnia, aqui estão exemplos de requisições:

Criar Usuário (POST)

URL: http://localhost:8080/usuario
Body (JSON):

{
    "nome": "Lucas",
    "email": "lucas@email.com"
}


Buscar Usuário (GET)

URL: http://localhost:8080/usuario?email=lucas@email.com

Atualizar Usuário (PUT)

URL: http://localhost:8080/usuario?id=1
Body (JSON):

{
    "nome": "Lucas Atualizado"
}


(Note que se não enviar o email, a lógica do Service mantém o antigo).

Deletar Usuário (DELETE)

URL: http://localhost:8080/usuario?email=lucas@email.com

📝 Conclusão

Este projeto demonstra o fluxo completo de uma aplicação Spring Boot:

O cliente chama o Controller.

O Controller passa para o Service.

O Service aplica regras e chama o Repository.

O Repository acessa a Entity no Banco de Dados.

Sinta-se à vontade para clonar, modificar e expandir este projeto para seus estudos!
