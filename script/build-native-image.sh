#!/bin/bash -v
# shellcheck disable=SC2092
# shellcheck disable=SC2034
# shellcheck disable=SC2006
cd ..
filename=springbootbase
suffix=-0.0.1-SNAPSHOT.jar
success=`mvn clean package`
buildPath=./build/libs/
# shellcheck disable=SC1073
# shellcheck disable=SC2086
if   [ -e "./build/libs/""$filename""$suffix" ]; then
  # shellcheck disable=SC2164
  cd $buildPath
  `native-image -jar --no-fallback "$filename""$suffix" $filename `
fi
exit 0