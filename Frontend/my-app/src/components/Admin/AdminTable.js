import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Container, Table } from "reactstrap";
import swal from "sweetalert";

function AdminTable() {
  const [data, setData] = useState([]);
  const [status, setStatus] = useState("");
  const [istrue, setIstrue] = useState(false);
  let token = JSON.parse(localStorage.getItem("token"));

  function getPendingRequests(stat) {
    axios
      .get(`http://localhost:8581/bank/admin/getcustomers?status=${stat}`, {
        headers: { Authorization: "Bearer " + token },
      })
      .then(
        (res) => {
          setStatus(stat);
          setData(res.data);
        },
        (err) => {
          // alert("Cannot fetch pending customers")
        }
      );
  }
  function handlePending(e, stat) {
    e.preventDefault();
    if (stat === "pending") setIstrue(true);
    else setIstrue(false);
    setData([]);
    setStatus(stat);
    getPendingRequests(stat);
  }
  function handleApprove(e, id, req) {
    e.preventDefault();
    axios
      .put(
        `http://localhost:8581/bank/admin/request/${id}?request=${req}`,
        req,
        {
          headers: {
            Authorization: "Bearer " + token,
          },
        }
      )
      .then(
        (res) => {
          swal({
            text: `request ${req} successfully`,
            icon: "success",
            button: "Ok",
          });
          handlePending(e, "pending");
        },
        (err) => {}
      );
  }

  return (
    <>
      <span style={{}}>
        <button
          style={{ padding: "40px", marginTop: "5%", marginLeft: "5%" }}
          type="button"
          class="btn btn-dark"
          onClick={(e) => handlePending(e, "pending")}
        >
          Pending Requests
        </button>
        <button
          style={{ padding: "40px", marginTop: "5%", marginLeft: "5%" }}
          class="btn btn-success"
          type="button"
          onClick={(e) => handlePending(e, "approved")}
        >
          Approved Requests
        </button>
        <button
          style={{ padding: "40px", marginTop: "5%", marginLeft: "5%" }}
          class="btn btn-danger"
          type="button"
          onClick={(e) => handlePending(e, "rejected")}
        >
          Rejected Requests
        </button>
      </span>
      {data.length === 0 && status != "" ? (
        <h2>No {status} Request</h2>
      ) : (
        <>
          <div>
            <div className="row">
              <Table
                className="table table-bordered"
                style={{
                  marginTop: "40px",
                  borderRadius: "20px",
                  marginLeft: "80px",
                  borderColor: "white",
                  width: "90%",
                }}
                bordered
                responsive
              >
                {status != "" ? (
                  <>
                    <thead>
                      <tr style={{ background: "black", color: "white" }}>
                        <th>Customer Id</th>
                        <th>Customer Name</th>
                        <th>Contact Number</th>
                        <th>Address</th>
                        <th>Age</th>
                        <th>Email</th>
                        <th>Gender</th>
                        <th>Aadhar Number</th>
                      </tr>
                    </thead>
                  </>
                ) : (
                  <h2></h2>
                )}

                <tbody style={{ background: "black", color: "white" }}>
                  {data.map((d) => (
                    <tr key={d.customerId}>
                      <td>{d.customerId}</td>

                      <td>{d.customerName}</td>

                      <td>{d.contactNo}</td>
                      <td>{d.address}</td>
                      <td>{d.age}</td>
                      <td>{d.email}</td>
                      <td>{d.gender}</td>
                      <td>{d.aadharNumber}</td>
                      {istrue ? (
                        <>
                          <td>
                            <Container>
                              <Button
                                color="danger"
                                onClick={(e) => {
                                  handleApprove(e, d.customerId, "approved");
                                }}
                              >
                                Approve
                              </Button>
                            </Container>
                          </td>
                          <td>
                            <Container>
                              <Button
                                color="danger"
                                onClick={(e) => {
                                  handleApprove(e, d.customerId, "rejected");
                                }}
                              >
                                Reject
                              </Button>
                            </Container>
                          </td>
                        </>
                      ) : (
                        <h2></h2>
                      )}
                    </tr>
                  ))}
                </tbody>
              </Table>
            </div>
          </div>
        </>
      )}
    </>
  );
}

export default AdminTable;
