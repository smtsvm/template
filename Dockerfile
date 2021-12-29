FROM dregistry.logo.com.tr:8123/oracle-java:latest
VOLUME /tmp
EXPOSE 8083
ADD target/*.war /app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT exec java -server -XX:+UseCompressedOops -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /app.jar