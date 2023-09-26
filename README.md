<h1>Projeto API REST de Cadastro de Médicos e Pacientes</h1>

<h2>Descrição</h2>
Este projeto consiste em uma API RESTful para realizar operações básicas de gerenciamento de informações de médicos e pacientes, como cadastro, listagem, atualização e exclusão de registros. A API foi desenvolvida utilizando o framework Spring.

<h2>Instruções de Configuração</h2>
<h3>Requisitos</h3>
Antes de começar, certifique-se de ter as seguintes ferramentas e tecnologias instaladas em seu ambiente de desenvolvimento:

* Java SDK
* Spring Boot
* Banco de Dados (por exemplo, MySQL, PostgreSQL)
* Git (opcional)

<h2>Configuração do Banco de Dados</h2>
Crie um banco de dados para o projeto.

Configure as credenciais do banco de dados no arquivo application.properties no projeto Spring.
Exemplo:
  spring.datasource.url=jdbc:mysql://localhost:3306/seubanco
  spring.datasource.username=seuusuario
  spring.datasource.password=suasenha

Instruções de Instalação
1. Clone este repositório em seu ambiente de desenvolvimento.
    git clone https://github.com/seu-usuario/seu-projeto.git

2. Acesse o diretório do projeto.
    cd seu-projeto
  
3. Execute o aplicativo Spring Boot.
    ./mvnw spring-boot:run

<h2>Como Usar</h2>
A API REST de gerenciamento de médicos e pacientes oferece as seguintes operações:

<h3>Cadastro de Médico ou Pacientes:</h3>

Endpoint: POST /medicos ou POST /pacientes
Exemplo de solicitação JSON:

  {
  "nome": "Dr. João Silva",
  "email": "joao.silva@example.com",
  "telefone": "(11) 98765-4321",
  "crm": "12345",
  "especialidade": "Cardiologia",
  "endereco": {
    "logradouro": "Rua das Flores",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "12345-678"
  }
}

<h3>Listagem de Médicos:</h3>
Endpoint: GET /medicos  ou  GET /pacientes

<h3>Atualização de Médico:</h3>
Endpoint: PUT /medicos/{id}  ou PUT /pacientes/{id}
Exemplo de solicitação JSON:

  {
  "nome": "Dr. João da Silva",
  "telefone": "(11) 98765-4321"
  }

<h3>Exclusão de Médico:</h3>
Endpoint: DELETE /medicos/{id}  ou DELETE /pacientes/{id}

<h3>Detalhamento de Médico:</h3>
Endpoint: GET /medicos/{id}  ou GET /pacientes/{id}

<h2>Estrutura do Projeto</h2>
<h3>A estrutura do projeto segue as melhores práticas do Spring Boot, com os seguintes diretórios principais:</h3>

src/main/java/com/med.voll.api: Código-fonte Java.

src/main/resources: Recursos, como arquivos de configuração e templates.

<h2>Dependências</h2>
<h3>As principais dependências do projeto incluem:</h3>

* Spring Boot
* Spring Data JPA
* Hibernate
* Spring Web
* MySQL Connector (ou o conector do banco de dados de sua escolha)
