/**
 * Created by Max on 02.12.16.
 */

// grab the things we need
var mongoose = require('mongoose');
require('mongoose-currency').loadType(mongoose);
var Schema = mongoose.Schema;
var Currency = mongoose.Types.Currency;

/* Answer Schema */
var answerSchema = new Schema({
    answer: { type: String, required: true },
    solution: {type: Boolean, required: true}
},{ _id : false });


/* Network Schema */
var networkSchema = new Schema({
    question: { type: String, required: true, unique: true },
    category: { type: String, default: '' },
    answers: [answerSchema]
}, {
    timestamps: true
});

// the schema is useless so far
// we need to create a model using it
var Networks = mongoose.model('Network', networkSchema);

// make this available to our Node applications
module.exports = Networks;
