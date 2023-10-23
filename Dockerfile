# This image must be built in the context of the repository root.

FROM chaquopy-app

COPY demo demo
COPY apps/jupyter apps/jupyter

RUN echo "sdk.dir=$(pwd)/android-sdk" > apps/jupyter/local.properties
RUN apps/jupyter/gradlew -p apps/jupyter app:assembleDebug
