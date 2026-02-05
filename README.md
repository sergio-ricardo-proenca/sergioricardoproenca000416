# Desafio Técnico SEPLAG-MT 2026 - Backend Sênior

Este repositório contém a solução técnica para o processo seletivo da **SEPLAG-MT**, cargo **Engenheiro da Computação (Sênior)**.

**Candidato:** SERGIO RICARDO PROENÇA  
**Inscrição:** 16567  
**E-mail:** sergio.rproenca@gmail.com
**CPF:** 000.416.461-00
---

## 🚀 Funcionalidades e Diferenciais Sênior

Além dos requisitos básicos de CRUD, esta implementação foca em escalabilidade, segurança e experiência do usuário (UX):

* **📈 Rate Limiting (Requisito d):** Implementação de um interceptor customizado que limita a 10 requisições por minuto por IP, protegendo a infraestrutura contra ataques de negação de serviço (DoS).
* **🔌 Real-time com WebSocket (Requisito c):** Sistema de notificações em tempo real. Sempre que um novo álbum é cadastrado, os clientes conectados via Stomp/JS recebem um alerta instantâneo.
* **☁️ Cloud Storage Integration (Requisito h):** Integração com **MinIO (S3 compatible)** para armazenamento persistente de capas de álbuns, evitando sobrecarga do banco de dados com arquivos binários.
* **🧪 Testes Unitários (Requisito i):** Cobertura de testes nos serviços de negócio utilizando **JUnit 5** e **Mockito**, garantindo a confiabilidade dos fluxos de salvamento e integração.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17** & **Spring Boot 4.x**
* **PostgreSQL** (Banco de dados relacional)
* **MinIO** (Armazenamento de objetos S3)
* **Docker & Docker Compose** (Orquestração de infraestrutura)
* **Spring Security** (Proteção de endpoints)
* **Maven** (Gerenciamento de dependências)

---

## 📦 Como Executar o Projeto

1. **Subir a infraestrutura (Postgres e MinIO):**
   ```bash
   docker-compose up -d
   
2. Executar a aplicação:

Bash
./mvnw spring-boot:run

3. Executar os testes unitários:

Bash
./mvnw test

📂 Arquitetura do Projeto
O projeto segue os princípios de Clean Code e separação de responsabilidades:

service/: Lógica de negócio e integrações externas.
controller/: Exposição dos endpoints REST.
interceptor/: Segurança e controle de tráfego (Rate Limit).
config/: Configurações de Bean, WebSocket e Segurança.

