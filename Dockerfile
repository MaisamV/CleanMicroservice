# using multistage docker build
# ref: https://docs.docker.com/develop/develop-images/multistage-build/

ARG APP_HOME=/fund
ARG db_url
ARG db_user_name
ARG db_user_pass
ARG db_superuser_name
ARG db_superuser_pass

FROM gradle:6-jdk11-alpine AS BUILDER
ARG APP_HOME
ARG db_url
ARG db_user_name
ARG db_user_pass
ARG db_superuser_name
ARG db_superuser_pass
ENV db_url=$db_url
ENV db_user_name=$db_user_name
ENV db_user_pass=$db_user_pass
ENV db_superuser_name=$db_superuser_name
ENV db_superuser_pass=$db_superuser_pass
WORKDIR $APP_HOME
COPY . .
RUN wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub
RUN apk add glibc-2.34-r0.apk
RUN gradle clean generateProto build -x test fatJar --stacktrace

# ----------------------------------------------------

FROM openjdk:11 AS RUNNER
ARG APP_HOME
ARG db_url
ARG db_user_name
ARG db_user_pass
ARG db_superuser_name
ARG db_superuser_pass
ENV db_url=$db_url
ENV db_user_name=$db_user_name
ENV db_user_pass=$db_user_pass
ENV db_superuser_name=$db_superuser_name
ENV db_superuser_pass=$db_superuser_pass
WORKDIR $APP_HOME
COPY --from=BUILDER $APP_HOME/ConfigCore/build/libs/ConfigCore-1.0-all.jar ./ConfigCore-1.0-all.jar
ENTRYPOINT ["java","-jar","./ConfigCore-1.0-all.jar"]