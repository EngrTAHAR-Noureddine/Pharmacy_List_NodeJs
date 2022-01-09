const express= require('express');
const router=express.Router();
const Consultation = require('../../models/Consultations');

router.get('/',(req,res)=>{
    Consultation.find().then(user=>res.json(user))
});

router.post('/',(req,res)=>{
    const newConsultation = new Consultation({
        nameUser: req.body.nameUser,
        locationUser: req.body.locationUser,
        namePharmacy: req.body.namePharmacy,
        locationPharmacy: req.body.locationPharmacy,
        consultation: req.body.consultation
    });
    newConsultation.save().then(consultation=>res.json(consultation));
});

module.exports = router;