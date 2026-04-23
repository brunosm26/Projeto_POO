# Connect Tickets - Arena de Pernambuco

Sistema web para conectar a populacao a Arena de Pernambuco, centralizando a divulgacao de eventos, participacao do publico, sugestoes e agendamento de visitas.

## Problema

A Arena de Pernambuco possui baixa ocupacao efetiva e dificuldades de comunicacao com o publico. O Connect Tickets resolve isso centralizando informacoes de eventos, gerenciando participacoes e fornecendo dashboards de ocupacao para os gestores.

## Stack

| Camada         | Tecnologia                                    |
|----------------|-----------------------------------------------|
| Linguagem      | Java 21                                       |
| Framework      | Spring Boot 3.4.4                             |
| Web            | Spring MVC (REST API)                         |
| Seguranca      | Spring Security + JWT (auth0 java-jwt 4.4.0)  |
| Persistencia   | Spring Data JPA + Hibernate                   |
| Banco de Dados | H2 (desenvolvimento/memoria)                  |
| Documentacao   | SpringDoc OpenAPI + Scalar UI                 |
| Build          | Maven (wrapper incluso)                       |
| Testes         | JUnit 5                                       |

## Entidades

### User
| Campo         | Tipo          | Observacao                   |
|---------------|---------------|------------------------------|
| id            | Long (PK)     | Auto gerado                  |
| name          | String        | Obrigatorio                  |
| email         | String        | Unico, obrigatorio           |
| password_hash | String        | Obrigatorio                  |
| role          | Enum          | `USER` ou `ADMIN`            |
| created_at    | LocalDateTime | Automatico                   |

### Event
| Campo           | Tipo          | Observacao                          |
|-----------------|---------------|-------------------------------------|
| id              | Long (PK)     | Auto gerado                         |
| name            | String        | Obrigatorio                         |
| description     | String        | Descricao completa                  |
| date_time       | LocalDateTime | Data e horario do evento            |
| category        | Enum          | `ESPORTE`, `SHOW`, `CULTURAL`, `CORPORATIVO` |
| max_capacity    | Integer       | Capacidade maxima                   |
| image_url       | String        | URL da imagem                       |
| location_detail | String        | Detalhe do local                    |
| created_by      | Long (FK)     | Referencia ao User (admin criador)  |
| created_at      | LocalDateTime | Automatico                          |
| updated_at      | LocalDateTime | Automatico                          |

### Participation
| Campo        | Tipo          | Observacao             |
|--------------|---------------|------------------------|
| id           | Long (PK)     | Auto gerado            |
| user_id      | Long (FK)     | Referencia ao User     |
| event_id     | Long (FK)     | Referencia ao Event    |
| confirmed_at | LocalDateTime | Data de confirmacao    |

### Visit
| Campo      | Tipo          | Observacao         |
|------------|---------------|--------------------|
| id         | Long (PK)     | Auto gerado        |
| user_id    | Long (FK)     | Referencia ao User |
| date       | LocalDate     | Data da visita     |
| time       | LocalTime     | Horario da visita  |
| created_at | LocalDateTime | Automatico         |

### Suggestion
| Campo       | Tipo      | Observacao                              |
|-------------|-----------|-----------------------------------------|
| id          | Long (PK) | Auto gerado                             |
| name        | String    | Nome do evento sugerido                 |
| description | String    | Descricao da sugestao                   |
| category    | Enum      | `ESPORTE`, `SHOW`, `CULTURAL`, `CORPORATIVO` |
| status      | Enum      | `PENDENTE`, `APROVADA`, `IGNORADA`      |
| user_id     | Long (FK) | Referencia ao User                      |

## Estrutura do Projeto

```
src/main/java/com/projetopoo/mytickets/
├── Application.java
├── config/
│   └── SecurityConfig.java              # Spring Security + JWT
├── controller/
│   ├── AuthController.java              # POST /api/auth/login, /register
│   ├── EventoController.java            # CRUD de eventos
│   ├── InscricaoController.java         # Participacao em eventos
│   ├── SugestaoController.java          # Sugestoes de eventos
│   ├── VisitaController.java            # Agendamento de visitas
│   ├── AdminController.java             # Endpoints administrativos
│   └── ScalarController.java            # Documentacao em /docs
├── model/
│   ├── Usuario.java
│   ├── Evento.java
│   ├── Inscricao.java
│   ├── Sugestao.java
│   ├── Visita.java
│   └── DTOs/                            # Records de request/response
├── repository/                          # Interfaces Spring Data JPA
├── service/                             # Logica de negocio
└── security/
    ├── TokenService.java                # Geracao e validacao JWT
    ├── JwtAuthenticationFilter.java     # Filtro Bearer token
    ├── CustomUserDetails.java
    └── CustomUserDetailsService.java
```

