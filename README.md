# Checklist do Projeto: API CRUD com Spring Boot e H2 🖥️📦

## Configuração Inicial

- [x] **Criar repositório no GitHub:**  
  Criar um repositório no GitHub para armazenar o código do projeto. 📁

- [x] **Configurar Trello:**  
  Criar um board no Trello para organizar as tarefas do projeto, dividindo em colunas como "To Do", "In Progress", e "Done". 📝

---

## Estruturação do Projeto

- [x] **Configuração do Projeto Spring Boot:**  
  Inicializar um novo projeto Spring Boot no Spring Initializr com as seguintes dependências:
  - Spring Web 🌐
  - Spring Data JPA 🗄️
  - H2 Database 🛢️

- [x] **Configuração do Banco de Dados H2:**  
  Configurar o banco H2 no arquivo `application.properties`: ⚙️
  ```properties
  spring.application.name=CrudUsuarioAplicativo
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.format_sql=true
  spring.h2.console.enabled=true
  spring.datasource.url=jdbc:h2:mem:banco

  server.error.include-message=always
  server.error.include-binding-errors=always

---

## Implementação dos Endpoints CRUD

- [x] **Implementar Endpoint para Criar Usuário (POST):**  
  Criar o método `POST /usuarios` que recebe os dados do usuário, valida, e salva no banco de dados. ➕👤

- [x] **Implementar Endpoint para Buscar Todos os Usuários (GET):**  
  Criar o método `GET /usuarios` que retorna todos os usuários armazenados no banco de dados. 🔍👥

- [x] **Implementar Endpoint para Buscar Usuário por ID (GET):**  
  Criar o método `GET /usuarios/{id}` que retorna um usuário específico pelo seu ID. 🆔👤

- [x] **Implementar Endpoint para Atualizar Usuário (PUT):**  
  Criar o método `PUT /usuarios/{id}` que atualiza as informações de um usuário existente. ♻️👤

- [x] **Implementar Endpoint para Atualizar Parcialmente Usuário (PATCH):**  
  Criar o método `PATCH /usuarios/{id}` que atualiza parcialmente os dados de um usuário. ✏️👤

- [x] **Implementar Endpoint para Deletar Usuário (DELETE):**  
  Criar o método `DELETE /usuarios/{id}` que exclui um usuário do banco de dados. ❌👤

---

## Criação do Modelo de Dados

- [x] **Definir a entidade `Usuario`:**  
  Criar a classe `Usuario` como uma entidade JPA, utilizando anotações como `@Entity`, `@Id`, e `@GeneratedValue`.

---

## Validações e Tratamento de Erros

- [x] **Validação de Entradas de Dados:**  
  Implementar validações básicas nos campos obrigatórios (ex.: nome não pode ser vazio) utilizando as anotações do Spring, como `@NotNull`, `@Size`, etc. ✔️📝

- [x] **Tratamento de Erros Globais:**  
  Configurar uma classe de tratamento de exceções (`@ControllerAdvice`) para retornar respostas apropriadas (ex.: `400 Bad Request`, `404 Not Found`) em caso de erros. ⚠️🚫

---

## Testes e Documentação

- [x] **Testar Endpoints com Postman/Insomnia:**  
  Testar todos os endpoints usando ferramentas como Postman ou Insomnia, validando que cada operação CRUD funciona corretamente. 🛠️🧪

- [x] **Documentar API no README:**  
  Documentar os detalhes da API (endpoints, formato dos dados, exemplos de requests/responses) no arquivo `README.md`. 📖📝

---

## Controle de Versão e Organização

- [x] **Commits Semânticos:**  
  Utilizar convenção de commits semânticos durante todo o desenvolvimento (ex.: `feat`, `fix`, `refactor`). 📝✅

- [x] **Seguir GitFlow (Opcional):**  
  Implementar o fluxo GitFlow para organizar branches e releases (ex.: criar branches `feature`, `develop`, `main`). 🌿

---

## Gestão do Projeto

- [x] **Definir Prazos no Trello:**  
  Atribuir prazos e responsáveis para cada tarefa dentro do board no Trello. ⏳

- [x] **Realizar Dailys:**  
  Participar de dailys para discutir progresso, bloqueios e próximos passos. ☕🗣️

---

## Entrega e Apresentação

- [x] **Publicar Projeto no GitHub:**  
  Fazer o push final do projeto no GitHub com todos os commits e documentações necessárias. 🚀

- [x] **Preparar Apresentação Final:**  
  Preparar uma apresentação para mostrar o progresso do projeto, dificuldades enfrentadas e lições aprendidas. 🎤📊
```
