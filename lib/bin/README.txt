Please find steps below for 'Using JDBC without Spring'

Pre-requisite steps:
1. Database: Apache Derby
2. Install Apache Derby and set environment variables
   https://db.apache.org/derby/
   https://builds.apache.org/job/Derby-docs/lastSuccessfulBuild/artifact/trunk/out/getstart/index.html
   
    Operating System	| Command
	UNIX (Korn Shell)	|  export DERBY_HOME=/opt/Derby_10
	Windows	            |  set DERBY_HOME=c:\Derby_10
	
	Operating System	| Command
	UNIX (Korn Shell)	| export PATH="$DERBY_HOME/bin:$PATH"
	Windows	            | set PATH=%DERBY_HOME%\bin;%PATH%
	
3. Go to '$DERBY_HOME/bin' and run startNetworkServer.bat/startNetworkServer.sh
4. Open Command prompt and connect to Derby sever using 'ij' client
5. Create table and insert row:

    >ij.bat
	ij version 10.13
	ij> connect 'jdbc:derby://localhost:1527/myDB;create=true';
    ij> CREATE TABLE Circle
        > (id Integer,
        > name char(50));
          0 rows inserted/updated/deleted
        ij> select *from Circle;
		ID         |NAME
		--------------------------------------------------------------

		0 rows selected