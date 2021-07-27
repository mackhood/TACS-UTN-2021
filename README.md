# TACS-UTN-2021

## ¿Cómo levantar el TP?

### Docker: Backend + db 

`$ docker-compose up --build -d`

Al ejecutar este comando, se buildea el backend + la base de datos y queda accesible por su ip publica. Se puede acceder a la ip publica mediante el comando

`docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' container_name_or_id
`

Luego desde el explorador o postman:

`ip_publica:8080`

### Entorno local sin Docker

En el directorio raiz del proyecto se encuentra el script `startup.sh` inicia tanto el backend en el puerto 8080, como el frontend en el puerto 3000.
Para conectarse a la base de datos mysql, configurar `spring.datasource.url` a gusto. Puede ser mysql local en una computadora, o el link a la instancia de ClearDB de Heroku que dejé configurada.

Para matar los procesos que inicia este script:

`lsof -t -i:3000` o bien `lsof -t -i:8080` para obtener los respectivos PIDs y luego
`kill -9 PID` para matar el proceso que ocupa el puerto  

## Consigna

https://docs.google.com/document/u/1/d/e/2PACX-1vSDeXS8A44GMMKxL47FTspYC6_4BXiWP2_lwo2Oiy4P7oRXORfseOdQ9F3K8vZ_xyHNPf6euMP1wEIV/pub
