import React, { useState, useEffect } from 'react';
import { Navigate } from "react-router-dom";
import { Button, CardBody } from "reactstrap";
import { AppBar, Toolbar } from '@material-ui/core';
import AdminTable from './AdminTable';
import { IconContext } from 'react-icons';

function AdminDashboard() { 
    const [logout, setLogout] = useState(false);
    function logoutHandler(e) {
        localStorage.clear();
        setLogout(true);
    }
    useEffect(() => {
        document.body.style.backgroundColor = " gray"
    }, [])

    return (
        <div>
            {logout ? <Navigate to="/" /> : <></>}
           <IconContext.Provider value={{ color: '#fff' }}>
                <div class="navbar">
                    <Button color="dark">ADMIN DASHBOARD</Button>

                    <Button style={{marginRight:"2%"}}
                        color="danger"
                        onClick={(e) => logoutHandler(e)}

                    >
                        Logout
                    </Button>
                </div>
                </IconContext.Provider>

            <AdminTable/> 




        </div>
    )
}

export default AdminDashboard