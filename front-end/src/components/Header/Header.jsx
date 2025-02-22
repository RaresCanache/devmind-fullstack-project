import {NavLink} from "react-router";
import "./Header.css"
import {useSelector} from "react-redux";
import {Drawer} from "@mui/material";
import {useState} from "react";
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';

const Header = () => {
    const user = useSelector(state => state.user.user);
    const [openDrawer, setOpenDrawer] = useState(false);

    const toggleDrawer = (toggled) => setOpenDrawer(toggled);

    return (
        <div className="nav-bar">
            <div className="inner-nav-bar">
                <NavLink to="" className={({isActive}) => isActive ? "active" : "not-active"}>
                    Home
                </NavLink>
                <NavLink to="login" className={({isActive}) => isActive ? "active" : "not-active"}>
                    Login
                </NavLink>
                <NavLink to="register" className={({isActive}) => isActive ? "active" : "not-active"}>
                    Register
                </NavLink>
            </div>
            <div>
                {user && (
                    <div className="user-drawer">
                        <button className="user-drawer-button" onClick={() => toggleDrawer(true)}>
                            <ArrowForwardIosIcon/>
                        </button>
                        <Drawer open={openDrawer} onClose={() => toggleDrawer(false)}>

                        </Drawer>
                    </div>)}
            </div>
        </div>
    );
};

export default Header;