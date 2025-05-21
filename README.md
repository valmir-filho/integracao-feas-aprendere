# Integração Feas x Aprendere.

## Conteúdo:

- API REST desenvolvida em Java com Spring Boot para integração entre a Fundação Estatal de Atenção à Saúde (FEAS) e o sistema Aprendere, permitindo consulta de dados de colaboradores a partir do CPF.

## Funcionalidades:

- Consulta de colaborador por número de CPF;
- Validação de CPF com tratamento de erro;
- Retorno em JSON com dados como:;
- CPF;
- RG;
- Nome;
- Nome social;
- Matrícula;
- Data de nascimento;
- E-mail;
- Sexo;
- Situação (ativo/inativo);
- Lotação;
- **Código da empresa** e **Órgão de origem** fixos no JSON.

## Tecnologias utilizadas:

- Java 8;
- Spring Boot 2.7;
- Spring Web;
- Spring JDBC;
- Oracle Database (via `ojdbc8`);
- Maven;
- Apache Tomcat (deploy via `.war`);
- Git e GitHub.

## Como rodar o projeto:

1. Build:

- bash: mvn clean package

2. Deploy no Tomcat:

- Copie o arquivo target/integracaoFeasAprendere.war para a pasta webapps/ do seu Tomcat e reinicie o servidor.

3. Endpoint da API:

- http://<ip_do_servidor>:8080/integracaoFeasAprendere/dados?cpf=00000000000

4. Resposta JSON:

{
  "CODIGO EMPRESA": 1,
  "CPF": 00000000000,
  "RG": "00000000",
  "NOME": "XXXXXX XXXX XXXXX XXXXX",
  "NOME SOCIAL": "NAO INFORMADO",
  "MATRICULA": 00000,
  "DATA DE NASCIMENTO": "0000-00-00",
  "EMAIL": "XXXXXXXX@XXXXX.XXX",
  "SEXO": "X",
  "ATIVO": 1,
  "ORGAO ORIGEM": "XXXX",
  "LOTACAO": "XXX - XXX. XXXXXXX"
}

### Used IDE: IntelliJ.
