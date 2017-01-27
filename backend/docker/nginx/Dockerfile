FROM nginx
RUN mkdir /etc/nginx/ssl
RUN openssl req -new -newkey rsa:4096 -days 365 -nodes -x509 -subj "/C=BR/ST=Bahia/L=Salvador/O=Dis/CN=localhost" -keyout /etc/nginx/ssl/nginx.key  -out /etc/nginx/ssl/nginx.crt

COPY etc/default.conf /etc/nginx/conf.d/
