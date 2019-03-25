/**
 * Created by Vishnukumar on 23/04/2018.
 */

var java = require('java');
var fs = require('fs');
java.classpath.push('./node_modules/dctm-query/dependencies/config');
java.classpath.push('./node_modules/dctm-query/dependencies/classes');

java.classpath.push('./node_modules/dctm-query/dependencies/lib/dfc-7.3.jar');
java.classpath.push('./node_modules/dctm-query/dependencies/lib/aspectjrt-1.8.6.jar');
java.classpath.push('./node_modules/dctm-query/dependencies/lib/commons-lang3-3.7.jar');
java.classpath.push('./node_modules/dctm-query/dependencies/lib/commons-lang-2.4.jar');
java.classpath.push('./node_modules/dctm-query/dependencies/lib/log4j-1.2.13.jar');
java.classpath.push('./node_modules/dctm-query/dependencies/lib/json-20180130.jar');

exports.dctmData = function(queryString,callback){

    var dctmProp = JSON.parse(fs.readFileSync('./node_modules/dctm-query/dependencies/config/dctm-prop.json', 'utf8'));

    java.callStaticMethod("npm.java.dfc.DfcUtils", 'executeQueryasList', dctmProp.userName, dctmProp.password, dctmProp.docbaseName,queryString

        , function (err, results) {
            if (err) {

                console.error(err);
                return;

            } else {

                callback(JSON.parse(results));
            }
        });
}

exports.getSessionTicket = function(username,password,callback){


    java.callStaticMethod("npm.java.dfc.DfcUtils", 'getSessionTicket',username , password, dctmProp.docbaseName
        , function (err, results) {
            if (err) {

                console.error(err);
                return;

            } else {

                console.log(results);
            }
        });

}

getSessionTicket = function(username,password,callback){


    java.callStaticMethod("npm.java.dfc.DfcUtils", 'getSessionTicket',username , password, dctmProp.docbaseName
        , function (err, results) {
            if (err) {

                console.error(err);
                return;

            } else {

                console.log(results);
            }
        });

}

java.callStaticMethod("npm.java.dfc.DfcUtils", 'getSessionTicket',"dmadmin" , "fsadmin73!", "cadev"
    , function (err, results) {
        if (err) {

            console.error(err);
            return;

        } else {

            console.log(results);
        }
    });