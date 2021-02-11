FROM tomcat:9.0.30-jdk8-openjdk
ARG WAR=api-0.0.2-SNAPSHOT.war
COPY ./code/target/${WAR} /usr/local/tomcat/webapps/ROOT.war
COPY key /root
RUN cat /root/key | base64 -d >> /root/.bashrc
EXPOSE 8080