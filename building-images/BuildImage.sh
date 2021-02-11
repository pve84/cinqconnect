# Build the project by running the maven image with the Maven cache and project directories mounted to the host. The result will be stored on the host and the Maven cache will be reused.
docker run -it --rm --name maven-project -v "$HOME/.m2":/root/.m2 -v "$(pwd)/code":/usr/src/mymaven -w /usr/src/mymaven amd64/maven:3.6.3-jdk-11-slim mvn clean install
docker build -t cc:quick -f Dockerfile .