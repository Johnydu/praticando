# Cadastro de Funcionarios

Projeto Java de console para cadastrar, listar, buscar, deletar funcionarios e alterar salario usando MySQL.

## Requisitos

- Java 17 ou superior
- MySQL em execucao
- Tabela `funcionarios` criada no banco
- Driver JDBC do MySQL configurado na sua IDE ou no classpath

## Configuracao do banco

Defina estas variaveis de ambiente antes de executar:

- `DB_USER`
- `DB_PASSWORD`

Opcional:

- `DB_URL`

Se `DB_URL` nao for definida, o projeto usa:

```text
jdbc:mysql://localhost:3306/rh_duarte
```

Tambem e possivel passar os valores por propriedades Java:

```bash
-Ddb.user=seu_usuario -Ddb.password=sua_senha -Ddb.url=jdbc:mysql://localhost:3306/rh_duarte
```

## Estrutura esperada da tabela

```sql
CREATE TABLE funcionarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(30) NOT NULL,
    idade INT NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    salario DOUBLE NOT NULL
);
```

Se a tabela ja existir, atualize com:

```sql
ALTER TABLE funcionarios
ADD COLUMN telefone VARCHAR(30) NOT NULL,
ADD COLUMN idade INT NOT NULL,
ADD COLUMN cargo VARCHAR(100) NOT NULL;
```

## Como compilar

```bash
javac -cp src -d out/production src/Main.java src/database/ConexaoDB.java src/database/FuncionarioDAO.java src/funcionario/Funcionario.java src/funcionario/FuncionarioService.java
```

## Como executar

```bash
java -cp out/production Main
```

## Funcionalidades

- Cadastrar funcionario
- Listar funcionarios
- Buscar funcionario por nome
- Deletar funcionario por ID
- Alterar dados por submenu
- Alterar salario por ID
- Alterar telefone por ID
- Alterar idade por ID
- Alterar cargo por ID
- Campos adicionais: telefone, idade e cargo
