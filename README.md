# Api creacion usuario

## Registrar usuario

### `POST` /rest/v1/user/register

#### Request:
```json
{
	"name": "pedro rodriguez",
	"email": "prod4@mail.com",
	"password": "A23",
	"phones": [
		{
		"number": "69584354",
		"cityCode": "11",
		"countryCode": "54"
		},
		{
		"number": "69584356",
		"cityCode": "11",
		"countryCode": "54"
		},
		{
		"number": "69584353",
		"cityCode": "11",
		"countryCode": "54"
		}
		
	]
}
```

#### Response:
```json
{
    "id": "456675bb-4691-4617-9c01-1cc6ab947f91",
    "created": "2020-02-09T23:58:14.882",
    "modified": "2020-02-09T23:58:14.882",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZWRybyByb2RyaWd1ZXoiLCJleHAiOjE1ODIxNjc0OTR9._xHwD3jHzqvkMOVux2QOrQNw9MuoMCOazUFKxRnnuTH1O-bj_GsnFHVbscvPtqI40vzxKv_Q3lY0PXtF4Oxrvg",
    "last_login": "2020-02-09T23:58:14.882",
    "is_active": true
}
```
## Scripts BD:
#### Estan ubicados en el proyecto en la ruta: $PROJECT_ROOT/src/main/resources/sql-scripts/

## Stack

 * java 1.8
 * spring-boot 2
 * hibernate
 * junit
 * h2
 * spring-security
 * oauth jwt
 
 ## Gradle build
 
Para buildear el proyecto se debe ejecutar
  
  ```bash
  ./gradlew build
  ```

Para ejecutar task de jacoco para reporte de coverage
  
  ```bash
  ./gradlew jacocoTestReport
  ```
 
