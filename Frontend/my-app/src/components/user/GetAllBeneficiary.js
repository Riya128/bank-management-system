import React, { useEffect, useState } from "react";
import axios from "axios";
import { Button, Container, Table } from "reactstrap";
import { BsFillArrowLeftSquareFill } from "react-icons/bs";
import { Navigate } from "react-router-dom";
import { Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import Menu from "../UserMenu.js/Menu";
import swal from "sweetalert";

function GetAllBeneficiary() {
  const [accountNumber, setAccountNumber] = useState();
  const [data, setData] = useState([]);
  const [modal, setModal] = useState("");
  const [editdata, setEditdata] = useState();
  const [show, setShow] = useState(false);
  const [len, setLen] = useState();
  const [editmodal, setEditmodal] = useState(false);
  let token = JSON.parse(localStorage.getItem("token"));

  useEffect(() => {
    document.body.style.backgroundColor = " gray";
    setAccountNumber(localStorage.getItem("accountNumber"));
    //viewBeneficiary()
  }, []);

  function viewBeneficiary() {
    axios
      .get(
        `http://localhost:8581/bank/user/getallbeneficiary/${accountNumber}`,
        {
          headers: { Authorization: "Bearer " + token },
        }
      )
      .then(
        (res) => {
          if (res.data.length === 0) {
            setLen(0);
          }
          console.log(res.status);
          setData(res.data);
          setShow(true);
          if (res.status === 400) {
            swal({
              text: "Cannot view the list",
              icon: "error",
              button: "Ok",
            });
          }
        },
        (err) => {
          setData([]);
          setLen(0);
          //alert('No Beneficiary Added')
        }
      );
  }
  function deleteBeneficiary(e, id) {
    e.preventDefault();
    axios
      .delete(`http://localhost:8581/bank/user/deletebeneficiary/${id}`, {
        headers: { Authorization: "Bearer " + token },
      })
      .then(
        (res) => {
          update(id);
          viewBeneficiary();
        },
        (error) => {}
      );
  }
  const update = (id) => {
    setData(data.filter((c) => c.id !== id));
  };
  function viewForm(id) {
    setEditmodal(!editmodal);

    setModal(id);
  }
  function changeHandler(e) {
    const neweditdata = { ...editdata };
    neweditdata[e.target.id] = e.target.value;
    setEditdata(neweditdata);
  }
  function editBeneficiary(e) {
    e.preventDefault();
    axios
      .put(
        `http://localhost:8581/bank/user/editbeneficiary/${modal}`,
        editdata,
        {
          headers: { Authorization: "Bearer " + token },
        }
      )
      .then(
        (response) => {
          viewBeneficiary(e);
          setModal("");
        },
        (error) => {}
      );
  }

  return (
    <div>
      {modal !== "" ? (
        <>
          <Modal isOpen={editmodal} toggle={(e) => viewForm(e)}>
            <ModalHeader toggle={(e) => viewForm(e)}>Edit</ModalHeader>
            <ModalBody>
              <form>
                <input
                  placeholder="Beneficiary Name"
                  onChange={(e) => changeHandler(e)}
                  id="bName"
                ></input>
                <input
                  placeholder="Beneficiary Email"
                  onChange={(e) => changeHandler(e)}
                  id="bEmail"
                ></input>
              </form>
            </ModalBody>
            <ModalFooter>
              <Button color="primary" onClick={(e) => editBeneficiary(e)}>
                Edit
              </Button>
              <Button color="secondary" onClick={(e) => viewForm(e)}>
                Cancel
              </Button>
            </ModalFooter>
          </Modal>
        </>
      ) : (
        <></>
      )}

      <div>
        <Menu />
      </div>
      <br></br>

      <Button
        color="dark"
        style={{ marginLeft: "5%", width: "90%" }}
        onClick={(e) => viewBeneficiary(e)}
      >
        VIEW
      </Button>
      <h3 style={{ textAlign: "center", color: "black", paddingTop: "5%" }}>
        Beneficiary List
      </h3>
      {len === 0 ? (
        <h4 style={{ marginLeft: "5%" }}>No Beneficiary Added</h4>
      ) : (
        <></>
      )}
      <Table
        style={{
          marginTop: "40px",
          borderRadius: "20px",
          marginLeft: "80px",
          borderColor: "white",
          width: "90%",
        }}
        bordered
      >
        {show == true ? (
          <thead>
            <tr style={{ backgroundColor: "black", color: "white" }}>
              <th>BId</th>
              <th>Beneficiary Account Number</th>
              <th>Account Type</th>
              <th>Email</th>
              <th>Name</th>
              <th>My Account Number</th>
              <th>Delete Record</th>
              <th>Edit Record</th>
            </tr>
          </thead>
        ) : (
          <h2></h2>
        )}

        <tbody style={{ backgroundColor: "black", color: "white" }}>
          {data.map((d) => (
            <tr key={d.beneficiaryId}>
              {Object.values(d).map((val) => (
                <td>{val}</td>
              ))}

              <td>
                <Container>
                  <Button
                    color="danger"
                    onClick={(e) => {
                      deleteBeneficiary(e, Object.values(d)[0]);
                    }}
                  >
                    Delete
                  </Button>
                </Container>
              </td>
              <td>
                <Container>
                  <Button
                    color="danger"
                    onClick={() => {
                      viewForm(Object.values(d)[0]);
                    }}
                  >
                    Edit
                  </Button>
                </Container>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default GetAllBeneficiary;
