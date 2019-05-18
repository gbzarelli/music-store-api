# Music Store API

 API para consultas e vendas de discos.

## Especificações
 
 O serviço deverá disponibilizar uma API REST contendo as seguintes operações:
 
 - Consultar o catálogo de discos de forma paginada, filtrando por gênero e ordenando de forma crescente pelo nome do disco;
 - Consultar o disco pelo seu identificador;
 - Consultar todas as vendas efetuadas de forma paginada, filtrando pelo range de datas (inicial e final) da venda e ordenando de forma decrescente pela data da venda;
 - Consultar uma venda pelo seu identificador;
 - Registrar uma nova venda de discos calculando o valor total de cashback considerando a tabela.
 
 O serviço será alimentado pela base de dados do `spotfy` [API](https://developer.spotify.com/documentation/web-api/quick-start/)
 
 Cada venda poderá ter 1 ou mais discos selecionados, o cashback deverá ser calculado e armazenado individualmente para cada disco bem como o cashback total da venda.

## Tecnologias

 - `Spring boot` - Framework base para a API
 - `Flyway` - Controle de versão e migração para banco de dados
 - `MySQL` - Banco de dados
 - `H2` - Banco de dados para os testes
 - `Swagger` - Documentação de API de forma dinâmica
 - `JPA/Hibernate` - Framework para persistencia de dados / ORM
 - `Docker` - Executa e gerencia aplicações dentro de invólucros chamados containers
 - `jUnit5 e Mockito` - Execução de testes

## Executando com Docker em ambiente de desenvolvimento

 Primeiramente entre na pasta do projeto, agora executaremos as seguintes
instruções:

- A instrução a seguir executará atravéz do `Maven` o `clean` do projeto, logo após o `package`, que executará os testes unitários para garantir a integridade do projeto e para gerar nosso `.jar`, por ultimo será realizado o build da imagem do `Docker` configurado pelo plugin [`dockerfile-maven-plugin`](https://github.com/spotify/docker-maven-plugin) no [`pom.xml`](./pom.xml)

```sh
$ mvn clean package dockerfile:build
```

- Após ter gerado a imagem do `Docker` atravéz do `dockerfile:build` basta executarmos com o `docker-compose`, ele irá montar nossos containers e deixa-los pronto para uso:

```sh
$ docker-compose up
```

## Documentação dinâmica com o Swagger

   http://localhost:8080/swagger-ui.html
   

# PROJETO EM DESENVOLVIMENTO...
   
