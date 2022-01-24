# using multistage docker build
# ref: https://docs.docker.com/develop/develop-images/multistage-build/

ARG project_name
ARG db_url
ARG db_user_name
ARG db_user_pass
ARG db_superuser_name
ARG db_superuser_pass

FROM gradle:6-jdk11-alpine AS BUILDER
ARG project_name
ARG db_url
ARG db_user_name
ARG db_user_pass
ARG db_superuser_name
ARG db_superuser_pass
ENV project_name=$project_name
ENV db_url=$db_url
ENV db_user_name=$db_user_name
ENV db_user_pass=$db_user_pass
ENV db_superuser_name=$db_superuser_name
ENV db_superuser_pass=$db_superuser_pass
WORKDIR $project_name
COPY . .
COPY ./sgerrand.rsa.pub /etc/apk/keys/sgerrand.rsa.pub
RUN apk add glibc-2.34-r0.apk
RUN gradle clean generateProto build -x test fatJar --stacktrace

# ----------------------------------------------------

FROM openjdk:11 AS RUNNER
ARG project_name
ARG db_url
ARG db_user_name
ARG db_user_pass
ARG db_superuser_name
ARG db_superuser_pass
ENV project_name=$project_name
ENV db_url=$db_url
ENV db_user_name=$db_user_name
ENV db_user_pass=$db_user_pass
ENV db_superuser_name=$db_superuser_name
ENV db_superuser_pass=$db_superuser_pass
WORKDIR $project_name
COPY --from=BUILDER $project_name/ConfigCore/build/libs/ConfigCore-1.0-all.jar ./ConfigCore-1.0-all.jar
EXPOSE 8089
EXPOSE 50051
ENTRYPOINT ["java","-jar","./ConfigCore-1.0-all.jar"]