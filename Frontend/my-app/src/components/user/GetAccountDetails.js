import React, { useState } from "react";
import axios from "axios";
import { ListGroup, ListGroupItem } from "reactstrap";
import swal from "sweetalert";

function UserBody() {
  const [id, setId] = useState();
  const [data, setData] = useState([]);
  const [show, setShow] = useState(false);
  let token = JSON.parse(localStorage.getItem("token"));

  function handleClick(e) {
    axios
      .get(`http://localhost:8581/bank/user/getaccount?id=${id}`, {
        headers: { Authorization: "Bearer " + token },
      })
      .then((res) => {
        console.log(res.data);
        localStorage.setItem("accountNumber", res.data.accountNumber);
        setShow(true);
        setData(res.data);
        //  setId("");
      })
      .catch((error) => {
        swal({
          text: "The id is incorrect Please contact Bank for correct Id",
          icon: "info",
          button: "Ok",
        });
        // setId("");
      });
  }
  function handleChange(e) {
    setId(e.target.value);
  }
  return (
    <>
      <h4
        style={{
          width: "50%",
          marginLeft: "39%",
          marginTop: "2%",
          fontFace: "Verdana (sans-serif)",
          fontFamily: "revert",
        }}
      >
        View Account Details
      </h4>
      <div style={{ width: "40%", marginLeft: "28.5%", marginTop: "5%" }}>
        <label for="inputPassword2" class="visually-hidden">
          Id
        </label>
        <input
          type="text"
          class="form-control"
          id="inputPassword2"
          placeholder="Enter customer id to view account details"
          value={id}
          onChange={(e) => handleChange(e)}
        ></input>
        <br></br>
        <button
          type="button"
          class="btn btn-success"
          style={{ width: "40%", marginLeft: "26%" }}
          onClick={(e) => handleClick(e)}
        >
          VIEW
        </button>
      </div>
      <br></br>
      <br></br>
      {show == true ? (
        <ListGroup style={{ width: "40%", marginLeft: "29%" }}>
          <ListGroupItem style={{ backgroundColor: "black", color: "white" }}>
            Your Account Number Is {data.accountNumber}
          </ListGroupItem>
          <ListGroupItem style={{ backgroundColor: "black", color: "white" }}>
            Your Account Was Created at {data.accountOpenDate}
          </ListGroupItem>
          <ListGroupItem style={{ backgroundColor: "black", color: "white" }}>
            Account Balance is Rs. {data.balance}
          </ListGroupItem>
          <ListGroupItem style={{ backgroundColor: "black", color: "white" }}>
            Account Type is {data.accountType}
          </ListGroupItem>
        </ListGroup>
      ) : (
        <></>
      )}
    </>
  );
}

export default UserBody;
