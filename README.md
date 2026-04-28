# Connect Tickets — Arena Pernambuco

Sistema web que conecta a população à Arena Pernambuco, centralizando divulgação de eventos, participação do público, sugestões e agendamento de visitas.

**API deployada:** https://tickets-poo.onrender.com  
**Documentação interativa:** https://tickets-poo.onrender.com/docs

---

## Stack

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 3.4.4 |
| Segurança | Spring Security + JWT (HMAC256, 2h) |
| Persistência | Spring Data JPA + Hibernate |
| Banco de Dados | Supabase (PostgreSQL) |
| Documentação | SpringDoc OpenAPI + Scalar UI |
| Build | Maven |

---

## Como Executar

```bash
git clone https://github.com/brunosm26/Connect_Tickets-Projeto_POO.git
cd Connect_Tickets-Projeto_POO

# Mac/Linux
chmod +x mvnw
./mvnw spring-boot:run
```

> Por padrão sobe na porta `8080`. Em produção a porta é definida pela variável `PORT`.

---

## Endpoints

Após o login, envie o token em todas as requisições autenticadas:
```
Authorization: Bearer {token}
```

### Autenticação

| Método | Rota | Acesso |
|---|---|---|
| POST | `/api/auth/register` | Público |
| POST | `/api/auth/login` | Público |
| POST | `/api/auth/register-admin` | ADMIN |

### Eventos

| Método | Rota | Acesso |
|---|---|---|
| GET | `/api/eventos` | Público |
| GET | `/api/eventos/{id}` | Público |
| GET | `/api/eventos?categoria={categoria}` | Público |
| POST | `/api/eventos` | ADMIN |
| PUT | `/api/eventos/{id}` | ADMIN |
| DELETE | `/api/eventos/{id}` | ADMIN |

> Categorias: `ESPORTE`, `SHOW`, `CULTURAL`, `CORPORATIVO`

### Usuários

| Método | Rota | Acesso |
|---|---|---|
| GET | `/api/usuarios` | ADMIN |
| GET | `/api/usuarios/{id}` | ADMIN |
| POST | `/api/usuarios` | ADMIN |
| PUT | `/api/usuarios/{id}` | ADMIN / próprio usuário |

### Inscrições

| Método | Rota | Acesso |
|---|---|---|
| POST | `/api/inscricoes` | Autenticado |
| GET | `/api/inscricoes` | ADMIN |
| GET | `/api/inscricoes/me` | Autenticado |
| DELETE | `/api/inscricoes/{id}` | Autenticado |

### Agendamentos

| Método | Rota | Acesso |
|---|---|---|
| POST | `/api/agendamentos` | Autenticado |
| GET | `/api/agendamentos` | ADMIN |
| GET | `/api/agendamentos/{id}` | Autenticado |
| DELETE | `/api/agendamentos/{id}` | Autenticado |

### Visitas

| Método | Rota | Acesso |
|---|---|---|
| POST | `/api/visitas` | Autenticado |
| GET | `/api/visitas` | Autenticado |
| GET | `/api/visitas/{id}` | Autenticado |
| DELETE | `/api/visitas/{id}` | Autenticado |

### Sugestões

| Método | Rota | Acesso |
|---|---|---|
| POST | `/api/sugestoes` | Autenticado |
| GET | `/api/sugestoes` | Autenticado |
| GET | `/api/sugestoes/{id}` | Autenticado |
| DELETE | `/api/sugestoes/{id}` | Autenticado |

---

## Entregas Acadêmicas

### Entrega 01 e 02 — Planejamento e Design

- **Backlog do Produto:** [Google Docs](https://docs.google.com/document/d/1fHHdMvh4ezyT2cgZMGVdHDAEMCshl091lF6qxCRIG8Y/edit?usp=sharing)
- **Histórias de Usuário (BDD):** [Google Docs](https://docs.google.com/document/d/1rNh6yjRiOeY_gCbTfAXizIdN77B5HpMKccM51h26qmo/edit?usp=sharing)
- **Protótipo Figma:** [Acessar protótipo](https://cold-target-85807250.figma.site/)
- **Screencast:** [Assistir no YouTube](https://youtu.be/EOYhhS9HprU)

### Entrega 03 — MVP

- **Screencast:** [Assistir no YouTube](https://youtu.be/oVw1oaaOWPE)
- **Story Users implementadas:** [Google Docs](https://docs.google.com/document/d/1c6c0XFHAeVdOe4d9dGdpWpzAMPqI-bRmF2OMzn7V9o8/edit?usp=sharing)
- **Issues/Bugtracker:** [Ver issues](https://github.com/brunosm26/Connect_Tickets-Projeto_POO/issues)

<img width="1123" alt="issues" src="https://github.com/user-attachments/assets/74aeece3-3699-425c-a1a0-1f3e1261ed5e" />

---

## Equipe

Projeto acadêmico da disciplina de Programação Orientada a Objetos — ADS Regular.
