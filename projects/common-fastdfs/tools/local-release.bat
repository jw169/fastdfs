@echo off
echo [INFO] build and install modules.
cd ..\
call mvn clean deploy -P local-release
pause
