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
        data: Buffer,
        contentType: String
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