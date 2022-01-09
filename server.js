const express = require('express');
const mongoose = require('mongoose');
const app = express();
const config = require('config');


const user = require('./routes/api/User');
const consultation = require('./routes/api/Consultation');


app.use(express.json());

const db = config.get('mongoURI');

//connect to mongo
mongoose.connect(db)
    .then(()=>console.log('mongo db connect'))
    .catch(err=>console.log(err));


app.use('/api/user',user);
app.use('/api/consultation',consultation);


const port = 5000;

app.listen(port,()=>console.log('server started on port : ',port));