# Base image
FROM tomcat:9.0.30-jdk8-openjdk
# Argument with a default value.
ARG WAR=api-0.0.2-SNAPSHOT.war
# Copy statement using the argument. 
COPY ./code/target/${WAR} /usr/local/tomcat/webapps/ROOT.war
# Expose does not really so anything to the image. It's more like a guide for the person who will run it.
EXPOSE 8080