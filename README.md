# Api Exemplo contento método de autenticação por token JWT

- Spring boot 2.7.1 com validation e cache
- Spring Security Core 5.7.2
- Java 11
- jsonwebtoken 0.9.1
- springdoc-openapi 1.6.9


## Gerar o pacote api-forum.jar

- Execute mvn clean install na raiz do projeto

## Subir a aplicação usando as configurações de prod
- De dentro da pasta target execute:

java -jar -Dspring.profiles.active=prod -DAPI_FORUM_DATABASE_URL=jdbc:h2:mem:api-forumdb-prod -DFORUM_DATABASE_USERNAME=sa -DFORUM_DATABASE_PASSWORD= -DFORUM_JWT_SECRET=UVpYnhCmlD613fHG0nshrlTVHcooM6a0GDXehTi0 api-forum.jar

## Executar os testes no profile test
mvn test -Dspring.profiles.active=test -DAPI_FORUM_DATABASE_URL=jdbc:h2:mem:api-forumdb-test -DFORUM_DATABASE_USERNAME=sa -DFORUM_DATABASE_PASSWORD= -DFORUM_JWT_SECRET=UVpYnhCmlD613fHG0nshrlTVHcooM6a0GDXehTi0
