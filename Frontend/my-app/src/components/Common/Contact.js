import React, { Component } from 'react';
import {
  Card, CardText, CardBody,
  CardTitle, CardSubtitle, Button
} from 'reactstrap';
import Home from './MainHome';
import './Contact.css'
import { AiFillLinkedin, AiFillMail, AiFillGithub } from 'react-icons/ai'
import Navbar from './Navbar';
function Contact() {
  return (
    <>
      <Navbar />



      <div >
        <div id='contactmain'>
          <Card>
            <CardBody>
              <h3 style={{ color: "purple" }}>Contact Us</h3>

              <CardText><AiFillMail/><label style={{ color: "purple" }}> Email:<a href="https://mail.google.com/mail/?view=cm&fs=1&to=email@domain.example">cognizantdev@gmail</a></label><em></em></CardText>
              <CardText><AiFillGithub width="70px" height="30px" /><label style={{ color: "purple" }}>Github:</label><em><a href="https://github.com/login">cognizantdev@github.com</a></em></CardText>
              <CardText><AiFillLinkedin width="40px" height="30px" /><label style={{ color: "purple" }}>Linkedin:</label><em><a href="http://www.linkedin.com/">cognizantdev@linkedin.in</a></em></CardText>

            </CardBody></Card>

        </div>
      </div>

    </>

  )
}

export default Contact