FROM sior/openjdk-8
MAINTAINER yuyang<yuyang@capinfo.com.cn>
WORKDIR /opt
ARG JAR_FILE
ARG JAR_VERSION
COPY ${JAR_FILE} app.jar
#COPY --from=hengyunabc/arthas:latest /opt/arthas arthas
RUN sh -c 'touch /app.jar'
VOLUME /opt/logs
#EXPOSE 80
ENV IMAGE_VERSION="${JAR_VERSION}" \
    JAVA_OPTS="-XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m -Xmn256m -XX:SurvivorRatio=2 -Xss256k"
ENTRYPOINT ["/sbin/tini", "--", "sh", "-c", "java -server $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar","--spring.profiles.active=prod" ]
