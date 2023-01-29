import React, { useState, useEffect } from "react";
import axios from "axios";
import { useLinkClickHandler } from "react-router-dom";
import { Button, Table } from "reactstrap";
import Menu from "../UserMenu.js/Menu";
import swal from "sweetalert";

function TransferMoney() {
  const [accountNumber, setAccountNumber] = useState();
  const [data, setData] = useState();
  const [postdata, setPostdata] = useState({
    transactionAmount: "",
    bAccountNumber: "",
  });
  const [array, setArr] = useState([]);
  let token = JSON.parse(localStorage.getItem("token"));

  useEffect(() => {
    setAccountNumber(localStorage.getItem("accountNumber"));

    viewBeneficiary();
  }, [accountNumber, postdata]);

  //get the list of beneficiaries
  function viewBeneficiary(e) {
    axios
      .get(
        `http://localhost:8581/bank/user/getallbeneficiary/${accountNumber}`,
        {
          headers: { Authorization: "Bearer " + token },
        }
      )
      .then(
        (res) => {
          let count = 1,
            i = 0;
          let arr = [];
          setData(res.data);
          res.data.map((d) => {
            Object.values(d).map((val) => {
              if (count == i) {
                arr.push({ val });
                count += 6;
              }
              i++;
            });
          });
          console.log(arr);
          setArr(arr);
          //   console.log(array[0].val)
        },
        (err) => {}
      );
  }

  function transfer(e) {
    e.preventDefault();
    console.log(postdata.bAccountNumber);
    console.log(postdata.transactionAmount);
    axios
      .post(
        `http://localhost:8581/bank/user/transaction/${accountNumber}`,
        {
          transactionAmount: postdata.transactionAmount,
          bAccountNumber: postdata.bAccountNumber,
        },
        {
          headers: { Authorization: "Bearer " + token },
        }
      )
      .then((res) => {
        if (res.status == 200) {
          // e.target.reset();
          //    setPostdata({bAccountNumber:"",transactionAmount:""})
          swal({
            text: "successfully deposited",
            icon: "success",
            button: "Ok",
          });
        }
        //      setPostdata({bAccountNumber:"",transactionAmount:""})

        if (res.status == 400) {
          //  setPostdata({bAccountNumber:"",transactionAmount:""})
        }
      })
      .catch((err) => {
        console.log(err.code);

        swal({
          text: "Not enough balance",
          icon: "info",
          button: "Ok",
        });
      });
  }

  function changeHandler(e) {
    const newdata = { ...postdata };
    newdata[e.target.id] = e.target.value;
    setPostdata(newdata);
    console.log(newdata);
  }
  return (
    <>
      <Menu />
      <form
        onSubmit={(e) => transfer(e)}
        style={{ marginLeft: "5%", width: "60%", marginTop: "5%" }}
      >
        <input
          required="true"
          type="text"
          id="transactionAmount"
          value={postdata.transactionAmount}
          placeholder="Enter the amount to transfer"
          onChange={(e) => changeHandler(e)}
          class="form-control"
        />
        <br></br>
        <select
          id="bAccountNumber"
          value={postdata.bAccountNumber}
          onClick={(e) => changeHandler(e)}
          class="form-select"
          aria-label="Default select example"
        >
          <option disabled selected>
            Choose the account Number{" "}
          </option>
          {array.map((a) =>
            Object.values(a).map((val) => <option>{val}</option>)
          )}
        </select>
        <h6 style={{ marginLeft: "1%", width: "60%", color: "red" }}>
          First Add A Beneficiary To Pay
        </h6>
        <Button
          style={{ marginLeft: "5%", width: "60%", marginTop: "5%" }}
          color="success"
        >
          TRANSFER
        </Button>
      </form>
    </>
  );
}

export default TransferMoney;
