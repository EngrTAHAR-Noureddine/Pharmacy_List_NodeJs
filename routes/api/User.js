const express= require('express');
const router=express.Router();
const User = require('../../models/User');

router.get('/',(req,res)=>{

    User.find().then(user=>{
        let list = user.filter(u => u.typeUser === "Pharmacy");
        list.forEach(element => element.passwordUser = null);
        res.json(list)
    })
});

router.post('/login',(req,res)=>{
    User.find().then(user=>{
        //console.log(user);

        var findedUser = null;
        if(user){
        for (let i = 0; i < user.length; i++){
            if(user[i].emailUser === req.body.emailUser && user[i].passwordUser === req.body.passwordUser){
                //console.log(user[i])
                findedUser = user[i]
            }
        }
        }
        res.json(findedUser)
    })
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