import React, { useState } from "react";
import Axios from "axios";
import { Navigate } from "react-router-dom";
import NavBar from "./Navbar";
import { FormGroup, Form, Label, Input, Button } from "reactstrap";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./UserRegisteration.css";
import swal from "sweetalert";

function UserRegisterForm() {
  const url = "http://localhost:8581/bank/register";
  const [data, setData] = useState({
    customerName: "",
    password: "",
    age: "",
    aadharNumber: "",
    address: "",
    contactNo: "",
    email: "",
    gender: "",
    accountType: "",
  });
  const [success, setSuccess] = useState(false);
  const [id, setId] = useState();
  const [startDate, setStartDate] = useState("");
  const [age, setAge] = useState("");

  function handleChange(e) {
    const newdata = { ...data };
    newdata[e.target.id] = e.target.value;

    newdata["age"] = age;
    setData(newdata);
    // console.log(newdata);
  }
  function handleDate(date) {
    setStartDate(date);
    var today = new Date();
    let age_now = today.getFullYear() - date.getFullYear();
    let m = today.getMonth() - date.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < date.getDate())) {
      age_now--;
    }
    setAge(age_now);
    console.log(age_now);
    const newdata = { ...data };
    newdata["age"] = age_now;
    setData(newdata);
  }
  function submit(e) {
    e.preventDefault();
    console.log(data);

    if (data.age < 18) {
      swal({
        text: `Age should be more than 18`,
        icon: "info",
        button: "Ok",
      });
      return;
    }
    console.log(data);

    //error handling in drop down left
    Axios.post(url, {
      customerName: data.customerName,
      password: data.password,
      age: parseInt(data.age),
      aadharNumber: data.aadharNumber,
      address: data.address,
      contactNo: data.contactNo,
      email: data.email,
      gender: data.gender,
      accountType: data.accountType,
    })
      .then((res) => {
        if (res.status === 201) {
          setSuccess(true);
          e.target.reset();
          setId(res.data.customerId);

          swal({
            text: `Request for account is created! You can view your account status using ${res.data.customerId}`,
            icon: "success",
            button: "Ok",
          });

          setData({});
        }
      })
      .catch((err) => {
        if (err) {
          swal({
            text: `Sorry! Could not submit your form`,
            icon: "error",
            button: "Ok",
          });
          setData({});
        }
      });
  }

  return (
    <>
      <NavBar />

      {success ? <Navigate to={"/accountStatus"} /> : <h3></h3>}
      <br></br>
      <h4
        style={{
          width: "50%",
          marginLeft: "31%",
          fontFace: "Verdana (sans-serif)",
          fontFamily: "revert",
        }}
      >
        Fill up the form to create an account
      </h4>
      <br></br>
      <Form
        style={{ width: "50%", marginLeft: "5%" }}
        onSubmit={(e) => submit(e)}
      >
        <FormGroup>
          <Label for="cutomerName">Name</Label>
          <Input
            type="text"
            id="customerName"
            value={data.customerName}
            placeholder="Enter your name"
            onChange={(e) => handleChange(e)}
            minLength={1}
            required
          />

          <div className="textcolor">Please enter your full name.</div>
        </FormGroup>
        <FormGroup>
          <Label for="age">Date Of Birth</Label>
          <DatePicker
            wrapperClassName="datePicker"
            dateFormat="dd/MM/yyyy"
            placeholderText="Click to select your DOB"
            selected={startDate}
            id="age"
            onChange={(date) => handleDate(date)}
            required
            peekNextMonth
            showMonthDropdown
            showYearDropdown
            dropdownMode="select"
            maxDate={new Date()}
          />
          <div className="textcolor">
            Minimum age to open account is 18 years.
          </div>
        </FormGroup>
        <FormGroup>
          <Label for="aadharNumber">Aadhar Number</Label>
          <Input
            type="text"
            id="aadharNumber"
            value={data.aadharNumber}
            placeholder="Enter your Aadhar number"
            onChange={(e) => handleChange(e)}
            maxlength="12"
            pattern="\d{12}"
            required
          />
          <div className="textcolor">
            Please enter your 12 digit aadhar number.
          </div>
        </FormGroup>
        <FormGroup>
          <Label for="address">Address</Label>
          <Input
            type="text"
            id="address"
            value={data.address}
            placeholder="Enter your address"
            onChange={(e) => handleChange(e)}
            required
          />
          <div className="textcolor">
            Please enter your residential address.
          </div>
        </FormGroup>
        <FormGroup>
          <Label for="contactNo">Contact number</Label>
          <Input
            type="text"
            placeholder="Enter your Contact Number"
            id="contactNo"
            maxlength="10"
            pattern="\d{10}"
            value={data.contactNo}
            onChange={(e) => handleChange(e)}
            required
          />
          <div className="textcolor">Please enter 10 digit contact number.</div>
        </FormGroup>
        <FormGroup>
          <Label for="gender">Select Gender</Label>
          <Input
            type="select"
            id="gender"
            value={data.gender}
            onChange={(e) => handleChange(e)}
            class="form-control"
            required
          >
            <option disabled>Select your Gender</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
          </Input>
        </FormGroup>
        <FormGroup>
          <Label for="contactNo">Email id</Label>
          <Input
            type="email"
            placeholder="Enter your Email id"
            id="email"
            value={data.email}
            onChange={(e) => handleChange(e)}
            required
          />
          <div className="textcolor">Please enter your email id.</div>
        </FormGroup>
        <FormGroup>
          <Label for="password">Password</Label>
          <Input
            type="password"
            placeholder="Enter your password"
            id="password"
            value={data.password}
            onChange={(e) => handleChange(e)}
            minLength={5}
            required={true}
          />
          <div className="textcolor">
            Password should be of minimum 5 characters.
          </div>
        </FormGroup>

        <FormGroup>
          <Label for="accountType">Select Account Type</Label>
          <Input
            type="select"
            id="accountType"
            value={data.accountType}
            onChange={(e) => handleChange(e)}
            required
            class="form-control"
          >
            <option disabled>Select your Account Type</option>
            <option value="savings">Savings</option>
            <option value="current">Current</option>
          </Input>
        </FormGroup>

        <div>
          <Button color="success">SUBMIT</Button>
        </div>
      </Form>
    </>
  );
}

export default UserRegisterForm;
