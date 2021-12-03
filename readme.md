- Set up docker:
  `docker run --name mariadb_komplex -e MYSQL_ROOT_PASSWORD=mypass -p 3306:3306 -d docker.io/library/mariadb:10.5`

- Set up profile in IntelliJ for running with the dev-version of application.properties.

    1. Run

    2. Edit configuration

    3. Add new `Spring Boot` configuration via + in top left corner

    4. Insert in `VM Options`

       `-Dspring.profiles.active=dev`

    5. Run program with your new profile
