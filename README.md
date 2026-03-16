🏟️ Arena Connect
Gestão de Eventos & Engajamento para Grandes Arenas
<p align="center"> Sistema desenvolvido para **gerenciamento inteligente de eventos**, permitindo interação entre **cidadãos e administradores** de um complexo de eventos. </p>
📌 Sobre o Projeto

💡 Arena Connect é uma solução robusta para a gestão de eventos em grandes arenas e centros de convenções.

O sistema permite que cidadãos acompanhem eventos, confirmem presença e sugiram novas atividades, enquanto administradores gerenciam toda a agenda através de um painel estratégico com métricas e curadoria de conteúdo.

A aplicação foi construída utilizando Java e Spring Boot, seguindo boas práticas de arquitetura em camadas e desenvolvimento de APIs REST.

🚀 Funcionalidades
👤 Área do Cidadão
📅 Catálogo de Eventos

Visualização de eventos futuros com informações completas:

Data

Categoria

Descrição detalhada

🔎 Filtros Dinâmicos

Busca rápida por categorias de eventos:

⚽ Esportes

🎤 Shows

🎭 Cultural

🏢 Corporativo

🎟️ Reserva de Vagas (RSVP)

Os usuários podem confirmar presença em eventos.

✔ Atualização automática da capacidade disponível
✔ Controle de vagas em tempo real

💬 Interatividade com a Arena

O cidadão pode:

Sugerir novos eventos

Solicitar visitas guiadas à Arena

🔑 Painel Administrativo
📋 CRUD de Eventos

Os administradores podem:

✔ Criar eventos
✔ Editar informações
✔ Atualizar dados
✔ Gerenciar agenda completa da Arena

📊 Dashboard Estatístico

Painel com indicadores importantes como:

📅 Total de eventos cadastrados

👥 Média de público

📈 Categorias mais populares

💡 Gestão de Sugestões

Sistema para analisar sugestões enviadas pela comunidade.

Os administradores podem:

✔ Aprovar sugestões
✔ Rejeitar ideias
✔ Transformar sugestões em novos eventos

🎨 Design & Experiência do Usuário

O protótipo visual do sistema foi desenvolvido com foco em usabilidade e experiência do usuário (UX).

🔗 Acesse o protótipo no Figma

https://cold-target-85807250.figma.site/home
🛠️ Stack Tecnológica
Backend
Tecnologia	Descrição
Java 17+	Linguagem principal
Spring Boot 3	Framework backend
Spring Data JPA	Persistência de dados
REST API	Comunicação entre sistemas
Banco de Dados
Tecnologia	Uso
MySQL / PostgreSQL (definir)	Armazenamento dos dados
Ferramentas
Ferramenta	Uso
Maven	Gerenciamento de dependências
Git / GitHub	Controle de versão
Figma	Prototipação de interface
🧱 Arquitetura do Projeto

O sistema segue o padrão de arquitetura em camadas, amplamente utilizado em aplicações Spring Boot.

Controller
   ↓
Service
   ↓
Repository
   ↓
Database
📂 Estrutura
src
 ┣ controller
 ┣ service
 ┣ repository
 ┣ model
 ┗ dto
⚙️ Como Rodar o Projeto
1️⃣ Clone o repositório
git clone https://github.com/seu-usuario/arena-connect.git
2️⃣ Acesse a pasta do projeto
cd arena-connect
3️⃣ Instale as dependências

Certifique-se de que o Maven esteja instalado.

mvn clean install
4️⃣ Execute a aplicação
mvn spring-boot:run
🌐 Acesse a API

A aplicação estará disponível em:

http://localhost:8080
