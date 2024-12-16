# DESAFIO ORACLE ONE FORUMHUB

# 📄 API Documentation - Tópicos, Cursos e Autenticação

## 📝 **Descrição**

Esta é uma API RESTful desenvolvida para gerenciamento de **tópicos**, **cursos** e **usuários**, incluindo recursos de autenticação. A API foi construída seguindo as melhores práticas de desenvolvimento e exposta através do padrão OpenAPI.

---

## 🚀 **Tecnologias Utilizadas**

- **Java** com **Spring Boot**
- **Swagger UI** para documentação dos endpoints
- Banco de Dados Relacional (ex.: MySQL ou PostgreSQL)
- Autenticação JWT (ou outra estratégia, se aplicável)

---

## 📚 **Endpoints**

### **1. Tópico Controller** `/topicos`
| Método   | Endpoint                               | Descrição                         |
|----------|----------------------------------------|-----------------------------------|
| `GET`    | `/topicos/{id}`                        | Busca um tópico pelo ID           |
| `GET`    | `/topicos`                             | Lista todos os tópicos            |
| `GET`    | `/topicos/{id}/resposta`               | Busca todas as resposta do tópico |
| `POST`   | `/topicos`                             | Cria um novo tópico               |
| `POST`   | `/topicos/{id}/resposta`               | Cria uma resposta em um tópico    |
| `PUT`    | `/topicos/{id}`                        | Atualiza um tópico                |
| `PUT`    | `/topicos/{id}/resposta/{idResposta}`  | Atualiza uma resposta de tópico   |
| `DELETE` | `/topicos/{id}/resposta/{idResposta}`  | Remove uma resposta de tópico     |
| `DELETE` | `/topicos/{id}`                        | Remove um tópico                  |

---

### **2. Curso Controller** `/cursos`
| Método   | Endpoint       | Descrição                 |
|----------|----------------|---------------------------|
| `GET`    | `/cursos/{id}` | Busca um curso pelo ID    |
| `POST`   | `/cursos`      | Cria um novo curso        |
| `PUT`    | `/cursos/{id}` | Atualiza um curso pelo ID |
| `DELETE` | `/cursos/{id}` | Remove um curso pelo ID   |

---

### **3. Usuário Controller** `/usuarios`
| Método   | Endpoint     | Descrição                  |
|----------|--------------|----------------------------|
| `POST`   | `/registrar` | Registra um novo usuário   |

---

### **4. Autenticação Controller** `/auth`
| Método   | Endpoint   | Descrição                     |
|----------|------------|-------------------------------|
| `POST`   | `/login`   | Autentica e gera token JWT    |

---

## ⚙️ **Como Executar o Projeto**

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/nome-do-projeto.git
   cd nome-do-projeto
2. **Configure o banco de dados**:
Crie o banco de dados (ex.: MySQL/PostgreSQL).
Configure as credenciais no application.properties:
    spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
    spring.datasource.username=usuario
    spring.datasource.password=senha
3. **Execute a aplicação**:
mvn spring-boot:run
4. **Acesse a documentação Swagger**:
URL: http://localhost:8080/swagger-ui/