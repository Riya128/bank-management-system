import React from 'react'
import { Navigate } from 'react-router-dom';
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Button from "@material-ui/core/Button";
import { useNavigate, Route, Routes } from "react-router-dom";
import { requirePropFactory } from '@material-ui/core';
const useStyles = makeStyles((theme) => ({
  rooot: {
    flexGrow: 1
  },
  menuButton: {
    marginRight: theme.spacing(1),
  }, title: {
    flexGrow: 1
  }
}));
function Navbar() {

  const classes = useStyles();
  
  const navigate = useNavigate();
  return (
    <div className={classes.root} >

      <AppBar  style={{ background: 'black' }} position="static">
        
        <Toolbar>
    <img src={require("./bank-vector-icon.jpg")} style={{width:"3.5%"}}/>
        <Button color="inherit" style={{marginRight:"40%" ,fontSize:"100%" }}>BMS BANK</Button>
          <Button color="inherit"
            onClick={() => {
              navigate("/");
            }}>
            HOME
          </Button>
          <Button color="inherit" 
            onClick={() => { navigate("/register") } }>CREATE ACCOUNT</Button>
          <Button color="inherit"
            onClick={() => { navigate("/contact") }}>CONTACT US</Button>
          <Button color="inherit"
            onClick={() => { navigate("/login") }}>LOGIN</Button>

          <Button color="inherit"
            onClick={() => { navigate("/accountStatus") }}>VIEW STATUS</Button>
         
        </Toolbar>
      </AppBar>


    </div>
  )
}

export default Navbar