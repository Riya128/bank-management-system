import React,{useEffect} from 'react'
import NavBar from './Navbar';
import Footer from './Footer';
import './MainHome.css';
import { Card, CardText, CardBody } from 'reactstrap';
function MainHome() {
  useEffect(() => {
    document.body.style.backgroundColor = " gray"

  }, [])
  
  return (
    <>
      <NavBar />

      <div class="float-container">
        <div class="float-child" style={{ marginTop: "2%" }} >
         
         <CardText> <p style={{ fontFace: "Verdana (sans-serif)", color: "black", fontFamily: "cursive", fontSize: "25px", textAlign: "center" }} >
            We're here to grow together</p></CardText>

          <p style={{ fontFace: "Verdana (sans-serif)", color: "black", textAlign: "center" }}>We have deep roots in the places where we live and work,<br></br>
            and a history of making a real world difference in the communities we call home </p>
            <br></br>

       
        </div>
      </div>
      
    
        <Footer />
        
    </>
  )
}

export default MainHome