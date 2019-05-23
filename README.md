# Music Store API

 API para consultas e vendas de discos.

## Especificações
 
 O serviço deverá disponibilizar uma API REST contendo as seguintes operações:
 
 - Consultar o catálogo de discos de forma paginada, filtrando por gênero e ordenando de forma crescente pelo nome do disco;
 - Consultar o disco pelo seu identificador;
 - Consultar todas as vendas efetuadas de forma paginada, filtrando pelo range de datas (inicial e final) da venda e ordenando de forma decrescente pela data da venda;
 - Consultar uma venda pelo seu identificador;
 - Registrar uma nova venda de discos calculando o valor total de cashback considerando a tabela.
 
 O serviço será alimentado pela base de dados do `spotfy` [`web-api`](https://developer.spotify.com/documentation/web-api/quick-start/)
 
 Cada venda poderá ter 1 ou mais discos selecionados, o cashback deverá ser calculado e armazenado individualmente para cada disco bem como o cashback total da venda.

## Tecnologias

 - [`Spring boot`](https://spring.io) - Framework base para a API
 - [`Flyway`](https://flywaydb.org) - Controle de versão e migração para banco de dados
 - [`MySQL`](https://www.mysql.com) - Banco de dados
 - [`H2`](https://www.h2database.com) - Banco de dados para os testes
 - [`Swagger`](https://swagger.io) - Documentação de API de forma dinâmica
 - [`JPA/Hibernate`](https://hibernate.org/orm/) - Framework para persistencia de dados / ORM
 - [`Docker`](https://www.docker.com) - Executa e gerencia aplicações dentro de invólucros chamados containers
 - [`jUnit5 e Mockito`](https://junit.org/junit5/) - Execução de testes

## Executando com Docker em ambiente de desenvolvimento

 Primeiramente entre na pasta do projeto, agora executaremos as seguintes
instruções:

- A instrução a seguir executará atravéz do [`Maven`](https://maven.apache.org) o `clean` do projeto, logo após o `package`, que executará os testes unitários para garantir a integridade do projeto e para gerar nosso `.jar`, por ultimo será realizado o build do [`Dockerfile`](./Dockerfile) gerando uma imagem no repositório local denominada de `beblue/music-store-api`. Para a realização do build foi utilizado o plugin [`dockerfile-maven-plugin`](https://github.com/spotify/docker-maven-plugin) configurado no [`pom.xml`](./pom.xml) do projeto.

```sh
$ mvn clean package dockerfile:build
```

- Após ter gerado a imagem da API atravéz do `dockerfile:build` iremos montar os containers pelo [`docker-compose`](./docker-compose.yml). O compose preparado irá deixar configurado uma container para o banco de dados e para o `music-store-api`:

```sh
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
 
 
## Documentação dinâmica com o Swagger

  http://{address}:8080/swagger-ui.html
