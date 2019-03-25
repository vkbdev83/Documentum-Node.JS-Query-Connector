/**
 * Created by babuvi on 13/04/2018.
 */

var query = require('dctm-query');
const express = require('express');
const app = express();

var reportsData ={};


app.use('/', express.static('./public'));

app.get('/getdata', (req, res) => {

        console.log("quer" + req.query.reportid);

        getData(function callback(json) {
        console.log("Response Json" + json);
        res.send(json);
    });

});


app.get('*', function(req, res) {
    res.sendFile('index.html',{root : "public"}); // load the single view file (angular will handle the page changes on the front-end)
})


app.listen(3000, () => console.log('Example app listening on port 3000!'));


function getData(callback) {

    var results = query.dctmData('select r_object_id,objecT_name from dm_document ',callback)



}


