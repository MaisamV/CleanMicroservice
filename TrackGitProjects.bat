set repoDir=%~dp0
git remote rename origin github
git branch -m base

git remote add saba-template http://scg.otcsaba.ir/m.vahidsafa/microservicetemplate.git
git fetch saba-template
git switch -c saba-base saba-template/master

@echo off
set /p repository="Enter Project name in gitlab: "
git remote add origin http://scg.otcsaba.ir/m.vahidsafa/%repository%
git fetch origin
git switch -c master origin/master

cd ..
ren "%repoDir%" "%repository%"