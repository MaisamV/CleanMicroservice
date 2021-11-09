# Clean Microservice
This project is a modular microservice template using Kotlin, Postgres, Flyway, Jooq and Ktor technologies working together considering clean Architecture rules.

you can easily change any module of data related to data, logic, delivery just with implementing related interfaces using another technology.
In order to use another database just replace jdbc and data sources defined in build.gradle and ConnectionManager.kt 
# Deploy
In order to run this project follow bellow instructions:
1. Define these environmental variables ```fund_db_url```, ```fund_db_superuser_name```, ```fund_db_superuser_pass```, ```fund_db_user_name```, ```fund_db_user_pass```
2. RESTART your terminal or IDE in order to load updated environment variable correctly.
3. ```git clone https://github.com/MaisamV/CleanMicroservice.git```
4. ```cd ./CleanMicroservice```
5. (If you didn't run flyway scripts already)```./gradlew flywayMigrate```
6. ```./gradlew build ConfigCore:run```