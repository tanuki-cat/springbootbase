#!/bin/bash -v
# shellcheck disable=SC2092
# shellcheck disable=SC2034
# shellcheck disable=SC2006
cd ..
filename=springbootbase
suffix=0.0.1-SNAPSHOT.jar
success=`mvn clean package`
# shellcheck disable=SC1073
# shellcheck disable=SC2086
if   [ -e "./target/""$filename-""$suffix" ]; then
  # shellcheck disable=SC2164
  cd target
  `native-image -jar "$filename-""$suffix" $filename`
fi
exit 0