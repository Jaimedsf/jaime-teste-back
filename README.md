
# Instação do back end

Abra o projeto com sua IDE e instale as dependências.


Rode local

```
mvn spring-boot:run
```
Ou suba a aplicação para ser distribuida dentro de um docker

```
docker build --build-arg JAR_FILE=target/*.jar -t jaime/pefoce .
```

Rode o docker composer para subir o postgres e a aplicação

```
 docker-compose up     
```

Ao acessar o postgres com um DBA, cadastre os tipos de usuários


```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

Caso queira adicionar vários usuários ainda dentro do DBA

```
INSERT INTO clients (nome, email, cpf, renda, telefone, data_criacao)
SELECT
    md5(random()::text),
    substr(md5(random()::text), 0, 20) || '@example.com',
    substring(md5(random()::text) from 1 for 11),
    random() * 10000,
    '(' || substr(md5(random()::text), 0, 2) || ') ' || substring(md5(random()::text) from 1 for 8) || '-' || substring(md5(random()::text) from 9 for 4),
    NOW() - (random() * interval '365 days')
FROM generate_series(1, 50);  -- Gera 50 registros


-- Insira 10 registros de dados aleatórios na tabela cliente
INSERT INTO clients (nome, email, cpf, renda, telefone, data_criacao)
SELECT
    initcap(substring(md5(random()::text) from 1 for 5)) || ' ' || initcap(substring(md5(random()::text) from 6 for 5)),
    substr(md5(random()::text), 0, 5) || '@example.com',
    (
        (floor(random() * 9) + 1)::text || -- Primeiro dígito
        (floor(random() * 10))::text || -- Segundo dígito
        (floor(random() * 10))::text || -- Terceiro dígito
        (floor(random() * 10))::text || -- Quarto dígito
        (floor(random() * 10))::text || -- Quinto dígito
        (floor(random() * 10))::text || -- Sexto dígito
        (floor(random() * 10))::text || -- Sétimo dígito
        (floor(random() * 10))::text || -- Oitavo dígito
        (floor(random() * 10))::text || -- Nono dígito
        (floor(random() * 10))::text ||  -- Décimo dígito
 	(floor(random() * 10))::text  -- Décimo primeiro dígito
    ),
    floor(random() * 10000)::numeric,
    (
        (floor(random() * 10))::text || (floor(random() * 10))::text || (floor(random() * 10))::text ||
        (floor(random() * 10))::text || (floor(random() * 10))::text || (floor(random() * 10))::text ||
        (floor(random() * 10))::text || (floor(random() * 10))::text || (floor(random() * 10))::text ||
        (floor(random() * 10))::text
    ),
    NOW() - (random() * interval '365 days')
FROM generate_series(1, 10);  -- Gera 10 registros
```

Com a aplicação rodando cadastre um administrador em http://localhost:8080/api/auth/signup por POST

```
{
    "username":"admin",
    "email":"admin@admin.com",
    "password": "12345678",
    "role": ["admin"]
}
```

Ou um usuário comum por POST


```
{
    "username":"usu",
    "email":"usu@usu.com",
    "password": "12345678",
    "role": ["user"]
}
```

Logue em http://localhost:8080/api/auth/signin e visualize o token por POST

```
{
"username": "admin",
"password": "12345678"
}
```

Para acessar todos os clientes http://localhost:8080/api/clients por GET necessário usar o token gerado ao se logar. No Headers adicione Authorization com valor Bearer {token}

Cadastrar um cliente em http://localhost:8080/api/clients/add


```
{
    "nome": "teste teste",
    "email": "teste@teste.com",
    "cpf": "123456789",
    "renda": 2550,
    "telefone": "859999999",
    "dataCriacao": "2023-05-19T09:36:00"
}
```

Obter todos os clientes em http://localhost:8080/api/clients. 

Deletar um cliente em http://localhost:8080/api/clients/152. Necessário usar o token gerado de um usuário administrador para deletar no Headers adicione Authorization com valor Bearer {token}




