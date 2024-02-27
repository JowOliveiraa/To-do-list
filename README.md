# Resumo da Aplicação

Um sistema de gerenciamento de tarefas.
- Os usuários podem se registrar e fazer login no sistema.
- Ele permite que os usuários criem, atualizem e excluam tarefas, comentários e status.

## Entidades

A aplicação consiste nas seguintes entidades:

- **User**: Representa um usuário no sistema. Cada usuário tem um ID, nome, senha, email, status de atividade, role e data de registro.
  
- **Task**: Representa uma tarefa no sistema. Cada tarefa tem um ID, nome, descrição, data de registro e um status associado.

- **Comment**: Representa um comentário em uma tarefa. Cada comentário tem um ID, texto do comentário, data de registro, e está associado a um usuário e uma tarefa.

- **Status**: Representa um status que pode ser associado a varias tarefas. Cada status tem um ID, nome e cor.

- **Tag**: Representa uma tag que pode ser associada a varias tarefas. Cada tag tem um ID, um nome e uma cor.

## Serviços

A aplicação fornece os seguintes serviços:

- **UserService**: Gerencia as operações relacionadas aos usuários, como registro, login, obter informações por ID, listar pesquisar por nome ou email, atualização de informações e alteração do status de atividade.

- **TaskService**: Gerencia as operações relacionadas às tarefas, como criação, buscar a tarefa por ID, listar todas as tarefas, atualização e exclusão por ID.

- **CommentService**: Gerencia as operações relacionadas aos comentários, como registro de comentário.

- **StatusService**: Gerencia as operações relacionadas aos status, como criação, definição de status para uma tarefa, busca de status e atualização de status.

- **TagService**: Gerencia as operações relacionadas às tags, como criar uma nova tag, associar uma tag a uma tarefa, listar todas as tags, atualizar uma tag, remover uma tag de uma tarefa e deletar uma tag.

## Dependências

Dependencias usadas no projeto:

- `spring-boot-starter-data-jpa`  Cria a camada de persistência com o banco de dados e realiza todas as transações.

- `spring-boot-starter-security`  Cria a camada de autenticação e autorização da aplicação.
  
- `spring-boot-starter-web`  Cria um TomCat imbutido na aplicação para subir o servidor.
  
- `java-jwt`  Cria, gerencia e verifica os tokens da aplicação.
  
- `flyway-core`  Cria, gerencia e aplica alterações no esquema do banco de dados.
  
- `flyway-mysql`  Fornece suporte específico para o MySQL, permitindo que o Flyway interaja com bancos de dados MySQL.
  
- `spring-boot-starter-validation`  Permite adicionar validações automaticas nos objetos da aplicação.


