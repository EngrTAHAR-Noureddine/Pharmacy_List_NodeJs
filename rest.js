const express = require("express");
const mysql = require("mysql");
var app = express();
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(bodyParser.raw());
app.use(express.static('public'));


var connection = mysql.createPool({
    connectionLimit : 10,
    host     : 'localhost',
    user     : 'root',
    password : '',
    database : 'teamdb',
    multipleStatements: true

});



app.post('/addteam',function(req,res,next){
    var query = "INSERT  INTO team (idPharma,text) VALUES (?,?)";
    connection.query(query,[req.body.idPharma,req.body.text],function(error,results){

        if(error) {
            next(error)
        }
        else {
            res.send(JSON.stringify('success'));
        }
    })



});


app.get('/getteams/:nom',function(req,res,next){
    var query = "select * from teams where nom=?"
    connection.query(query,[req.params.nom],function(error,results){
        if (error) { next(error) } else {
            res.send(JSON.stringify(results));
        }
    })
})

app.get('/getteams',function(req,res,next){
    var query = "select * from teams"
    connection.query(query,function(error,results){
        if (error) { next(error) } else {
            res.send(JSON.stringify(results));
        }
    })
})

app.get('/getteams1',function(req,res,next){
    var query = "select * from team"
    connection.query(query,function(error,results){
        if (error) { next(error) } else {
            res.send(JSON.stringify(results));
        }
    })
})

app.get('/getusers',function(req,res,next){
    var query = "select * from appusers"
    connection.query(query,function(error,results){
        if (error) { next(error) } else {
            res.send(JSON.stringify(results));
        }
    })
});

/*récuperer les utilisteurs authentifiés avec email et mdp */
app.get('/getuser/:email/:mdp',function(req,res,next){
    var query = "select * from appusers where email=? and mdp=?"
    connection.query(query,[req.params.email,req.params.mdp],
        function(error,results){
            if (error) { next(error) } else {
                res.send(results);
            }
        })
});

var server = app.listen(8082,function(){
    var host = server.address().address
    var port = server.address().port
    console.log("connected...")
});