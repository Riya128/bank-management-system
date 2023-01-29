import React from "react";

import "./Footer.css";

const Footer = () => {
  return (
    <footer id="footer">
      <div className="leftFooter">
        <h4>Best Services Available</h4>
        <p>Banking At Door Step</p>

       
      </div>

      <div className="midFooter">
        <h1>BMS BANK.</h1>
        <p>Providing smooth services is our first priority</p>

      </div>

      <div className="rightFooter">
        <h4>Contact Us</h4>
        <a href="http://instagram.com">Instagram</a>
        <a href="http://youtube.com">Youtube</a>
        <a href="http://facebook.com/">Facebook</a>
      </div>
    </footer>
  );
};

export default Footer;