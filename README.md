💸 Dashboard Financeiro - Backend

API REST para controle financeiro pessoal, desenvolvida com foco em boas práticas de arquitetura, organização de código e simulação de um sistema real.

🚀 Funcionalidades
📥 Cadastro de receitas (entradas)
📤 Cadastro de despesas (saídas)
📊 Dashboard mensal:
  Total de entradas
  Total de saídas
  Saldo
📈 Análise de gastos por categoria (%)
🎯 Metas financeiras por categoria
🚨 Alertas de gastos ao ultrapassar metas
📄 Documentação interativa com Swagger

🛠️ Tecnologias utilizadas
Java 17+
Spring Boot
Spring Data JPA
MySQL
Lombok
Swagger (OpenAPI)

🧱 Arquitetura

O projeto segue uma estrutura em camadas:

controller/
service/
repository/
dto/
model/
config/

📚 Boas práticas aplicadas
✔ Uso de DTOs (Request e Response)
✔ Separação de responsabilidades (Controller, Service, Repository)
✔ Validação de dados com Bean Validation
✔ Uso de BigDecimal para valores monetários
✔ Código limpo e organizado
✔ Documentação automática com Swagger

⚙️ Como rodar o projeto
🔹 Pré-requisitos
Java 17+
Maven
MySQL

🔹 1. Clonar o repositório
git clone https://github.com/Lcvianasz/dashboard-financeiro-backend.git
🔹 2. Configurar o banco de dados

Crie um banco no MySQL:

CREATE DATABASE financeiro_db;

🔹 3. Configurar o application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/financeiro_db
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

🔹 4. Rodar o projeto
mvn spring-boot:run

🌐 Acessos
🔗 API: http://localhost:8080
📄 Swagger: http://localhost:8080/swagger-ui/index.html

📌 Exemplos de endpoints

📊 Dashboard
GET /dashboard?mes=4&ano=2026

📈 Categorias
GET /dashboard/categorias?mes=4&ano=2026

🚨 Alertas
GET /dashboard/alertas?mes=4&ano=2026

💸 Transações
POST /transacoes
GET /transacoes

DELETE /transacoes/{id}

🎯 Metas
POST /metas
GET /metas

🔜 Próximas melhorias
🔐 Autenticação com JWT
👤 Multi-usuário
📊 Integração com frontend (gráficos)
🧪 Testes automatizados
☁️ Deploy em nuvem
📷 Preview

Em breve: prints ou GIF do sistema

🤝 Contribuição

Sinta-se à vontade para abrir issues ou enviar pull requests.

📬 Contato

Desenvolvido por Lucas Viana Souza
🔗 https://github.com/Lcvianasz

⭐ Se curtir o projeto

Deixe uma estrela ⭐ no repositório!
