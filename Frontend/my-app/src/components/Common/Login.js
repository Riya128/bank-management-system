import React, { useEffect, useState } from "react";
import { FormGroup, Form, Label, Input, Button } from "reactstrap";
import Navbar from "./Navbar";
import Axios from "axios";
import { Navigate } from "react-router-dom";
import swal from "sweetalert";

function Login() {
  const url = "http://localhost:8581/login";
  const [data, setData] = useState({
    customerId: "",
    password: "",
  });
  const [success, setSuccess] = useState(false);
  const [path, setPath] = useState("/userDashboard");
  function handleChange(e) {
    const newdata = { ...data };
    newdata[e.target.id] = e.target.value;
    setData(newdata);
  }

  function submit(e) {
    e.preventDefault();
    if (data.customerId === "1789000") {
      setPath("/adminDashboard");
    }

    Axios.post(url, {
      customerId: data.customerId,
      password: data.password,
    })
      .then((res) => {
        if (res.status === 200) {
          setSuccess(true);
          e.target.reset();
          localStorage.setItem("token", JSON.stringify(res.data.jwtToken));

          swal({
            text: `You are successfully logged in`,
            icon: "success",
            button: "Ok",
          });

          setData({});
        }
      })
      .catch((err) => {
        if (err) {
          swal({
            text: `Enter valid credentials`,
            icon: "error",
            button: "Ok",
          });
          setData({});
        }
      });
  }

  return (
    <>
      <Navbar />
      {success ? <Navigate to={path} /> : <h3></h3>}
      <br></br>
      <h4
        style={{
          width: "50%",
          marginLeft: "31%",
          fontFace: "Verdana (sans-serif)",
          fontFamily: "revert",
        }}
      >
        LOGIN{" "}
      </h4>
      <br></br>
      <Form
        style={{ width: "50%", marginLeft: "5%" }}
        onSubmit={(e) => submit(e)}
      >
        <FormGroup>
          <Label for="cutomerId">Customer Id</Label>
          <Input
            type="text"
            id="customerId"
            value={data.customerId}
            placeholder="Enter your customer id"
            onChange={(e) => handleChange(e)}
            required
          />
        </FormGroup>
        <FormGroup>
          <Label for="password">Password</Label>
          <Input
            type="password"
            placeholder="Enter your password"
            id="password"
            value={data.password}
            onChange={(e) => handleChange(e)}
            required={true}
          />
        </FormGroup>

        <div>
          <Button color="success">LOGIN</Button>
        </div>
      </Form>
    </>
  );
}

export default Login;
