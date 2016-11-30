// grab the things we need
var mongoose = require('mongoose')
require('mongoose-currency').loadType(mongoose)
var Schema = mongoose.Schema

/* Leader Schema */
var leaderSchema = new Schema({
  name: { type: String, required: true, unique: true },
  image: { type: String, default: '' },
  designation: { type: String, default: '' },
  abbr: { type: String, default: '' },
  description: { type: String, required: true }
}, {
  timestamps: true
})

// the schema is useless so far
// we need to create a model using it
var Leaders = mongoose.model('Leader', leaderSchema)

// make this available to our Node applications
module.exports = Leaders
