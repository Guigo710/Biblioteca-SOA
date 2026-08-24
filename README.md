📚 Biblioteca SOA — Spring Boot

Projeto desenvolvido para demonstrar na prática os conceitos de SOA (Service-Oriented Architecture) utilizando Java, Spring Boot, Spring Data JPA, REST API e H2 Database.

A aplicação representa um sistema de biblioteca dividido em serviços independentes, onde cada serviço possui uma responsabilidade de negócio específica e se comunica com os demais através de APIs HTTP.

🏗️ Arquitetura Geral

A aplicação é dividida em três serviços principais:

                         ┌─────────────────┐
                         │     CLIENTE     │
                         └────────┬────────┘
                                  │
                             HTTP / REST
                                  │
                ┌─────────────────┴─────────────────┐
                │                                   │
                ▼                                   ▼
       ┌─────────────────┐                ┌─────────────────┐
       │ SERVICE LIVROS  │                │ SERVICE USUÁRIOS│
       │     :8081       │                │     :8082       │
       └────────┬────────┘                └────────┬────────┘
                │                                  │
                ▼                                  ▼
             H2 DB                              H2 DB
                ▲                                  ▲
                │                                  │
                └──────────────┬───────────────────┘
                               │
                               │ HTTP / REST
                               │
                    ┌──────────┴──────────┐
                    │ SERVICE EMPRÉSTIMOS │
                    │       :8083         │
                    └──────────┬──────────┘
                               │
                               ▼
                            H2 DB
## Serviços
   Serviço          |	 Porta |	Responsabilidade           |
--------            |	 ----- |	----------------           |
service-livros      |	8081	 | Gerenciamento de livros     |
service-usuarios    |	8082	 | Gerenciamento de usuários   |
service-emprestimos | 8083	 | Gerenciamento de empréstimos|

Cada serviço é uma aplicação Spring Boot independente, com seu próprio pom.xml, banco e regras de negócio.

🔎 O que é SOA?

SOA (Service-Oriented Architecture) é um estilo arquitetural que organiza uma aplicação em serviços independentes, cada um responsável por uma determinada capacidade de negócio.

Neste projeto:

                    BIBLIOTECA
                         │
          ┌──────────────┼──────────────┐
          │              │              │
          ▼              ▼              ▼
       LIVROS         USUÁRIOS      EMPRÉSTIMOS

Cada serviço possui sua própria responsabilidade.

Por exemplo:

## 📚 Serviço de Livros

Responsável por:

- cadastrar livros;
- consultar livros;
- listar livros;
- controlar disponibilidade.
## 👤 Serviço de Usuários

Responsável por:

- cadastrar usuários;
- consultar usuários;
- listar usuários.
## 📖 Serviço de Empréstimos

Responsável por:

- criar empréstimos;
- consultar empréstimos;
- validar se o livro existe;
- validar se o usuário existe.
## 🔗 Comunicação entre os serviços

Os serviços não acessam diretamente o banco de dados uns dos outros.

Por exemplo, quando o serviço de empréstimos precisa verificar um livro:

service-emprestimos

        │
        |  
        │   GET /livros/1
        ▼
service-livros

        │
        ▼
      H2 DB

Para verificar um usuário:

service-emprestimos

        │
        │  GET /usuarios/1
        ▼
service-usuarios

        │
        ▼
      H2 DB

Essa comunicação é realizada através de HTTP/REST.

Isso mantém os serviços desacoplados e permite que cada um gerencie seus próprios dados.

## 🧩 Arquitetura interna dos serviços

Cada serviço segue uma estrutura em camadas:

Controller
     │
     ▼
  Service
     │
     ▼
 Repository
     │
     ▼
  Database
Controller

Responsável por receber as requisições HTTP.

Exemplo:

- POST /livros
- GET  /livros
- GET  /livros/{id}
  
  ## Service

Contém as regras de negócio.

Exemplo:

public Livro cadastrar(Livro livro) {
    livro.setDisponivel(true);
    return repository.save(livro);
}
## Repository

Responsável pelo acesso aos dados utilizando Spring Data JPA.

public interface LivroRepository
        extends JpaRepository<Livro, Long> {
}
## Model

Representa as entidades persistidas no banco.

