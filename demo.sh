#!/bin/bash
#

mkdir build || echo dir build exists

if [ "${BUILD_CONFIG}" = "" ]; then
    BUILD_CONFIG='Release'
fi

./gradlew build

cd functional-test
./gradlew clean test
