# API CRUD Simples com Spring Boot e H2

## Objetivo

Este projeto tem como objetivo desenvolver uma API simples utilizando **Spring Boot** que implemente operações CRUD (Create, Read, Update, Delete) para gerenciar um recurso como **Usuários** ou **Produtos**. O banco de dados utilizado será o **H2**, um banco em memória. A API expõe endpoints RESTful e inclui validação básica dos dados de entrada, além de tratamento de erros apropriado.

---

## Requisitos

1. **Endpoints RESTful** para realizar as operações CRUD no recurso escolhido (exemplo: Usuários ou Produtos).
2. **Banco de dados em memória H2**: utilizado para armazenar os dados de forma volátil durante a execução da aplicação.
3. **Validação de dados**: entradas para a API serão validadas para garantir consistência.
4. **Tratamento de erros**: a API deve tratar erros de forma clara, retornando mensagens e códigos de status adequados.
5. **Versionamento e Controle de Versão**:
   - O código será disponibilizado no **GitHub**.
   - Utilizar **commits semânticos** (ex.: `feat`, `fix`, `refactor`).
   - Se possível, seguir o fluxo de trabalho **GitFlow** para gerenciar branches e releases.

---

## Tecnologias Utilizadas

- **Java 17** (ou superior)
- **Spring Boot** 3.x
- **Spring Data JPA**
- **H2 Database**
- **Maven** (para gerenciamento de dependências)
- **Git/GitHub** (para controle de versão)
- **Trello** (para gestão do projeto)

---

## Instalação e Configuração

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/LukaTamazato/CRUD-usuario.git
   cd CRUD-usuario
