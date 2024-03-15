# fiap.hackaton.booking.api

Hackaton Challenge FIAP Fase 5 - Booking API

## Relatório Técnico

---
- [Introdução - Desenvolvimento, Arquitetura e Entidades](#introdução---desenvolvimento-arquitetura-e-entidades)
- [Bibliotecas em Destaque](#bibliotecas-em-destaque)
- [Testes Unitários](#testes-unitários)
- [Plugins da IDE](#plugins-da-ide)

### Introdução - Desenvolvimento, Arquitetura e Entidades

Seguindo os requisitos do PDF  disponibilizado contendo as regras do Hackaton, 
o projeto foi desenvolvido com o intuito mostrar o conhecimento aprendido a respeito 
da arquitetura de microsserviços, da implementação de testes unitários e principalmente 
o uso de codificação limpa. Vale ressaltar que o projeto foi totalmente desenvolvido
em inglês.

#### Desenvolvimento e Arquitetura

Para iniciar o desenvolvimento da solução, foi inicialmente pensando um modelo
de dois microsserviços: **Booking Management API** e **Booking API**, onde
este primeiro concentra toda a gestão de **Quartos de Hotéis** e 
de **Serviços Adicionais**,enquanto o segundo é focado no usuário, contendo 
a lógica de gestão de **Clientes** e de suas **Reservas**.

Falando sobre a arquitetura, para a concepção desse sistema foi
utilizada como base uma arquitetura padrão MVC, levando em consideração
os conceitos de **Domain Driven Design** e **Clean Code**. Por conta
disso, os pacotes _Controller_, _Service_, _Domain/Model_ e _Repository_
são implementados como de costume, enquanto pacotes adicionais como _UseCases_,
_Builder_, _Exceptions_ e _Properties_ foram criados para auxiliar na manutenção
do conceito de código limpo, reduzindo quantidades exorbitantes de dependências.

Passando rapidamente pelos pacotes principais, o Controller é aquele responsável
por criar uma porta de entrada e saída para requisições externas, seja de usuários
físicos, ou aplicações terceiras, para o Booking API e Booking Management API.
A Repository faz o papel de se comunicar de fato com o banco de dados, enquanto
a Service faz o papel de "implementar" sua lógica, isolando assim o acesso direto à
Repository por qualquer outra classe.


#### Dados e Entidades

Para gestão de dados foi utilizado o banco de dados relacional **PostgreSQL**
Segue abaixo modelo relacional:


## Bibliotecas Em Destaque

---

Impossível não ressaltar novamente a importancia do MapStruct, que foi
muito utilizado para fazer a comunicação entre os objetos das camadas,
transformando objetos de requests em objetos de Model, ou objetos de Model
para objetos de Entity, por exemplo.

Outra biblioteca mega importante foi o Flyway, para o uso de Migrations no
banco de dados. Isso possibilitou o desenvolvimento ágil e muito tempo de
configuração de banco de dados economizada.

## Testes Unitários

---

Utilizando o JUnit 5, forma feitos os testes unitários na aplicação, com
foco nos testes de regras de negócio, e o conceito de TDD foi utilizado
para a aplicação deles na camada de Service e UseCases do projeto. A ideia desses testes
foi mapear exatamente todos os cenários de cada método, seja sucesso, erros
e tipos de erros de forma independente.


## Plugins da IDE

---

O SonarLint foi um ótimo companheiro de desenvolvimento para esse projeto,
sendo muito utilizado para identificar lógicas que poderiam ser refatoradas para
uma forma mais simples, importações não utilizadas e nomenclaturas incorretas.


*Nota : Ao subir a aplicação, é possível acessar seu Swagger, através deste link:* http://localhost:8080/swagger-ui/index.html#/
