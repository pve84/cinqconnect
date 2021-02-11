echo "Building app"
docker run -it --rm --name maven-project -v "$HOME/.m2":/root/.m2 -v "$(pwd)/code":/usr/src/mymaven -w /usr/src/mymaven amd64/maven:3.6.3-jdk-11-slim  mvn -q clean install
docker build -t cc:capture-the-flag -f ./code/src/docker/Dockerfile .
docker run -d -p 8080:8080 cc:capture-the-flag
echo "Happy hunting!"