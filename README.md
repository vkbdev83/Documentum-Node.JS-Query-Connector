# Documentum-Node.JS-Query-Connector

This connector could be used within your node.js(Angular / express , etc)  application for execution of Documentum READ query and retrieve the data in JSON format.

Source Code of the Connector is available in this repository for reference. The connector is already uploaded as module in npm and ready to use.

Documentum DFC provides collection of Java APIs for accessing Documentum capabilities.

Similar to DFC, a new node module (library) written in Java Script is developed for performing the DQL read capabilities of the Documentum.  This module could be used within your node.js application for execution of Documentum READ query and retrieve the data in JSON format.

Pre-requisite.

Below Documentum libraries should be placed in dependencies/lib 
  
    1. aspectjrt-1.8.6.jar
    2. commons-lang-2.4.jar
    3. commons-lang3-3.7.jar
    4. dfc-7.3.jar
    5. jackson-annotations-2.8.8.jar
    6. jackson-core-2.7.4
    7. jackson-databind-2.7.4.jar
    8. json-20180130.jar
    9. log4j-1.2.13.jar


The below are the steps required to setup the Documentum node connector within your node.js application

1.	Download and Install the Node.js from the https://nodejs.org/en/download/

2.	Install the Python 2.7 & VC++ using the below npm command from the command prompt

    CMD > npm install --global --production windows-build-tools

3.	Setup a new node.js Express project  in Eclipse using the http://www.nodeclipse.org/usage

4.	Using Command prompt, navigate to the Project setup location and execute the below command for adding Documentum Node module as dependency to your application.
     CMD > npm i dctm-query
     
5.	Using Windows Explorer , navigate to <Project Setup Location>/node_modules/dctm-query/dependencies/config

      a.	Edit the dfc.properties to have the Docbroker Host name/port & Global Registry Repository details.
      b.	Edit the dctm-prop.json to have the Repository name ,
      c.	User name and password for connecting to the Repository 


6.	Now we can use the Documentum node module within your application, below is the code snippet for using the module within your node js application.   

//Initializing the Documentum node module.

  var query = require('dctm-query');
  
/* Calling the Function with the below parameters 
Param1 – DQL to be executed
Param2 – JavaScript function(Call-backs) to be executed post the successful execution of DQL query */


query.dctmData('select r_object_id,objecT_name from dm_document ',callback)







