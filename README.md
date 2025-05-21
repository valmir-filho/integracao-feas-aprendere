# Integração Feas x Aprendere

API REST desenvolvida em Java com Spring Boot para integração entre a Fundação Estatal de Atenção à Saúde (FEAS) e o sistema Aprendere, permitindo consulta de dados de colaboradores a partir do CPF.

---

## 🔍 Funcionalidades

- Consulta de colaborador por número de CPF
- Validação de CPF com tratamento de erro
- Retorno em JSON com dados como:
  - CPF
  - RG
  - Nome
  - Nome social
  - Matrícula
  - Data de nascimento
  - E-mail
  - Sexo
  - Situação (ativo/inativo)
  - Lotação
  - **Código da empresa** e **Órgão de origem** fixos no JSON

---

## 🚀 Tecnologias utilizadas

- Java 8
- Spring Boot 2.7
- Spring Web
- Spring JDBC
- Oracle Database (via `ojdbc8`)
- Maven
- Apache Tomcat (deploy via `.war`)
- Git e GitHub

---

## 📦 Como rodar o projeto

### 1. Build do projeto

```bash
mvn clean package
