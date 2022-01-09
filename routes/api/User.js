const express= require('express');
const router=express.Router();
const User = require('../../models/User');

router.get('/',(req,res)=>{
    User.find().then(user=>res.json(user))
});

router.post('/',(req,res)=>{
    const newUser = new User({
        nameUser: req.body.nameUser,
        locationUser: req.body.locationUser,
        photoUser: req.body.photoUser,
        typeUser: req.body.typeUser,
        consultingUser: req.body.consultingUser,
        phoneUser: req.body.phoneUser,
        emailUser: req.body.emailUser,
        passwordUser : req.body.passwordUser
    });
    newUser.save().then(user=>res.json(user));
});

module.exports = router;