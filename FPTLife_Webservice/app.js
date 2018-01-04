var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var routes = require('./routes/index');
var users = require('./routes/users');
var mysql = require('mysql');
var connection = require("express-myconnection");
var app = express();

// Create Sql Connection
app.use(connection(mysql, {
  // host: "us-cdbr-iron-east-05.cleardb.net",
  // user: "b46b32fead3e5a",
  // password: "6017cf3b",
  // database: "heroku_8ab1d308346fa9a",
  // host: "localhost",
  // user: "root",
  // password: "",
  // database: "schedule",
  host: "sql12.freemysqlhosting.net",
  user: "sql12203217",
  password: "hcld3wGGtt",
  database: "sql12203217",
  timezone: 'utc'
}, 'request'));


// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', routes);
app.use('/users', users);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function (err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function (err, req, res, next) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});

module.exports = app;
