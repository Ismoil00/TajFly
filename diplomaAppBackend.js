express = require('express');
express_object = new express();


mysql_class = require('mysql');
my_database = {host: '18.184.7.144', port: '3366', user: 'ilmhona', password: 'Feb@2222', database: 'ilmhona'};
mysql_object = mysql_class.createConnection(my_database);


//----------------------------------------------------------------------------------------------------------------------------------------------
//Below are functions for planes departuring from tajikistan airposts!


express_object.get('/DYU_Departure', DYU_Departure);
function DYU_Departure(request, response){
    mysql_object.query('SELECT * from Ismoil_FlyingPlanes where airport_from = "DYU";', function (error, results){
        console.log(error);
        response.send(results);
    });
}

express_object.get('/LBD_Departure', LBD_Departure);
function LBD_Departure(request, response){
    mysql_object.query('SELECT * from Ismoil_FlyingPlanes where airport_from = "LBD";', function (error, results){
        console.log(error);
        response.send(results);
    });
}

express_object.get('/TJU_Departure', TJU_Departure);
function TJU_Departure(request, response){
    mysql_object.query('SELECT * from Ismoil_FlyingPlanes where airport_from = "TJU";', function (error, results){
        console.log(error);
        response.send(results);
    });
}

//----------------------------------------------------------------------------------------------------------------------------------------------
//Below are functions for planes arriving to Tajikistan airports!


express_object.get('/DYU_Arrival', DYU_Arrival);
function DYU_Arrival(request, response){
    mysql_object.query('SELECT * FROM Ismoil_LandingPlanes where airport_to = "DYU";', function (error, results){
        console.log(error);
        response.send(results);
    });
}

express_object.get('/LBD_Arrival', LBD_Arrival);
function LBD_Arrival(request, response){
    mysql_object.query('SELECT * FROM Ismoil_LandingPlanes where airport_to = "LBD";', function (error, results){
        console.log(error);
        response.send(results);
    });
}

express_object.get('/TJU_Arrival', TJU_Arrival);
function TJU_Arrival(request, response){
    mysql_object.query('SELECT * FROM Ismoil_LandingPlanes where airport_to = "TJU";', function (error, results){
        console.log(error);
        response.send(results);
    });
}

//----------------------------------------------------------------------------------------------------------------------------------------------
//Below are functions for signing up event!


express_object.get('/signUpEmailMatch', signUpEmailMatch);
function signUpEmailMatch(request, response){
    mysql_object.query('SELECT email from Ismoil_Users where email = "' + request.query.email + '"', function (error, results){
        response.send(results);
        console.log(results);
    });
}

express_object.get('/signUpPasswordMatch', signUpPasswordMatch);
function signUpPasswordMatch(request, response){
    mysql_object.query('SELECT `password` from Ismoil_Users where `password` = "' + request.query.password + '"', function (error, results){
        response.send(results);
        console.log(results);
    });
}


express_object.get('/addAccountData', addAccountData);
function addAccountData(request, response){
    mysql_object.query('INSERT INTO Ismoil_Users(first_name, last_name, password, email) VALUES("' + request.query.first_name + '", "' + request.query.last_name + '", "' + request.query.password + '", "'+ request.query.email + '")', function (error, results){
        response.send(results);
        console.log(results);
    });
}


//----------------------------------------------------------------------------------------------------------------------------------------------
//Below are functions for signing in event!


express_object.get('/accountCheck', accountCheck);
function accountCheck(request, response){
    mysql_object.query('SELECT first_name FROM Ismoil_Users WHERE email="' + request.query.email + '" and `password`="' + request.query.password + '"', function (error, results){
        response.send(results);
        console.log(results);
    });
}

express_object.listen(3500);