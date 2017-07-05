# Download Dependencies
#Add here your private Token from gitlab:
YOURTOKEN="tk3MRhz8TJyjpqMSbHLk"




TOKEN="PRIVATE-TOKEN: "
WGETHEADER="$TOKEN $YOURTOKEN"




#Getting JAXBOpenMath-2.0.jar
cd lib

rm -r -f this.zip
rm -r -f target/

wget --no-check-certificate -O this.zip --header "$WGETHEADER" "https://s3gitlab.paluno.uni-due.de/api/v4/projects/137/jobs/artifacts/master/download?job=run-build"
unzip this.zip

rm -r -f JAXBOpenMath-2.0.jar
cp target/JAXBOpenMath-2.0.jar JAXBOpenMath-2.0.jar


rm -r -f this.zip
rm -r -f target/


#end