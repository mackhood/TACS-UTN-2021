FROM node:10

USER root
#COPY --chown=www-data:www-data?
COPY  frontend /usr/frontend/
WORKDIR /usr/frontend/
RUN npm install

EXPOSE 80

EXPOSE 3000
CMD ["npm", "start"]

RUN /bin/bash
