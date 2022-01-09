const mongoose = require('mongoose');

const Schema=mongoose.Schema;

//Create Schema
const UserSchema = new Schema({
    nameUser:{
        type:String,
        required:true
    },
    emailUser:{
        type:String,
        required:true
    },
    passwordUser:{
        type:String,
        required:true
    },
    locationUser:{
        type:String,
        required:true
    },
    photoUser:{
        type: String,
        required:true
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