## Endpoints

Base URL: `http://localhost:8080`
Documentacao interativa: `/docs` (Scalar UI) | OpenAPI JSON: `/v3/api-docs`

Apos o login, envie o token em todas as requisicoes autenticadas:
```
Authorization: Bearer {token}
```
> Token JWT com expiracao de **2 horas**. Algoritmo HMAC256.

### Autenticacao (publico)

| Metodo | Rota                       | Acesso  | Status |
|--------|----------------------------|---------|--------|
| POST   | `/api/auth/register`       | Publico | [x]     |
| POST   | `/api/auth/login`          | Publico | [x]     |
| POST   | `/api/auth/register-admin` | ADMIN   | [x]     |

### Eventos (leitura publica)

| Metodo | Rota                                    | Acesso  | Status      |
|--------|-----------------------------------------|---------|-------------|
| GET    | `/api/eventos`                          | Publico | [x]          |
| GET    | `/api/eventos/{id}`                     | Publico | [x]          |
| POST   | `/api/eventos`                          | ADMIN   | [x]          |
| PUT    | `/api/eventos/{id}`                     | ADMIN   | [x]          |
| DELETE | `/api/eventos/{id}`                     | ADMIN   | [x]          |
| POST   | `/api/eventos/{id}/admins/{idUsuario}`  | ADMIN   | [x]          |
| DELETE | `/api/eventos/{id}/admins/{idUsuario}`  | ADMIN   | [x]          |
| GET    | `/api/eventos?categoria={categoria}`    | Publico | [ ] pendente |

> Categorias: `ESPORTE`, `SHOW`, `CULTURAL`, `CORPORATIVO`

### Usuarios (admin)

| Metodo | Rota                   | Acesso                   | Status      |
|--------|------------------------|--------------------------|-------------|
| GET    | `/api/usuarios`        | ADMIN                    | [x]          |
| GET    | `/api/usuarios/{id}`   | ADMIN                    | [x]          |
| POST   | `/api/usuarios`        | ADMIN                    | [x]          |
| PUT    | `/api/usuarios/{id}`   | ADMIN / proprio usuario  | [x]          |
| DELETE | `/api/usuarios/{id}`   | ADMIN                    | [ ] pendente |

### Inscricoes

| Metodo | Rota                    | Acesso      | Status      |
|--------|-------------------------|-------------|-------------|
| POST   | `/api/inscricoes`       | Autenticado | [x]          |
| GET    | `/api/inscricoes`       | ADMIN       | [x]          |
| GET    | `/api/inscricoes/me`    | Autenticado | [x]          |
| DELETE | `/api/inscricoes/{id}`  | Autenticado | [ ] pendente |

### Agendamentos

| Metodo | Rota                       | Acesso      | Status      |
|--------|----------------------------|-------------|-------------|
| POST   | `/api/agendamentos`        | Autenticado | [x]          |
| GET    | `/api/agendamentos`        | ADMIN       | [x]          |
| GET    | `/api/agendamentos/{id}`   | Autenticado | [x]          |
| DELETE | `/api/agendamentos/{id}`   | Autenticado | [ ] pendente |

### Visitas

| Metodo | Rota                 | Acesso      | Status      |
|--------|----------------------|-------------|-------------|
| POST   | `/api/visitas`       | Autenticado | [x]          |
| GET    | `/api/visitas`       | Autenticado | [x]          |
| GET    | `/api/visitas/{id}`  | Autenticado | [x]          |
| DELETE | `/api/visitas/{id}`  | Autenticado | [ ] pendente |

### Sugestoes

| Metodo | Rota                              | Acesso      | Status      |
|--------|-----------------------------------|-------------|-------------|
| POST   | `/api/sugestoes`                  | Autenticado | [x]          |
| GET    | `/api/sugestoes`                  | Autenticado | [x]          |
| GET    | `/api/sugestoes/{id}`             | Autenticado | [x]          |
| PUT    | `/api/sugestoes/{id}/status`      | ADMIN       | [ ] pendente |
| DELETE | `/api/sugestoes/{id}`             | Autenticado | [ ] pendente |

### Legenda

| Simbolo | Significado                    |
|---------|--------------------------------|
| [x]     | Implementado e disponivel      |
| [ ]     | Pendente de implementacao      |

