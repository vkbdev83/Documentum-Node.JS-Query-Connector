# Documentum-Node.JS-Query-Connector

Source Code of the Connector , connector could be used for download from npm

Node.js is a JavaScript runtime built on Chrome's V8 JavaScript engine. Node.js uses an event-driven, non-blocking I/O model that makes it lightweight and efficient.  It brings event-driven programming to web servers, enabling development of fast web servers in JavaScript. Node.js operates on a single thread, using non-blocking I/O calls, allowing it to support tens of thousands of concurrent connections without incurring the cost of thread context switching.  

npm is a package manager for the JavaScript programming language. It is the default package manager for the JavaScript runtime environment Node.js . npm is the largest ecosystem of open source libraries in the world.

Documentum DFC provides collection of Java APIs for accessing Documentum capabilities.
Similar to DFC, a new node module (library) written in Java Script is developed for performing the DQL read capabilities of the Documentum.  This module could be used within your node.js application for execution of Documentum READ query and retrieve the data in JSON format.


The below are the steps required to setup the Documentum node module within your node.js application

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