@Entity
@Table(name = "livros")
public class Livro {
    // atributos
}
## 📁 Estrutura do projeto
biblioteca-soa/
│
├── service-livros/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/biblioteca/livros/
│   │       │       ├── controller/
│   │       │       ├── service/
│   │       │       ├── repository/
│   │       │       └── model/
│   │       │
│   │       └── resources/
│   │           └── application.properties
│   │
│   └── pom.xml
│
├── service-usuarios/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/biblioteca/usuarios/
│   │       │       ├── controller/
│   │       │       ├── service/
│   │       │       ├── repository/
│   │       │       └── model/
│   │       │
│   │       └── resources/
│   │           └── application.properties
│   │
│   └── pom.xml
│
└── service-emprestimos/
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── com/biblioteca/emprestimos/
    │       │       ├── controller/
    │       │       ├── service/
    │       │       ├── repository/
    │       │       ├── model/
    │       │       └── client/
    │       │           ├── LivroClient.java
    │       │           └── UsuarioClient.java
    │       │
    │       └── resources/
    │           └── application.properties
    │
    └── pom.xml
##🛠️ Tecnologias utilizadas
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- REST API
- HTTP
- Git/GitHub
##▶️ Como executar

Como cada serviço é uma aplicação Spring Boot independente, cada um deve ser executado separadamente.

1. Clone o projeto
git clone <URL_DO_REPOSITORIO>
cd biblioteca-soa
2. Execute o serviço de livros

Entre na pasta:

cd service-livros

No Windows:

.\mvnw.cmd spring-boot:run

O serviço estará disponível em:

http://localhost:8081
3. Execute o serviço de usuários

Abra outro terminal:

cd service-usuarios

Execute:

.\mvnw.cmd spring-boot:run

Disponível em:

http://localhost:8082
4. Execute o serviço de empréstimos

Abra um terceiro terminal:

cd service-emprestimos

Execute:

.\mvnw.cmd spring-boot:run

Disponível em:

http://localhost:8083

Ao final, os três serviços estarão funcionando simultaneamente:

service-livros       → localhost:8081
service-usuarios     → localhost:8082
service-emprestimos  → localhost:8083
🧪 Exemplos de API
📚 Livros
Criar livro
POST http://localhost:8081/livros
{
    "titulo": "Clean Code",
    "autor": "Robert C. Martin"
}
Listar livros
GET http://localhost:8081/livros
Buscar livro
GET http://localhost:8081/livros/1
👤 Usuários
Criar usuário
POST http://localhost:8082/usuarios
{
    "nome": "Guilherme",
    "email": "guilherme@email.com"
}
Listar usuários
GET http://localhost:8082/usuarios
Buscar usuário
GET http://localhost:8082/usuarios/1
📖 Empréstimos
Criar empréstimo
POST http://localhost:8083/emprestimos
{
    "livroId": 1,
    "usuarioId": 1
}

Durante a criação do empréstimo, o serviço verifica:

                 POST /emprestimos
                         │
                         ▼
                Service Empréstimos
                         │
             ┌───────────┴───────────┐
             │                       │
             ▼                       ▼
       LivroClient              UsuarioClient
             │                       │
             ▼                       ▼
       :8081/livros/1          :8082/usuarios/1
             │                       │
             └───────────┬───────────┘
                         │
                         ▼
                  Criar empréstimo
🗄️ Banco de dados

Cada serviço possui seu próprio banco H2.

service-livros
      │
      └── H2 → livros

service-usuarios
      │
      └── H2 → usuarios

service-emprestimos
      │
      └── H2 → emprestimos

Essa separação evita que um serviço dependa diretamente da estrutura interna do banco de outro serviço.

A comunicação acontece através das APIs.

🔄 Fluxo de um empréstimo

Um exemplo completo:

1. Cliente solicita empréstimo
              │
              ▼
2. service-emprestimos
              │
              ├──────► Verifica livro
              │             │
              │             ▼
              │       service-livros
              │
              ├──────► Verifica usuário
              │             │
              │             ▼
              │       service-usuarios
              │
              ▼
3. Validações concluídas
              │
              ▼
4. Salva empréstimo
              │
              ▼
5. H2 do service-emprestimos
🎯 Objetivos do projeto

Este projeto foi desenvolvido com o objetivo de praticar:

Arquitetura Orientada a Serviços (SOA);
desenvolvimento de APIs REST;
comunicação entre aplicações Spring Boot;
separação de responsabilidades;
arquitetura em camadas;
Spring Data JPA;
persistência de dados;
comunicação HTTP entre serviços;
conceitos de desacoplamento;
organização de aplicações distribuídas.
📚 Conceitos demonstrados
SOA

Serviços independentes organizados por responsabilidade de negócio.

Baixo acoplamento

Os serviços não dependem diretamente da implementação interna uns dos outros.

API REST

Os serviços disponibilizam funcionalidades através de endpoints HTTP.

Separação de responsabilidades

Cada serviço possui uma função específica dentro do sistema.

Banco por serviço

Cada serviço mantém seus próprios dados e não acessa diretamente o banco dos demais.
