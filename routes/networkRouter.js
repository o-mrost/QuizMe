//import required modules
var express = require('express');
var bodyParser = require('body-parser');
var mongoose = require('mongoose');

var Networks = require('../models/networks');

var networkRouter = express.Router();

networkRouter.use(bodyParser.json());

// Route '/networks' 

networkRouter.route('/')

//for every incoming GET request query the DB for all questions
//and return it as a JSON
    .get(function (req, res, next) {
        Networks.find({}, function (err, network) {
            if (err) throw err;
            res.json(network);
        });
    })

//for every incoming POST request add a new record that 
//matches the schema
    .post(function (req, res, next) {
        Networks.create(req.body, function (err, network) {
            if (err) throw err;
            console.log('Question created!');
            var id = network._id;
            res.writeHead(200, {
                'Content-Type': 'text/plain'
            });
            res.end('Added the question with id: ' + id);
        });
    });

module.exports = networkRouter;
