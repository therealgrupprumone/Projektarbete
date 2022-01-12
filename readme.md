![CI Status](https://github.com//therealgrupprumone/Projektarbete/actions/workflows/maven.yml/badge.svg)

- Set up docker:
  `docker run --name mariadb_komplex -e MYSQL_ROOT_PASSWORD=mypass -p 3306:3306 -d docker.io/library/mariadb:10.5`

- Set up profile in IntelliJ for running with the dev-version of application.properties.

    1. Run

    2. Edit configuration

    3. Add new `Spring Boot` configuration via + in top left corner

    4. Insert in `VM Options`

       `-Dspring.profiles.active=dev`

    5. Run program with your new profile


**Endpoints**

```
GET

all users:
api/v1/users

user by ID:
api/v1/users/{id}
QueryParameter = id;

all roles:
api/v1/roles

all feeds:
api/v1/feeds

feed by ID:
api/v1/feeds/{id}
QueryParameter = id;

all messages:
api/v1/messages

```

```
POST

/api/v1/users
 
JSON-body:
  { 
	"username": "Jannis",
	"password" : "hej123",
	"email": "jannis@test.se"
}
/api/v1/messages
 
JSON-body:
  { 
  "chatMessage": "fmessage message",
  "feedId": 1
}

```

```
DELETE
api/v1/users/{id}
QueryParameter = id;

```