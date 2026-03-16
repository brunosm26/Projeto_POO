🏟️ Arena Connect: Gestão de Eventos & Engajamento
O Arena Connect é uma solução robusta para a gestão de grandes complexos de eventos. Desenvolvido em Java com Spring Boot, o sistema permite que cidadãos acompanhem a agenda da Arena, confirmem presença e sugiram novos eventos, enquanto oferece aos administradores um painel de controle estratégico com métricas e curadoria de conteúdo.

📖 Funcionalidades (User Stories)
O projeto foi construído para atender aos seguintes requisitos de negócio:

👤 Área do Cidadão
Catálogo de Eventos: Visualização de eventos futuros com detalhes completos (data, categoria e descrição).

Filtros Dinâmicos: Busca por categorias como Esportes, Shows, Cultural e Corporativo.

Reserva de Vagas (RSVP): Confirmação de participação com atualização automática da capacidade do evento.

Interatividade: Canal para sugestão de novos eventos e agendamento de visitas guiadas à Arena.

🔑 Painel Administrativo
CRUD de Eventos: Cadastro, edição e atualização de informações dos eventos.

Dashboard Estatístico: Visualização de total de eventos, média de público e categorias mais populares.

Gestão de Sugestões: Fluxo de aprovação ou descarte de ideias enviadas pela comunidade.

🎨 Design & UX
O protótipo visual foi desenhado com foco em usabilidade e pode ser acessado através do link abaixo:

🔗 Acesse o Protótipo no Figma

<img width="1894" height="906" alt="image" src="https://github.com/user-attachments/assets/aa1a7f5f-99cc-4940-a306-9d98247ef792" />


🛠️ Stack Técnica
Linguagem: Java 17+

Framework Principal: Spring Boot 3

Persistência de Dados: Spring Data JPA

Banco de Dados: ...

Gerenciador de Dependências: Maven

Arquitetura: REST API com separação de camadas (Controller, Service, Repository).

🚀 Como Rodar o Projeto
Clone o repositório:

Bash

git clone https://github.com/seu-usuario/arena-connect.git
Entre na pasta do projeto:

Bash

cd arena-connect
Certifique-se de ter o Maven instalado e execute:

Bash

mvn clean install
mvn spring-boot:run
Acesse a aplicação:
A API estará rodando em http://localhost:8080.
