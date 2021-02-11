FROM tomcat:9.0.30-jdk8-openjdk
ARG war=api-0.0.2-SNAPSHOT.war
COPY ./code/target/${WAR} /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080