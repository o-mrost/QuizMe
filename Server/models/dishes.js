// grab the things we need
var mongoose = require('mongoose')
require('mongoose-currency').loadType(mongoose)
var Schema = mongoose.Schema
var Currency = mongoose.Types.Currency

/* Comment Schema */
var commentSchema = new Schema({
  rating: { type: Number, min: 1, max: 5, required: true },
  comment: { type: String, required: true },
  author: { type: String, required: true }
}, {
  timestamps: true
})

/* Dish Schema */
var dishSchema = new Schema({
  name: { type: String, required: true, unique: true },
  image: { type: String, default: '' },
  category: { type: String, default: '' },
  label: { type: String, default: '' },
  price: { type: Currency, default: '' },
  description: { type: String, required: true },
  comments: [commentSchema]
}, {
  timestamps: true
})

// the schema is useless so far
// we need to create a model using it
var Dishes = mongoose.model('Dish', dishSchema)

// make this available to our Node applications
module.exports = Dishes
