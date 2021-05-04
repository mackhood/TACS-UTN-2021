FROM node:10

USER root
WORKDIR /app/

COPY ./frontend/src/ /app/src
COPY ./frontend/public/ /app/public
COPY ./frontend/package.json /app

RUN ls

EXPOSE 3000

# Para DEV usar npm install, luego para prd y tst "npm ci"
# npm ci pisa cualquier node_modules existente y verifica que
#ambos package.json y package-lock.json coincidan
RUN npm install

CMD ["npm", "start"]