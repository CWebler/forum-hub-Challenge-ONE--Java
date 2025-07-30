# 🧠 Fórum Hub API

Projeto desenvolvido durante o curso de Spring Boot, com foco em boas práticas de desenvolvimento, segurança com autenticação JWT, e testes via Insomnia.

---

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot 3
- Spring Security
- JWT (Auth0 - java-jwt)
- Spring Data JPA
- Hibernate
- MySQL (ou H2 para testes)
- Maven
- Insomnia (para testes manuais da API)

---

## 📚 Funcionalidades implementadas

### 🔒 Autenticação e Segurança

- Autenticação com **JWT** (JSON Web Token)
- Endpoint `/login` para geração do token
- Proteção de todos os endpoints com autenticação obrigatória
- Filtro de segurança customizado para validar o token em cada requisição
- Senhas criptografadas com **BCrypt**

### 💬 Gerenciamento de Tópicos

- `GET /topicos`: Lista todos os tópicos (autenticado)
- `GET /topicos/{id}`: Detalha um tópico específico por ID
- `POST /topicos`: Cria um novo tópico (autenticado)
- `PUT /topicos`: Atualiza título e mensagem de um tópico (autenticado e dono do tópico)
- `DELETE /topicos/{id}`: Deleta um tópico (apenas se for o autor)

---

## 🛠️ Como rodar o projeto

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/forum-hub.git
