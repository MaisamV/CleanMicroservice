set repoDir=%~dp0
git remote rename origin tempRemote
git branch -m temp

git remote add github https://github.com/MaisamV/CleanMicroservice.git
git fetch github
git switch -c base github/master

git checkout base
git branch -d temp
git remote remove tempRemote

git remote add saba-template http://scg.otcsaba.ir/m.vahidsafa/microservicetemplate.git
git fetch saba-template
git switch -c saba-base saba-template/master

@echo off
set /p repository="Enter Project name in gitlab: "
cd ..
ren "%repoDir%" "%repository%"
cd "%repository%"

git remote add origin http://scg.otcsaba.ir/m.vahidsafa/%repository%
git fetch origin
git switch -c master origin/master