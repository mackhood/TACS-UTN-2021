#Si tenes el puerto 8080 (para el be) o 3000 (para el fe)
# podes matar el proceso que esté ocupándolo con los siguientes comandos:
#lsof -t -i:3000 lsof -t -i:8080 para obtener los respectivos PIDs
#kill -9 PID para matar el proceso que ocupa el puerto
cd Backend
mvn package -DskipTests=true
mvn spring-boot:run &
cd ../Frontend/frontend
npm install
npm start &
