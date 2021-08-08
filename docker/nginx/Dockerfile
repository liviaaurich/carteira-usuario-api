# Criar a minha própria imagem do nginx com a customização do nginx.conf
FROM nginx:1.19.6-alpine

# Comando para remover o arquivo e conf padrão do nginx, para então substituí-lo pelo o meu
RUN rm /etc/nginx/conf.d/default.conf

# Copio o arquivo que criei para o local do outro que removi
COPY nginx.conf /etc/nginx/conf.d/default.conf