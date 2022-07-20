# Api Exemplo contento método de autenticação por token JWT

- Spring boot 2.7.1 com validation e cache
- Spring Security Core 5.7.2
- Java 11
- jsonwebtoken 0.9.1
- springdoc-openapi 1.6.9


## Gerar o pacote api-forum.jar

- Execute na raiz do projeto

```mvn clean install -Dmaven.test.skip=true```

## Subir a aplicação usando as configurações de prod
- De dentro da pasta target execute:

```java -jar -Dspring.profiles.active=prod -DAPI_FORUM_DATABASE_URL=jdbc:h2:mem:api-forumdb-prod -DFORUM_DATABASE_USERNAME=sa -DFORUM_DATABASE_PASSWORD= -DFORUM_JWT_SECRET=UVpYnhCmlD613fHG0nshrlTVHcooM6a0GDXehTpr api-forum.jar```

## Executar os testes no profile test

```mvn test -Dspring.profiles.active=test -DAPI_FORUM_DATABASE_URL=jdbc:h2:mem:api-forumdb-test -DFORUM_DATABASE_USERNAME=sa -DFORUM_DATABASE_PASSWORD= -DFORUM_JWT_SECRET=UVpYnhCmlD613fHG0nshrlTVHcooM6a0GDXehTes```

## Gerar imagem docker

- Execute na raiz do projeto:

```docker login``` <br/>
```docker build -t <seu_usuario_docker_hub>/api-forum:v1 .```

Como boa prática, geramos também a versão latest

```docker tag <seu_usuario_docker_hub>/api-forum:v1 <seu_usuario_docker_hub>/api-forum:latest```

- Ao finalizar, envie as imagens para o docker hub

```docker push <imagem:v1>``` <br/>
```docker push <imagem:latest>```

Exemplo de como ficará armazenado as imagens no Docker Hub
![image](https://user-images.githubusercontent.com/3687713/179742002-ccba777c-daca-430e-ab01-fca533b56a6e.png)


## Executando a imagem v1 - ambiente de prod pelo container

``` docker run -p 8080:8080 --env ENV=prod --env API_FORUM_DATABASE_URL=jdbc:h2:mem:api-forumdb-prod --env API_FORUM_DATABASE_USERNAME=sa --env API_FORUM_DATABASE_PASSWORD= --env API_FORUM_JWT_SECRET=UVpYnhCmlD613fHG0nshrlTVHcooM6a0GDXehTpr --env PORT=8080 <seu_usuario_docker_hub>/api-forum:v1 ```
