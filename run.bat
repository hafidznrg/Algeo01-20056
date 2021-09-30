@echo off

cd %CD%/src/

echo Compiling Program...
javac -d ../bin ./*.java

echo Running program...
echo This program is developed by
echo 1. Fikri Khoiron Fadhila             13520056
echo 2. Malik Akbar Hashemi Rafsanjani    13520105
echo 3. Hafidz Nur Rahman Ghozali         13520117
echo --------------------------------
cd ..
cd %CD%/bin
java Main
echo --------------------------------
cd ..
PAUSE