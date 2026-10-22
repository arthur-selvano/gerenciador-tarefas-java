📋 Gerenciador de Tarefas (Java + Swing + PostgreSQL + Docker)

Um sistema desktop completo e intuitivo para gerenciamento de pessoas e atribuição de tarefas dentro de uma empresa. O projeto implementa um CRUD completo onde cada tarefa pode ser vinculada ao ID de um usuário cadastrado.

🛠️ Tecnologias e Dependências Utilizadas

Linguagem: Java 25

Interface Gráfica (GUI): Java Swing (javax.swing) e AWT (java.awt)

Banco de Dados: PostgreSQL 16

Containerização: Docker e Docker Compose

Driver JDBC: Driver oficial do PostgreSQL configurado no pom.xml

Arquitetura: View - Model - DAO (Data Access Object)

📂 Estrutura de Diretórios do Projeto

gerenciador-tarefas/                 # Pasta raiz do projeto
├── docker-compose.yml              # Configuração do container PostgreSQL
├── init.sql                        # Script de criação das tabelas e dados iniciais
└── gerenciador-tarefas/            # Subpasta do projeto Java (Maven)
    ├── .gitignore
    ├── pom.xml                     # Configurações do Maven e dependências
    └── src/
        ├── main/
        │   └── java/
        │       └── com/
        │           └── empresa/
        │               └── tarefas/
        │                   ├── config/
        │                   │   └── FabricaConexao.java  # Gerenciador da conexão JDBC
        │                   ├── dao/
        │                   │   ├── PessoaDAO.java       # Operações CRUD de Pessoas
        │                   │   └── TarefaDAO.java       # Operações CRUD de Tarefas
        │                   ├── model/
        │                   │   ├── Pessoa.java          # Record/Modelo de Pessoa
        │                   │   └── Tarefa.java          # Record/Modelo de Tarefa
        │                   ├── view/
        │                   │   ├── JanelaPrincipal.java # Janela base com as abas
        │                   │   ├── PainelPessoas.java   # Formulário e tabela de Pessoas
        │                   │   └── PainelTarefas.java   # Formulário, filtro e ações de Tarefas
        │                   └── Main.java                # Ponto de entrada (Main Class)
        └── resources/


🚀 Como Rodar o Projeto

1. Pré-requisitos

JDK 25 (ou JDK 17+)

Docker e Docker Compose

Git

2. Passo a Passo

1️⃣ Subir o banco de dados no Docker

Na raiz do projeto (onde estão os arquivos docker-compose.yml e init.sql), execute no terminal:

docker-compose up -d


O script init.sql criará as tabelas pessoas e tarefas automaticamente no container PostgreSQL.

2️⃣ Executar a aplicação Java

Opção A: Via IDE (IntelliJ IDEA / Eclipse)

Abra a pasta gerenciador-tarefas/gerenciador-tarefas na sua IDE.

Aguarde a sincronização automática do Maven (pom.xml).

Localize o arquivo src/main/java/com/empresa/tarefas/Main.java e execute a classe (Run).

Opção B: Via Terminal com Maven

Navegue até a pasta interna do projeto Java e execute:

cd gerenciador-tarefas
mvn compile exec:java -Dexec.mainClass="com.empresa.tarefas.Main"


Opção C: Compilação Manual via Terminal (Java CLI)

cd gerenciador-tarefas

# Compilar todas as classes do projeto
javac -d target/classes -cp "src/main/java" src/main/java/com/empresa/tarefas/*.java src/main/java/com/empresa/tarefas/*/*.java

# Executar a aplicação
java -cp target/classes com.empresa.tarefas.Main


💻 Funcionalidades do Sistema

Aba Pessoas:

Cadastrar nova pessoa informando Nome e Email.

Listar todas as pessoas cadastradas na tabela exibindo o ID gerado.

Aba Tarefas:

Cadastrar nova tarefa informando Título, Descrição e ID da Pessoa responsável.

Filtrar a lista de tarefas por ID de usuário.

Marcar tarefas selecionadas como Concluídas.

Deletar tarefas selecionadas da tabela e do banco de dados.
