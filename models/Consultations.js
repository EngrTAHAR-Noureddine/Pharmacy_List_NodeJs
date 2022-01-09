const mongoose = require('mongoose');

const Schema=mongoose.Schema;
//Create Schema
const ConsultationSchema = new Schema({
    nameUser:{
        type:String,
        required:true
    },
    locationUser:{
        type:String,
        required:true
    },
    namePharmacy:{
        type:String,
        required:true
    },
    locationPharmacy:{
        type:String,
        required:true
    },
    consultation:{
        type:String,
        required:true
    }
});

module.exports = Project = mongoose.model('consultations',ConsultationSchema);