set repoDir=%~dp0
git remote rename origin github
git branch -m base

git remote add saba-template http://scg.otcsaba.ir/m.vahidsafa/microservicetemplate.git
git checkout --track saba-template/master
git checkout master
git branch -m saba-base
git branch saba-base --set-upstream-to saba-template/master

git branch develop
git checkout develop
@echo off
set /p repository="Enter Project name in gitlab: "
git remote add origin http://scg.otcsaba.ir/m.vahidsafa/%repository%
git push -u origin develop
cd ..
ren "%repoDir%" "%repository%"