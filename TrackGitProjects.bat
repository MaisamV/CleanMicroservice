set repoDir=%~dp0
git remote rename origin tempRemote
git branch -m temp

git remote add github https://github.com/MaisamV/CleanMicroservice.git
git fetch github master:base
git branch --set-upstream-to=github/master base

git checkout base
git branch -d temp
git remote remove tempRemote

git remote add saba-template http://scg.otcsaba.ir/m.vahidsafa/microservicetemplate.git
git fetch saba-template master:saba-base
git branch --set-upstream-to=saba-template/master saba-base

@echo off
set /p repository="Enter Project name in gitlab: "
git remote add origin http://scg.otcsaba.ir/m.vahidsafa/%repository%
git fetch origin master:master
git branch --set-upstream-to=origin/master master
git checkout master
cd ..
ren "%repoDir%" "%repository%"