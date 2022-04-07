# Before starting application run docker container:
```
docker run \
--detach \
--name=docker_edu_mysql \
--env="MYSQL_ROOT_PASSWORD=toor" \
--env="MYSQL_DATABASE=baza" \
--publish 3306:3306 \
mysql
```