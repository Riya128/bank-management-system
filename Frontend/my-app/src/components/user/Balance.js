import React, { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "reactstrap";
import Menu from "../UserMenu.js/Menu";
import swal from "sweetalert";

function Balance() {
  const [balance, setBalance] = useState();
  const [accountNumber, setAccountNumber] = useState();
  const [show, setShow] = useState(false);
  let token = JSON.parse(localStorage.getItem("token"));

  useEffect(() => {
    setAccountNumber(localStorage.getItem("accountNumber"));
    document.body.style.backgroundColor = " gray";
  }, []);

  function getBalance() {
    axios
      .get(`http://localhost:8581/bank/user/getbalance/${accountNumber}`, {
        headers: { Authorization: "Bearer " + token },
      })
      .then(
        (res) => {
          setBalance(res.data);
          setShow(true);
          if (res.status !== 200) {
            swal({
              text: "Cannot view your balance",
              icon: "error",
              button: "Ok",
            });
          }
        },
        (err) => {}
      );
  }

  return (
    <div>
      <Menu />
      <br></br>
      <Button
        style={{ marginLeft: "5%", width: "85%" }}
        onClick={(e) => getBalance(e)}
        color="dark"
      >
        VIEW BALANCE
      </Button>
      {show === true ? (
        <h5
          style={{
            marginLeft: "5%",
            fontFamily: "sans-serif",
            marginTop: "8%",
          }}
        >
          Balance in your account is Rs. {balance}
        </h5>
      ) : (
        <></>
      )}
    </div>
  );
}

export default Balance;
