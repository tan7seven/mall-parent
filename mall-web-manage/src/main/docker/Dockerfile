FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD mall-web-manage-1.0.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="\
              -Duser.timezone=Asia/Shanghai \
              -Dfile.encoding=UTF-8 "
# 配置容器启动后执行的命令
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
