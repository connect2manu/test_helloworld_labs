if test ! -d database
then
        mkdir database
fi
if test -d database
then
        echo Removing database files...
        rm -r database/test.*
fi
echo Starting database engine...
cd database/
java -classpath ../lib/hsqldb.jar org.hsqldb.Server
