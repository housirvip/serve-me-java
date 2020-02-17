# serve-me-java

> Build with [SpringBoot](https://spring.io/projects/spring-boot/)

## Tools used

- SpringBoot
- Spring Data Jpa
- Spring Security
- Mysql
- Lombok
- Guava
- Firebase Admin SDK

## How to use

Remember to change the application.yml

The mysql address should be changed to your own.

```shell
# please create a new database name is: serve-me
# and set your environment param
export DB_URL=mysql://{user}@{ipaddress}:{port}/serve-me?characterEncoding=utf8&autoReconnect=true&useSSL=false
export DB_PASSWORD={password}
export GOOGLE_APPLICATION_CREDENTIALS={path}

# for example: 
# DB_URL=mysql://root@localhost:3306/serve-me?characterEncoding=utf8&autoReconnect=true&useSSL=false
# DB_PASSWORD=123456
# GOOGLE_APPLICATION_CREDENTIALS=/path/to/firebase-admin-sdk-credentials.json

{user} is your sql username
{password} is sql password
{ipaddress} is sql address, local machine is localhost
{port} is sql port, usually is 3306
```
