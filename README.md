# Dashboard Financeiro - Backend

Backend da aplicação de controle financeiro com carteira de investimentos integrada à API da Brapi.dev.

## 📋 Tecnologias

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- Spring WebFlux (WebClient)
- Spring Cache
- MySQL
- Maven
- Swagger/OpenAPI
- WebSocket (opcional)

## 🚀 Funcionalidades

- ✅ CRUD de transações (receitas/despesas)
- ✅ Metas financeiras
- ✅ Dashboard com resumos e gráficos
- ✅ Carteira de investimentos (compra/venda de ativos)
- ✅ Integração com a API Brapi.dev para cotações em tempo real
- ✅ Cálculo de rentabilidade e preço médio
- ✅ Cache de cotações
- ✅ Documentação com Swagger

## 🛠️ Pré-requisitos

- Java 17 ou superior
- MySQL 8.0+
- Maven
- Chave de API da [Brapi.dev](https://brapi.dev) (plano gratuito suficiente)

## ⚙️ Configuração

1. **Clone o repositório**
```bash
git clone https://github.com/seu-usuario/dashboard-financeiro-backend.git
cd dashboard-financeiro-backend

    Crie o banco de dados MySQL

sql

CREATE DATABASE financeiro_db;

    Configure o application.properties

properties

spring.datasource.url=jdbc:mysql://localhost:3306/financeiro_db
spring.datasource.username=root
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080

# Brapi.dev
brapi.api.base-url=https://brapi.dev/api
brapi.api.token=SEU_TOKEN_AQUI

    Execute a aplicação

bash

mvn spring-boot:run

A aplicação estará disponível em http://localhost:8080.
📚 Documentação da API

Após iniciar, acesse: http://localhost:8080/swagger-ui.html
🔌 Endpoints Principais
Transações

    GET /api/transacoes – Lista todas as transações

    POST /api/transacoes – Cria uma nova transação

    DELETE /api/transacoes/{id} – Remove uma transação

Metas

    GET /api/metas – Lista metas

    POST /api/metas – Cria uma meta

    PUT /api/metas/{id} – Atualiza meta

Dashboard

    GET /api/dashboard?mes=4&ano=2026 – Resumo do mês

    GET /api/dashboard/categorias?mes=4&ano=2026 – Gastos por categoria

Investimentos

    GET /api/investimentos/carteira – Obtém a carteira com cotações atualizadas

    POST /api/investimentos/comprar – Registra compra de ativo

    POST /api/investimentos/vender – Registra venda de ativo

🧪 Testando com Postman

Use os headers:

    X-User-Id: 1 (ID fixo para testes – a autenticação será implementada futuramente)

Exemplo de compra:
json

POST /api/investimentos/comprar
{
  "simbolo": "PETR4",
  "quantidade": 10,
  "precoUnitario": 47.16,
  "observacao": "Primeira compra"
}

📦 Estrutura do Projeto
text

src/main/java/com/dashboard/financeiro/
├── config/          – Configurações (WebClient, Swagger, WebSocket)
├── controller/      – Endpoints REST
├── dto/             – Objetos de transferência
├── model/           – Entidades JPA
├── repository/      – Interfaces Spring Data
├── service/         – Regras de negócio
└── exception/       – Tratamento de erros

📌 Observações

    A autenticação básica via header X-User-Id é utilizada para simplificar os testes.

    O cache das cotações é feito em memória (@Cacheable) para evitar chamadas excessivas à API.

    O plano gratuito da Brapi.dev tem limite de 1 ativo por requisição e delay de 30 minutos.

👨‍💻 Autor
Lucas Viana Souza
