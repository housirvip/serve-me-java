# serve-me-java

> Build with [SpringBoot](https://spring.io/projects/spring-boot/)

## How to use

Remember to change the application.yml

The mysql address should be changed to your own.

```shell
# please create a new database name is: serve-me
# and set your environment param
export CLEARDB_DATABASE_URL=mysql://{user}:{password}@{ipaddress}:{port}/serve-me?characterEncoding=utf8&autoReconnect=true&useSSL=false

# for example: CLEARDB_DATABASE_URL=mysql://root:housirvip@localhost:3306/serve-me?characterEncoding=utf8&autoReconnect=true&useSSL=false
{user} is your sql username
{password} is sql password
{ipaddress} is sql address, local machine is localhost
{port} is sql port, usually is 3306
```
