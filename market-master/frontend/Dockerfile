FROM node:16

WORKDIR /usr/app
RUN chown -R node:node /usr/app
USER node

ARG NPM_TOKEN
RUN echo "//registry.npmjs.org/:_authToken=$NPM_TOKEN" > .npmrc

COPY --chown=node:node ./ /usr/app
RUN npm install

EXPOSE 3000
CMD ["npm", "run", "dev"]
