git clone https://github.com/MaisamV/CleanMicroservice.git
cd ./CleanMicroservice
git remote rename origin github
git branch -m base
git remote set-url --add --push github https://github.com/MaisamV/CleanMicroservice.git
git remote set-url --add --push github http://scg.otcsaba.ir/m.vahidsafa/microservicetemplate.git
git branch master
git checkout master
@echo off
set /p repository="Enter Project name in gitlab: "
git remote add origin http://scg.otcsaba.ir/m.vahidsafa/%repository%
git push -u origin master
cd ..
ren CleanMicroservice %repository%