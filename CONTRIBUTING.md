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

### Autenticacao (publico)
| Metodo | Rota                  | Descricao         |
|--------|-----------------------|-------------------|
| POST   | /api/auth/register    | Cadastrar usuario |
| POST   | /api/auth/login       | Login, retorna JWT |

### Eventos (requer JWT)
| Metodo | Rota                            | Descricao                   |
|--------|---------------------------------|-----------------------------|
| GET    | /api/eventos                    | Listar eventos futuros      |
| GET    | /api/eventos/{id}               | Detalhes de um evento       |
| POST   | /api/eventos                    | Cadastrar evento (admin)    |
| PUT    | /api/eventos/{id}               | Editar evento (admin)       |
| GET    | /api/eventos?categoria=SHOW     | Filtrar por categoria       |
| POST   | /api/eventos/{id}/participar    | Confirmar participacao      |

### Outros (requer JWT)
| Metodo | Rota              | Descricao              |
|--------|-------------------|------------------------|
| POST   | /api/sugestoes    | Sugerir evento         |
| GET    | /api/sugestoes    | Listar sugestoes (admin)|
| POST   | /api/visitas      | Agendar visita         |

### Documentacao
| Recurso             | URL                               |
|---------------------|-----------------------------------|
| Scalar UI           | http://localhost:8080/docs        |
| OpenAPI JSON        | http://localhost:8080/v3/api-docs |

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

### Pendente
- [ ] Implementar `GET /api/eventos?categoria=` — filtro de eventos por categoria
- [ ] Implementar atualizacao de status de Sugestao (aprovar/ignorar) — endpoint ADMIN
- [ ] Revisar cascade de entidades
- [ ] Adicionar DELETE para Agendamento, Inscricao, Visita e Sugestao
- [ ] Atualizar CONTRIBUTING.md com stack e estrutura atual (Supabase, novos DTOs, etc.)
- [ ] Unificar Visita e Agendamento? (código duplicado?, ou eu não entendi a regra de negócio)

### Pendente (produção)
- [ ] Corrigir CORS: restringir `allowedOrigins` para a URL do frontend em producao
- [ ] Mudar `spring.jpa.hibernate.ddl-auto=update` para `validate` em producao

## Equipe

Projeto academico da disciplina de Programacao Orientada a Objetos - ADS Regular.
