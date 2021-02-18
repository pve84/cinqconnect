        ____   _____ _____ _   _  ____      _____ ____  _   _ _   _ ______ _____ _______  ____   
       / / /  / ____|_   _| \ | |/ __ \    / ____/ __ \| \ | | \ | |  ____/ ____|__   __| \ \ \  
      / / /  | |      | | |  \| | |  | |  | |   | |  | |  \| |  \| | |__ | |       | |     \ \ \ 
     < < <   | |      | | | . ` | |  | |  | |   | |  | | . ` | . ` |  __|| |       | |      > > >
      \ \ \  | |____ _| |_| |\  | |__| |  | |___| |__| | |\  | |\  | |___| |____   | |     / / / 
       \_\_\  \_____|_____|_| \_|\___\_\   \_____\____/|_| \_|_| \_|______\_____|  |_|    /_/_/  
# Containerization
Welcome to the first edition of CINQ Connect! During this course you will learn the basics of building images and a bit of troubleshooting. 

## Prerequisites
- [Visual Studio Code](https://code.visualstudio.com/download)
- Visual Studio Code [Remote Development extension](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.vscode-remote-extensionpack)

The course is divided into 3 parts.
1. Builing images
    * Exploring the Dockerfile
    * Building the image
    * Optimizing the Dockerfile
2. Troubleshooting
    * Find out what's wrong with the Dockerfiles
    * Find out what's wrong with the running container 
3. Capture the flag!
    * There are 2 ASCII art flag hidden somewhere in the container. It's up to you to find them. Happy hunting!

> **The application:**  
> The application running in the container is a simple Java Spring Boot API example which is slightly altered for this course. The application is used to verify if images is correct and the container is running. 

## Setup your environment
> Be sure to have Visual Studio Code and the Remote developenment extension installed
- Open VS Code
- Press `F1`
- Type `connect to` and select `Remote-SSH Connect to Host...`
- Enter `cc@[IP]`
- Select `Continue`
- Enter password
- Click `Open Folder`
- Select `cinqconnect`
- Click `Ok`
> If you do not have a Terminal windows at the bottom of your editor open a new one by using the shotcut ``Control + ` ``

## 1. Building images
Go to the directory `building-images`. There are 3 Dockerfiles and 1 directory called `code`

### Dockerfile
This is a simple setup to create an image based on Tomcat which will run a default package called ROOT.war.  
As can be seen at line 6 a file will be copied to the image. We need to provide this file by building the application first.

#### Inspect the Dockerfile
Open `Dockerfile` and read the comments.

#### Building the application  
To build the application go to the terminal window and `cd` into the `building-images` directory and enter the `mvn` command to start the build.

<details><summary>spoiler</summary>

```shell
cd building-images
mvn clean install -f ./code
```

</details>

This will take a while but the result is a file called `api-0.0.2-SNAPSHOT.war` which is located under `./code/target` 

#### Building the image
Now we can build the image. To start the build use `docker build`. Use the tag `cc:manual` and the current path as build context.

<details><summary>spoiler</summary>

```shell
docker build -t cc:manual .
``` 

</details>

#### Start a new container using the new image
Use `docker run` to start a new container and be sure to export port `8080`.

<details><summary>spoiler</summary>

```shell
docker run -p 8080:8080 cc:manual
``` 

</details>

To validate if the application is running correct you can open a Browser and go to http://[IP]:8080/api/v1/int. It should return a random integer.  

> You console is now tied to the container. To exit the container (and kill it) press `ctrl + c`

Kill the container to continue `ctrl + c` 

### Dockerfile.build
This Dockerfile will also build the application. It does this by installing Maven into the (temporary) image.

#### Inspect the Dockerfile
Open `Dockerfile.build` and read the comments.

#### Building the image
Build using `docker build` use the tag `cc:build` and use the current path as build context. Be sure to point docker to the `Dockerfile.build` Dockerfile.

<details><summary>spoiler</summary>

```shell
docker build -t cc:build -f Dockerfile.build .
```

</details>

To verify if the image works run the image just like before but use the tag `cc:build`.  

This takes away the need to manually build the application but it's a pretty inefficient Dockerfile. Now every image we create will contain Maven which we don't need to run our app. The image is very big which you can check by running `docker images cc:build`

### Dockerfile.buildstages
This Dockerfile uses stages to build the application and build the final image. This will greatly reduce the image size and clutter inside. The first stage uses a Maven image provided by Apache Maven. This will speed up te build process.

#### Inspect the Dockerfile
Open `Dockerfile.buildstages` and read the comments.

#### Building the image
Build using `docker build` use the tag `cc:buildstages` and use the current path as build context. Be sure to point docker to the `Dockerfile.buildstages` Dockerfile.  

To verify if the image works run the image just like before but use the tag `cc:buildstages`. 

Now, if you check the size of the image it will be a lot smaller `docker images cc:buildstages`

## Troubleshooting
The directory `troubleshooting` contains more or less the same Dockerfiles as `building-images` except for some small issues. Try to build the images and find out what's wrong with them en verify if the application is running correctly by using your browser.
> Tip: If you don't see the CINQ Connect logo the application is not copied succesfully to the image

> You can also use `curl http://localhost:8080/api/v1/int` in a second terminal.

<details><summary>spoiler</summary>
  
    - 1945
    - Docker: Where am I?
    - I run Arch btw
    - Get of the stage!

</details>

## Capture the flag!
     _    _.--.____.--._
    ( )=.-":;:;:;;':;:;:;"-._
     \\\:;:;:;:;:;;:;::;:;:;:\
      \\\:;:;:;:;:;;:;:;:;:;:;\
       \\\:;::;:;:;:;:;::;:;:;:\
        \\\:;:;:;:;:;;:;::;:;:;:\
         \\\:;::;:;:;:;:;::;:;:;:\
          \\\;;:;:_:--:_:_:--:_;:;\
           \\\_.-"             "-._\
            \\
             \\
              \\
               \\
                \\
                 \\
HARRRRR There be two flags hidden! Go and find them!  

Go the the directory `capture-the-flag` and run `./startapp.sh`

<details><summary>spoiler</summary>

    - I will bash that coconut out of the tree!
    - Arrrr what are those random numbers?
    - I'd rather look at me plunder instead of code!

</details>
