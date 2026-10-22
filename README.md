
# 📋 Gerenciador de Tarefas

Sistema desktop para gerenciamento de pessoas e tarefas, desenvolvido com Java Swing e PostgreSQL. O projeto permite cadastrar pessoas, criar tarefas associadas a usuários e acompanhar seu status.

## 🛠️ Tecnologias utilizadas

- Java 25
- Java Swing e AWT
- PostgreSQL 16
- JDBC
- Docker e Docker Compose
- Apache Maven

## 🏗️ Arquitetura

O projeto utiliza uma organização baseada em Model, View e DAO, separando a interface gráfica, os modelos de dados e as operações de acesso ao banco.

### Estrutura de diretórios

```text
gerenciador-tarefas/
├── docker-compose.yml
├── init.sql
└── gerenciador-tarefas/
    ├── .gitignore
    ├── pom.xml
    └── src/
        └── main/
            ├── java/
            │   └── com/
            │       └── empresa/
            │           └── tarefas/
            │               ├── config/
            │               │   └── FabricaConexao.java
            │               ├── dao/
            │               │   ├── PessoaDAO.java
            │               │   └── TarefaDAO.java
            │               ├── model/
            │               │   ├── Pessoa.java
            │               │   └── Tarefa.java
            │               ├── view/
            │               │   ├── JanelaPrincipal.java
            │               │   ├── PainelPessoas.java
            │               │   └── PainelTarefas.java
            │               └── Main.java
            └── resources/
```

## ✨ Funcionalidades

### 👥 Gerenciamento de pessoas

- Cadastro de pessoas com nome e e-mail.
- Listagem de pessoas cadastradas.
- Exibição do ID de cada pessoa.

### 📝 Gerenciamento de tarefas

- Cadastro de tarefas com título, descrição e ID da pessoa responsável.
- Listagem das tarefas cadastradas.
- Filtro de tarefas por ID da pessoa responsável.
- Marcação de tarefas como concluídas.
- Exclusão de tarefas selecionadas.

## 📋 Pré-requisitos

Antes de executar o projeto, instale:

- [JDK 25](https://jdk.java.net/25/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Apache Maven](https://maven.apache.org/) (para execução via terminal)

## 🚀 Como executar

### 1. Clone o repositório

```bash
git clone https://github.com/arthur-selvano/gerenciador-tarefas-java
```

Entre na pasta raiz do projeto:

```bash
cd gerenciador-tarefas
```

### 2. Inicie o banco de dados

Na pasta que contém o arquivo `docker-compose.yml`, execute:

```bash
docker compose up -d
```

O PostgreSQL será iniciado em um container Docker. O arquivo `init.sql` contém o script de inicialização das tabelas e dos dados iniciais.

Para verificar o estado do container:

```bash
docker compose ps
```

### 3. Execute a aplicação

#### Opção A — Pela IDE

1. Abra a pasta `gerenciador-tarefas/gerenciador-tarefas` no IntelliJ IDEA ou Eclipse.
2. Aguarde o Maven carregar as dependências.
3. Abra `src/main/java/com/empresa/tarefas/Main.java`.
4. Execute a classe `Main`.

#### Opção B — Pelo terminal

Entre na pasta interna do projeto Java:

```bash
cd gerenciador-tarefas
```

Compile e execute a aplicação:

```bash
mvn compile exec:java -Dexec.mainClass="com.empresa.tarefas.Main"
```

Essa opção requer que o `pom.xml` esteja configurado com o plugin Maven Exec.

## 🗄️ Banco de dados

A aplicação utiliza PostgreSQL 16. A conexão é gerenciada pela classe `FabricaConexao.java`, que utiliza JDBC para realizar as operações no banco.

Verifique se as configurações de conexão da aplicação correspondem às definidas no `docker-compose.yml`, incluindo host, porta, banco de dados, usuário e senha.

## 🛑 Encerrando o ambiente

Para parar os containers:

```bash
docker compose down
```

Para remover os containers e também os volumes associados:

```bash
docker compose down -v
```

**Atenção:** o segundo comando pode apagar permanentemente os dados persistidos no banco de dados.

## 📌 Próximas melhorias

- Implementação de testes automatizados.
- Validação dos dados informados nos formulários.
- Melhorias no tratamento de exceções.
- Externalização das configurações de conexão com o banco de dados.
- Aprimoramento da interface gráfica.

## 👨‍💻 Objetivo

Projeto desenvolvido para praticar Java, programação orientada a objetos, interfaces gráficas, persistência de dados com PostgreSQL e utilização de Docker em um ambiente de desenvolvimento.
