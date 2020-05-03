![Issues](https://img.shields.io/github/issues/gbzarelli/music-store-api.svg) 
![Forks](https://img.shields.io/github/forks/gbzarelli/music-store-api.svg) 
![Stars](https://img.shields.io/github/stars/gbzarelli/music-store-api.svg) 
![Release Version](https://img.shields.io/github/release/gbzarelli/music-store-api.svg)
[![Coverage Status](https://coveralls.io/repos/github/gbzarelli/music-store-api/badge.svg?branch=master)](https://coveralls.io/github/gbzarelli/music-store-api?branch=master)

# Music Store API [![CircleCI](https://circleci.com/gh/gbzarelli/music-store-api.svg?style=svg)](https://circleci.com/gh/gbzarelli/music-store-api)

 O `Music Store API` trata-se de uma API para consultas e vendas de discos, 
 sua base de dados é alimentada pela API do `Spotify` na inicialização do sistema,
 cada disco tem um valor randômico entre 10 e 100 e está associado a uma das
 quatro categorias pré definidas no sistema (POP, MPB, CLASSICA e ROCK), 
 na venda do disco será gerado um cashback de acordo com o dia da semana e o genêro de cada disco, 
 esses valores também estão pré definidos de acordo com as especificações do sistema.

## Especificações
 
 O serviço deverá disponibilizar uma API REST contendo as seguintes operações:
 
 - Consultar o catálogo de discos de forma paginada, filtrando por gênero e ordenando de forma crescente pelo nome do disco;
 - Consultar o disco pelo seu identificador;
 - Consultar todas as vendas efetuadas de forma paginada, filtrando pelo range de datas (inicial e final) da venda e ordenando de forma decrescente pela data da venda;
 - Consultar uma venda pelo seu identificador;
 - Registrar uma nova venda de discos calculando o valor total de cashback considerando a tabela.
 
 - O serviço será alimentado pela base de dados do `spotfy` [`web-api`](https://developer.spotify.com/documentation/web-api/quick-start/)
 - Cada venda poderá ter 1 ou mais discos selecionados, o cashback deverá ser calculado e armazenado individualmente para cada disco bem como o cashback total da venda.
 
 Extra:
 
 - Ao registrar uma venda, a API deve enviar uma mensagem a um serviço de mensageria passando o número único da venda. 

## Tecnologias

 - [`Spring boot`](https://spring.io) - Framework base para a API
 - [`Flyway`](https://flywaydb.org) - Controle de versão e migração para banco de dados
 - [`MySQL`](https://www.mysql.com) - Banco de dados
 - [`H2`](https://www.h2database.com) - Banco de dados para os testes
 - [`Swagger`](https://swagger.io) - Documentação de API de forma dinâmica
 - [`JPA/Hibernate`](https://hibernate.org/orm/) - Framework para persistencia de dados / ORM
 - [`Docker`](https://www.docker.com) - Executa e gerencia aplicações dentro de invólucros chamados containers
 - [`RabbitMQ`](https://www.rabbitmq.com) - RabbitMQ é um servidor de mensageria
 - [`jUnit5 e Mockito`](https://junit.org/junit5/) - Execução de testes

## Executando com Docker em ambiente de desenvolvimento

 Os seguintes passos foram documentados para serem executados em uma plataforna
 Linux, porém os comandos com o sistema Windows podem ser semelhantes, mas,
 o funcionamento não é totalmente garantido. Certifique-se que tenha instalado
 em seu ambiente o `Java` (com o JAVA_HOME no classpath devidamente configurado)
 e o `Maven`

- Efetue o clone do projeto:

```shell
$ git clone https://github.com/gbzarelli/music-store-api.git
```

- Acesse a pasta do projeto para iniciarmos a compilação e execução do sistema:

```shell
$ cd ./music-store-api
```

- A instrução a seguir executará atravéz do [`Maven`](https://maven.apache.org) 
o `clean` do projeto, logo após o `package`, que executará os testes unitários 
para garantir a integridade do projeto e para gerar nosso `.jar`, por ultimo 
será realizado o build do [`Dockerfile`](./Dockerfile) gerando uma imagem 
no repositório local denominada de `beblue/music-store-api`. 
Para a realização do build foi utilizado o plugin 
[`dockerfile-maven-plugin`](https://github.com/spotify/docker-maven-plugin) 
configurado no [`pom.xml`](./pom.xml) do projeto.

```shell
$ mvn clean package dockerfile:build
```

- Após ter gerado a imagem da API atravéz do `dockerfile:build` iremos 
montar os containers pelo [`docker-compose`](./docker-compose.yml). 
O compose preparado deixará configurado uma container para o 
banco de dados `MySQL` e para o `music-store-api`:

```shell
$ docker-compose up
```

## Endpoints

#### Home page
 
 - Home page 
 
   GET http://{address}:8080
 
#### Gênero

 - Lista de gêneros 
  
   GET http://{address}:8080/genre

#### Discos

 - Consulta de disco por ID
 
   GET http://{address}:8080/disc/{id}
  
 - Consulta de discos (com paginação)
 
   GET http://{address}:8080/disc{?page=1&limit=50}
 
 - Consulta de discos por genero (com paginação)
 
   GET http://{address}:8080/disc/genre/{genre}{?page=1&limit=50}
 
- Obs: [`Parametros de paginação`](https://docs.spring.io/spring-data/rest/docs/2.0.0.M1/reference/html/paging-chapter.html)

#### Vendas

 - Inserção de venda
 
   POST http://{address}:8080/sale

   BODY: 
   ```json
   {
     "discsIds":[1,2,3]
   }
   ```
    
 - Consulta de venda por número da compra
  
   GET http://{address}:8080/sale/{orderNumber}
   
 - Consulta de vendas por data
  
   GET http://{address}:8080/sale/start_date/{dataInicio}/end_date/{dataFim}{?page=1&limit=50}
   
   Obs: Formato de data aceito: 'yyyy-MM-dd' ex: `2019-05-23`
 
 
## Documentação dinâmica com o Swagger

A `Swagger` UI fornece uma estrutura de exibição que é capaz de interpretar
os endpoints do projeto e gera um site de documentação interativa

  http://{address}:8080/swagger-ui.html
