import React from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import { AiOutlineLogout } from 'react-icons/ai';
import {AiOutlinePlus} from 'react-icons/ai';
import{BsFillPlusCircleFill,BsChevronDoubleRight} from 'react-icons/bs';
export const SidebarData = [
    {
        title: 'Home',
        path: '/userDashboard',
        icon: <AiIcons.AiFillHome />,
        cName: 'nav-text'
    },
    {
        title: 'View Beneficiaries',
        path: '/getallbeneficiary',
        icon: <BsChevronDoubleRight />,
        cName: 'nav-text'
    },
    {
        title: 'Deposit Money',
        path: '/depositmoney',
        icon: <BsChevronDoubleRight />,
        cName: 'nav-text'
    },
    {
        title: 'Add Beneficiary',
        path: '/addbeneficiary',
        icon: <BsChevronDoubleRight />,
        cName: 'nav-text'
    },
    {
        title: 'Pay',
        path: '/makepayment',
        icon: <BsChevronDoubleRight />,
        cName: 'nav-text'
    },
    {
        title: 'View Balance',
        path: '/viewbalance',
        icon: <BsChevronDoubleRight />,
        cName: 'nav-text'
    },
];