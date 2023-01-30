#!/bin/zsh
# This script is used to start the server
if [ ! -e "../target/springbootbase-0.0.1-SNAPSHOT.jar" ]; then
    echo "Please run the build.sh script first"
    exit 1
fi

# copy the jar file to the current directory
cp ../target/springbootbase-0.0.1-SNAPSHOT.jar .

# start the server
`docker-compose -f ./docker-compose-shadowfiend.yml up -d`

# remove the jar file
rm springbootbase-0.0.1-SNAPSHOT.jar