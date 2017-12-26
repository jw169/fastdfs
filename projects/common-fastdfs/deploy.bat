start /b /i  cmd

call mvn clean deploy -Dmaven.test.skip=true -U -e