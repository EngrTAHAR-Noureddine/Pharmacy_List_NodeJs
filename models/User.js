const mongoose = require('mongoose');

const Schema=mongoose.Schema;

//Create Schema
const UserSchema = new Schema({
    nameUser:{
        type:String,
        required:true
    },
    locationUser:{
        type:String,
        required:true
    },
    photoUser:{
        data: Buffer,
        contentType: String
    },
    typeUser:{
        type:String,
        required:true
    },
    phoneUser:{
        type:String,
        required:true
    }
});

module.exports = Project = mongoose.model('users',UserSchema);