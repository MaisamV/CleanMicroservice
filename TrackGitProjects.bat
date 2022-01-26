set repoDir=%~dp0
git remote rename origin github
git branch -m base

git remote add saba-template http://scg.otcsaba.ir/m.vahidsafa/microservicetemplate.git
git fetch saba-template
git switch -c saba-base saba-template/master

git branch master
git checkout master
@echo off
set /p repository="Enter Project name in gitlab: "
git remote add origin http://scg.otcsaba.ir/m.vahidsafa/%repository%
git checkout --track origin/master
git push -u origin master
cd ..
ren "%repoDir%" "%repository%"