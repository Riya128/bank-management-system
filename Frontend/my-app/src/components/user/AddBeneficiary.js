import React, { useState, useEffect } from "react";
import Axios from "axios";
import { Input, Button, Label } from "reactstrap";
import Menu from "../UserMenu.js/Menu";
import swal from "sweetalert";
function AddBeneficiary() {
  let token = JSON.parse(localStorage.getItem("token"));
  const [accountNumber, setAccountNumber] = useState();
  const url = `http://localhost:8581/bank/user/addbeneficiary/${accountNumber}`;
  const [data, setData] = useState({
    bName: "",
    bAccountNumber: "",
    bEmail: "",
    bAccountType: "",
  });

  useEffect(() => {
    setAccountNumber(localStorage.getItem("accountNumber"));
  }, []);

  function handleChange(e) {
    const newdata = { ...data };
    newdata[e.target.id] = e.target.value;
    setData(newdata);
    console.log(data);
  }

  function submit(e) {
    e.preventDefault();
    Axios.post(
      url,
      {
        bName: data.bName,
        bAccountNumber: data.bAccountNumber,
        bEmail: data.bEmail,
        bAccountType: data.bAccountType,
      },
      { headers: { Authorization: "Bearer " + token } }
    )
      .then((res) => {
        console.log(res.status);
        if (res.status === 201) {
          e.target.reset();
          swal({
            text: "Added Successfully",
            icon: "success",
            button: "Ok",
          });

          setData({});
        }
        if (res.status === 400) {
          e.target.reset();
          swal({
            text: "Cannot add beneficiary! Beneficiary should have an account in this bank",
            icon: "info",
            button: "Ok",
          });

          setData({});
        }
      })
      .catch((err) => {
        e.target.reset();
        swal({
          text: "Cannot add beneficiary! Beneficiary should have an account in this bank",
          icon: "info",
          button: "Ok",
        });

        setData({});
      });
  }
  return (
    <div>
      <Menu />
      <form onSubmit={(e) => submit(e)}>
        <div
          style={{
            width: "40%",
            marginLeft: "35%",
            marginTop: "2%",
            color: "maroon",
            fontSize: "15px",
          }}
        >
          Beneficiary should be an account holder in the bank
        </div>

        <div
          style={{ width: "40%", marginLeft: "5%", marginTop: "5%" }}
          class="col-auto"
        >
          <Label for="bName" class="visually-hidden">
            Beneficiary Name
          </Label>
          <div>
            <Input
              type="text"
              id="bName"
              value={data.bName}
              placeholder="Enter Beneficiary Name"
              onChange={(e) => handleChange(e)}
              class="form-control"
              required
            />
          </div>

          <Label for="bEmail" class="visually-hidden">
            Beneficiary Email
          </Label>
          <div>
            <Input
              type="email"
              id="bEmail"
              value={data.bEmail}
              placeholder="Enter Beneficiary Email"
              onChange={(e) => handleChange(e)}
              class="form-control"
              required
            />
          </div>

          <Label for="bAccountNumber" class="visually-hidden">
            Beneficiary Acount Number
          </Label>
          <div>
            <Input
              type="text"
              id="bAccountNumber"
              value={data.bAccountNumber}
              placeholder="Enter Beneficiary Account Number"
              onChange={(e) => handleChange(e)}
              class="form-control"
              required
            />
          </div>

          <Label for="bAccountType" class="visually-hidden">
            Beneficiary Account Type
          </Label>
          <div>
            <Input
              type="text"
              id="bAccountType"
              value={data.bAccountType}
              placeholder="Enter Beneficiary Account Type"
              onChange={(e) => handleChange(e)}
              class="form-control"
              required
            />
          </div>
        </div>
        <br></br>

        <Button style={{ marginLeft: "5%" }} color="success">
          Add
        </Button>
      </form>
    </div>
  );
}

export default AddBeneficiary;
