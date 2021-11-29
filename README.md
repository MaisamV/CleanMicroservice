# Clean Microservice
This project is a modular microservice template using Kotlin, Postgres, Flyway, Jooq and Ktor technologies working together considering clean Architecture rules.

you can easily change any module of data related to data, logic, delivery just with implementing related interfaces using another technology.
In order to use another database just replace jdbc and data sources defined in build.gradle and ConnectionManager.kt 
# Deploy
In order to run this project follow bellow instructions:
1. Define these environmental variables ```db_url```, ```db_superuser_name```, ```db_superuser_pass```, ```db_user_name```, ```db_user_pass```
2. RESTART your terminal or IDE in order to load updated environment variable correctly.
3. ```git clone http://git.sabaolgoo.ir/m.vahidsafa/microservicetemplate.git```
4. ```cd ./microservicetemplate```
5. (If you didn't run flyway scripts already)```./gradlew flywayMigrate```
### Gradle
6. ```./gradlew clean generateProto build ConfigCore:run```
### Java
6. ```./gradlew clean generateProto build fatJar```
7. ```java -jar ./ConfigCore/build/libs/ConfigCore-1.0-all.jar```
### Docker
6. ```docker build -t clean_microservice . --build-arg db_url --build-arg db_user_name --build-arg db_user_pass --network=host```
7. ```docker run -p 8089:8089 -p 50051:50051 clean_microservice```