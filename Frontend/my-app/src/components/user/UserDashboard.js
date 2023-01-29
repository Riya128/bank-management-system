import React, { useState, useEffect } from 'react'
import GetAccountDetails from './GetAccountDetails';
import { Navigate } from "react-router-dom";
import { Button } from "reactstrap";
import Menu from '../UserMenu.js/Menu';
import AddBeneficiary from './AddBeneficiary';
import DepositMoney from './DepositMoney';
import TransferMoney from './TransferMoney';
function UserDashboard() {

    useEffect(() => {
        document.body.style.backgroundColor = " gray"
    }, [])

    return (
        <div>



            <Menu />
            <GetAccountDetails/>




        </div>
    )
}
export default UserDashboard