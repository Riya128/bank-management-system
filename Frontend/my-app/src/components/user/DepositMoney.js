import React, { useState, useEffect } from "react";
import axios from "axios";
import { Button, Input } from "reactstrap";
import Menu from "../UserMenu.js/Menu";
import swal from "sweetalert";

function DepositMoney() {
  const [accountNumber, setAccountNumber] = useState();
  const [amount, setAmount] = useState();
  let token = JSON.parse(localStorage.getItem("token"));

  useEffect(() => {
    setAccountNumber(localStorage.getItem("accountNumber"));
  }, []);

  function deposit(e) {
    e.preventDefault();
    axios
      .post(
        `http://localhost:8581/bank/user/deposit/${accountNumber}?amount=${amount}`,
        amount,
        {
          headers: { Authorization: "Bearer " + token },
        }
      )
      .then(
        (res) => {
          swal({
            text: "Money deposited successfuly in your account",
            icon: "success",
            button: "Ok",
          });

          setAmount("");
        },
        (err) => {}
      );
  }
  function changeHandler(e) {
    console.log(e.target.value);
    setAmount(e.target.value);
  }

  return (
    <div>
      <Menu />
      <Input
        style={{ width: "40%", marginLeft: "5%", marginTop: "5%" }}
        onChange={(e) => changeHandler(e)}
        value={amount}
      ></Input>
      <br></br>
      <Button
        style={{ width: "10%", marginLeft: "18%" }}
        color="success"
        onClick={(e) => deposit(e)}
      >
        Deposit
      </Button>
    </div>
  );
}

export default DepositMoney;
