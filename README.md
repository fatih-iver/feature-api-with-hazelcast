# Feature-Api

Feature-Api helps us do runtime feature toggling. It is implemented by using Spring & Togglz.

## Before Run

```bash
docker run -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```

## Run

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8082
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8083
```

## Toggle Features

Check toggle console at:
<br> http://localhost:8081/togglz-console/index
<br> http://localhost:8082/togglz-console/index
<br> http://localhost:8083/togglz-console/index

## Example Usages

```bash
curl --location --request GET 'http://localhost:8080/is-feature-active?feature=NEW_WEBSITE'

curl --location --request GET 'http://localhost:8080/is-feature-active?feature=NEW_WEBSITE&user=fatih.iver@gmail.com'
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)