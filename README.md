# Objetivo
## Criar uma API Rest para cadastrar e manipular débitos
### Requisitos Funcionais
* 1. Buscar todos os débitos
  * a. Paginação e possibilidade de filtrar por data de lançamento, cpf e nome da pessoa
* 2. Buscar um débito por id
* 3. Buscar o valor total de todos os débitos lançados
* 4. Criar um débito
  * a. Campos obrigatórios
    * i. data de lançamento
    * ii. cpf da pessoa
    * iii. nome da pessoa
    * iv. parcelas
      * 1. número
      * 2. data de vencimento
      * 3. valor
  *b. Validações:
    * i. Deve ter pelo menos uma parcela
    * ii. A data de lançamento deve ser menor ou igual a data atual
    * iii. O número da parcela deve ser único por débito
    * iv. A data de vencimento da parcela deve ser maior do que a data atual
    * v. O valor da parcela deve ser maior que 0
    * vi. O CPF da pessoa deve ser válido
* 5. Incluir uma parcela nova em um débito já existente
* 6. Alterar a data de vencimento de uma parcela
* 7. Excluir um débito
     
### Requisitos Técnicos
  * Java e spring boot
  * Salvar em algum banco de dados relacional
  * Testes unitários e de integração


# Scripts Banco

```
create table aise.pessoa(
  idPessoa bigserial NOT NULL PRIMARY KEY,
  nomePessoa varchar(200) not null,
  pfjPessoa varchar(14) not null,
  tipo VARCHAR(8) check(tipo in ('FISICA', 'JURIDICA')) not null
);

create table aise.debito(
  idDebito bigserial NOT NULL PRIMARY KEY,
  idPessoa bigserial not null,
  dataLancamento date not null,
  origemDebito varchar(200) not null,
  constraint fkidPessoa foreign key (idPessoa) references pessoa (idPessoa)
);

create table aise.debitoParcela(
  idDebitoParcela bigserial NOT NULL PRIMARY KEY,
  idDebito bigserial not null,
  parcela integer not null,
  dataVencimento date not null,
  valor numeric(15,2) not null,
  situacao VARCHAR(6) check(situacao in ('ABERTO', 'PAGO')) not null,
  constraint fkidDebito foreign key (idDebito) references debito (iddebito)
);
```
