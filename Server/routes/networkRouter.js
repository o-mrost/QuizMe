/**
 * Created by Max on 02.12.16.
 */

var express = require('express');
var bodyParser = require('body-parser');
var mongoose = require('mongoose');

var Networks = require('../models/networks');

var networkRouter = express.Router();

networkRouter.use(bodyParser.json());

/** Route '/' **/

networkRouter.route('/')

    .get(function (req, res, next) {
        Networks.find({}, function (err, network) {
            if (err) throw err;
            res.json(network);
        });
    })

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

networkRouter.route('/:answerId/answer')
    .get(function (req, res, next) {
        Networks.findById(req.params.answerId, function (err, network) {
            if (err) throw err;
            res.json(network.answers);
        });
    })

    .post(function (req, res, next) {
         Networks.findById(req.params.answerId, function (err, network) {
            if (err) throw err;
            network.answers.push(req.body);
            network.save(function (err, network) {
                if (err) throw err;
                console.log('Updated answer!');
                res.json(network);
            });
        });
    });

module.exports = networkRouter;
