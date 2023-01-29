import React, { useState } from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import { Link } from 'react-router-dom';
import { SidebarData } from './SidebarData';
import './Menu.css';
import { IconContext } from 'react-icons';
import { Navigate } from "react-router-dom"
import { Button } from "reactstrap";
import { AiOutlineLogout } from 'react-icons/ai';

function Menu() {
    const [sidebar, setSidebar] = useState(false);
    const [logout, setLogout] = useState(false);
    function logoutHandler(e) {
        localStorage.clear();
        setLogout(true);
    }

    const showSidebar = () => setSidebar(!sidebar);

    return (
        <>
            {logout ? <Navigate to="/" /> : <></>}

            <IconContext.Provider value={{ color: '#fff' }}>
                <div className="navbar">
                    <Link to='#' className='menu-bars'>
                        <FaIcons.FaBars onClick={showSidebar} />
                    </Link>
                    <Button style={{ fontSize: "20px" ,marginRight:"70%"}} color="dark">USER DASHBOARD</Button>

                    <Button style={{ marginRight: "2%" }}
                        color="danger"
                        onClick={(e) => logoutHandler(e)}

                    >
                        Logout
                    </Button>

                </div>
                <nav className={sidebar ? 'nav-menu active' : 'nav-menu'}>
                    <ul className='nav-menu-items' onClick={showSidebar}>
                        <li className='navbar-toggle'>
                            <Link to='#' className='menu-bars'>
                                <AiIcons.AiOutlineClose />
                            </Link>
                        </li>
                        {SidebarData.map((item, index) => {
                            return (
                                <li key={index} className={item.cName}>
                                    <Link to={item.path}>
                                        {item.icon}
                                        <span>{item.title}</span>
                                    </Link>
                                </li>
                            );
                        })}
                    </ul>
                </nav>

            </IconContext.Provider>
        </>
    );
}

export default Menu;