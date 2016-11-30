// grab the things we need
var mongoose = require('mongoose')
require('mongoose-currency').loadType(mongoose)
var Schema = mongoose.Schema
var Currency = mongoose.Types.Currency

/* Promotion Schema */
var promoSchema = new Schema({
  name: { type: String, required: true, unique: true },
  image: { type: String, default: '' },
  label: { type: String, default: '' },
  price: { type: Currency, default: '' },
  description: { type: String, required: true }
}, {
  timestamps: true
})

// the schema is useless so far
// we need to create a model using it
var Promotions = mongoose.model('Promotion', promoSchema)

// make this available to our Node applications
module.exports = Promotions
