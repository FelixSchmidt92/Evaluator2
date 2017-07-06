powershell -nologo "Invoke-WebRequest -Uri "https://s3gitlab.paluno.uni-due.de/api/v4/projects/137/jobs/artifacts/master/download?job=run-build\"&\"private_token=-9pHt86dLHfPw-uyp4f2 -OutFile lib\this.zip

powershell -nologo "Expand-Archive -Path lib\this.zip -DestinationPath lib"
%powershell -nologe "& { Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('lib\this.zip', 'lib'); }"  For Powershell 3 or higher

xcopy /Y /F /S lib\target\JAXBOpenMath-2.0.jar lib

rmdir /S /Q lib\target

del lib\this.zip