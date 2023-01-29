import React, { useState } from "react";
import Navbar from "./Navbar";
import axios from "axios";
import { InputGroup, Button, Input } from "reactstrap";
import swal from "sweetalert";
function AccountStatus() {
  const [id, setId] = useState();

  function handleStatus(e) {
    axios
      .get(`http://localhost:8581/bank/viewaccountstatus?id=${id}`)
      .then((data) => {
        swal({
          text: `Account Status is ${data.data}`,
          icon: "info",
          button: "Ok",
        });
        setId("");
        if (data.status == 400) {
          swal({
            text: "The id is incorrect Please contact Bank for correct Id",
            icon: "info",
            button: "Ok",
          });
        }
      })
      .catch((error) => {
        swal({
          text: "Cannot connect to the network",
          icon: "error",
          button: "Ok",
        });

        setId("");
      });
  }
  function handleChange(e) {
    setId(e.target.value);
    console.log(id);
  }
  return (
    <div>
      <Navbar />
      <div style={{ marginTop: "10%", marginLeft: "25%", width: "40%" }}>
        <InputGroup type="text">
          <Input
            placeholder="Enter the id"
            value={id}
            onChange={(e) => handleChange(e)}
          />
          <Button color="dark" onClick={(e) => handleStatus(e)}>
            VIEW STATUS
          </Button>
        </InputGroup>

        <div style={{ color: "red" }}>Create account to get Id</div>
      </div>
    </div>
  );
}

export default AccountStatus;
