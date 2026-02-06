# 🏛️ Processo Seletivo SEPLAG-MT 2026

## Cargo: Engenheiro da Computação (Sênior)

Este repositório contém a solução técnica para o desafio prático da SEPLAG-MT. A aplicação consiste em um ecossistema completo para gestão de Artistas e Álbuns, utilizando arquitetura de micro-serviços orquestrada via Docker.

---

### 👤 Candidato
* **Nome:** SERGIO RICARDO PROENÇA
* **N° Inscrição:** 16567
* **E-mail:** sergio.rproenca@gmail.com
* **CPF:** 000.416.461-00
* **Repositório:** [sergio-ricardo-proenca/sergioricardoproenca000416](https://github.com/sergio-ricardo-proenca/sergioricardoproenca000416)

---

### 🛠️ Tecnologias e Stack Técnica
* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.x
* **Banco de Dados:** PostgreSQL 15
* **Storage:** MinIO (S3 Compatible API)
* **Real-time:** WebSocket (STOMP + SockJS)
* **Segurança:** JWT (JSON Web Token) & Rate Limiting
* **Infra:** Docker & Docker Compose
* **Documentação:** OpenAPI 3.0 (Swagger)
* **Migrations:** Flyway

---

### 📊 Status dos Requisitos (Checklist Sênior)

| Requisito | Status | Descrição |
| :--- | :---: | :--- |
| **Segurança JWT** | ✅ | Autenticação com expiração e renovação. |
| **Rate Limit** | ✅ | Limite de 10 requisições/min por IP (Sênior d). |
| **WebSocket** | ✅ | Notificação real-time de novos álbuns (Sênior c). |
| **Cloud Storage** | ✅ | Integração MinIO/S3 para capas (Requisito h). |
| **Links Assinados**| ✅ | Recuperação de imagens com expiração (Requisito i). |
| **Testes Unitários**| ✅ | Cobertura Mockito/JUnit 5 (Sênior b). |
| **Flyway** | ✅ | Migrations e Carga Inicial inclusas (Requisito k). |
| **Docker Compose** | ✅ | Orquestração completa do ecossistema. |
| **Health Checks** | ✅ | Probes de Liveness/Readiness ativos (Sênior a). |

---

### 🏗️ Arquitetura e Decisões Sênior
A aplicação foi estruturada seguindo os princípios de **Clean Code** e **SOLID**:
- **Layered Architecture:** Separação clara entre Controller, Service e Repository.
- **Service Layer:** Centralização da lógica de negócio e integração S3, garantindo alta testabilidade.
- **Rate Limit Interceptor:** Proteção de infraestrutura implementada via Interceptor para controle granular de tráfego.
- **Escalabilidade:** Uso de MinIO para armazenamento de arquivos, mantendo a aplicação *stateless*.

---

### 🚀 Como Executar o Projeto com Docker Compose

A aplicação está configurada para subir todo o ambiente de forma automática.

#### 1. Clonar e Iniciar
```bash
# Clone o repositório
git clone [https://github.com/sergio-ricardo-proenca/sergioricardoproenca000416.git](https://github.com/sergio-ricardo-proenca/sergioricardoproenca000416.git)
cd sergioricardoproenca000416

# Inicie o ecossistema completo
docker-compose up -d --build

Container,Porta Host,Finalidade
argus-api,8080,API Principal (Spring Boot)
argus-db,5433,Banco de Dados PostgreSQL
argus-minio,9000/9001,Storage S3 (Capas de Álbuns)
argus-redis,6379,Cache e Rate Limiting

3. Links Úteis e Monitoramento
Swagger UI: http://localhost:8080/swagger-ui.html

Health Check (Sênior a): http://localhost:8080/actuator/health

MinIO Console: http://localhost:9001 (User/Pass: admin / password123)

4. Executar Testes Unitários
Bash
./mvnw test

📡 Endpoints Principais (Versionados - Requisito j)
POST /api/v1/auth/login - Autenticação JWT.

GET /api/v1/albuns - Listagem paginada (Requisito d).

POST /api/v1/albuns - Cadastro completo com upload S3 e disparo de WebSocket.

GET /api/v1/regionais - Sincronização e listagem (Requisito Sênior e).