## Como Executar

```bash
# clonar o repositorio
git clone https://github.com/brunosm26/Connect_Tickets-Projeto_POO.git
cd Connect_Tickets-Projeto_POO

# permissao do maven wrapper (mac/linux)
chmod +x mvnw

# rodar
./mvnw spring-boot:run
```

## Documentacao de Negocio

- **Backlog do Produto:** [Google Docs](https://docs.google.com/document/d/1fHHdMvh4ezyT2cgZMGVdHDAEMCshl091lF6qxCRIG8Y/edit?usp=sharing)
- **Historias de Usuario (BDD):** [Google Docs](https://docs.google.com/document/d/1rNh6yjRiOeY_gCbTfAXizIdN77B5HpMKccM51h26qmo/edit?usp=sharing)
- **Prototipo Figma:** [Figma](https://cold-target-85807250.figma.site/)
- **Screencast:** [YouTube](https://youtu.be/EOYhhS9HprU)

## TO DO

### Feito
- [x] Alinhar entidade `Usuario`: `isAdmin (Boolean)` → `role (ENUM)`
- [x] Adicionar `created_at` e `updated_at` na entidade `Usuario`
- [x] Mover JWT secret para variavel de ambiente (`JWT_SECRET`)
- [x] Migrar banco de H2 para Supabase PostgreSQL
- [x] Adicionar Docker setup
- [x] Criar camada de servicos (logica de negocio separada dos controllers)
- [x] Criar DTOs para desacoplar a API das entidades JPA
- [x] Tratamento global de excecoes (`GlobalExceptionHandler`)
- [x] Excecoes customizadas (`EntityNotFoundException`, `BusinessException`)
- [x] Corrigir permissoes de rotas no `SecurityConfig` (`/api/auth/register-admin` protegido)
- [x] Padronizar prefixo `/api/*` em todos os controllers
- [x] Corrigir N+1 queries (`FetchType.LAZY`) e referencias circulares (`@JsonIgnore`)
- [x] Padronizar nomes de colunas para ingles (`booking_id`, `booked_at`, etc.)
- [x] Logging estruturado (substituir `e.printStackTrace()` por SLF4J)
- [x] Corrigir compatibilidade com Supabase/PgBouncer (`prepareThreshold=0`)
- [x] Renomear pasta `DTOs` para `dtos` (alinhamento com declaracao de pacote)
- [x] CRUD completo de Eventos: `GET`, `POST`, `PUT /api/eventos/{id}`, `DELETE /api/eventos/{id}`
- [x] Gerenciamento de admins de evento: `POST/DELETE /api/eventos/{id}/admins/{idUsuario}`
- [x] CRUD de Usuarios: `GET`, `GET /{id}`, `POST`, `PUT /api/usuarios/{id}`
- [x] Inscricoes: `POST`, `GET` (admin), `GET /me` (usuario logado)
- [x] Agendamentos: `POST`, `GET` (admin), `GET /{id}`
- [x] Visitas: `POST`, `GET`, `GET /{id}`
- [x] Sugestoes: `POST`, `GET`, `GET /{id}`
- [x] Autenticacao JWT: login, register, register-admin (protegido por ADMIN)
- [x] Documentacao da API disponivel em `/docs` (Scalar UI)
- [x] Proteger `GET /api/usuarios` — atualmente publico sem autenticacao
- [x] Refatorar `Visita`: unificar `date` + `time` em `scheduled_at`
- [x] Adicionar `MockDataInitializer` — cria admin e user padrao ao subir em perfil `dev`
- [x] Implementar `GET /api/eventos?categoria=` — filtro de eventos por categoria

### Pendente

- [ ] Implementar atualizacao de status de Sugestao (aprovar/ignorar) — endpoint ADMIN
- [ ] Revisar cascade de entidades
- [x] Adicionar DELETE para Agendamento, Inscricao, Visita e Sugestao
- [ ] Atualizar CONTRIBUTING.md com stack e estrutura atual (Supabase, novos DTOs, etc.)
- [ ] Unificar Visita e Agendamento? (código duplicado?, ou eu não entendi a regra de negócio)

### Pendente (produção)
- [ ] Corrigir CORS: restringir `allowedOrigins` para a URL do frontend em producao
- [ ] Mudar `spring.jpa.hibernate.ddl-auto=update` para `validate` em producao

## Equipe

Projeto academico da disciplina de Programacao Orientada a Objetos - ADS Regular.
