# Teste Prático — Iniflex

## Teste Prático de Programação

Considerando que uma indústria possui as pessoas/funcionários abaixo:

<img width="770" height="404" alt="custom-test-102943-question-eee278f5-a195-4265-956e-dcfcef31bafb" src="https://github.com/user-attachments/assets/f0c9643e-20e7-4223-a5da-5f1ce26da721" />

Diante disso, você deve desenvolver um projeto Java com os seguintes requisitos:

### 1. Classe Pessoa

Criar uma classe `Pessoa` com os seguintes atributos:

* `nome` (`String`)
* `dataNascimento` (`LocalDate`)

### 2. Classe Funcionário

Criar uma classe `Funcionario` que estenda a classe `Pessoa`, contendo os seguintes atributos:

* `salario` (`BigDecimal`)
* `funcao` (`String`)

### 3. Classe Principal

Deve conter uma classe `Principal` para executar as seguintes ações:

#### 3.1 — Inserção dos funcionários

Inserir todos os funcionários, na mesma ordem e com as mesmas informações da tabela apresentada no teste.

#### 3.2 — Remoção do funcionário João

Remover o funcionário **"João"** da lista.

#### 3.3 — Impressão dos funcionários

Imprimir todos os funcionários com todas as suas informações, sendo que:

* A informação de data deve ser exibida no formato `dd/mm/aaaa`;
* A informação de valor numérico deve ser exibida com separador de milhar como ponto e decimal como vírgula.

#### 3.4 — Aumento salarial

Os funcionários receberam **10% de aumento de salário**.

Atualizar a lista de funcionários com o novo valor dos salários.

#### 3.5 — Agrupamento por função

Agrupar os funcionários por função em um `Map`, sendo:

* A chave: a **função**;
* O valor: a **lista de funcionários**.

#### 3.6 — Impressão agrupada

Imprimir os funcionários agrupados por função.

#### 3.8 — Aniversariantes

Imprimir os funcionários que fazem aniversário nos meses:

* Outubro (`10`);
* Dezembro (`12`).

#### 3.9 — Funcionário com maior idade

Imprimir o funcionário com a maior idade.

Exibir os seguintes atributos:

* Nome;
* Idade.

#### 3.10 — Ordem alfabética

Imprimir a lista de funcionários em ordem alfabética.

#### 3.11 — Total dos salários

Imprimir o total dos salários dos funcionários.

#### 3.12 — Quantidade de salários mínimos

Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é:

**R$ 1.212,00**

---

## Orientações Gerais

* Você poderá utilizar a ferramenta que tem maior domínio, como Eclipse, NetBeans etc.

---

## Como executar o código

Para executar o projeto, abra o **terminal** na raiz do projeto.

Certifique-se de que o **Java JDK 8 ou superior** esteja instalado:

```bash
java -version
```

No terminal, execute o comando abaixo para compilar todas as classes:

```bash
javac -d bin src/entities/*.java src/application/*.java
```

Após a compilação, execute a aplicação pelo terminal:

```bash
java -cp bin application.Principal
```

O resultado das operações será exibido no terminal.
