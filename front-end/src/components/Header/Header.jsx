import {NavLink} from "react-router";
import "./Header.css"
import {useSelector} from "react-redux";
import {Drawer} from "@mui/material";

const Header = () => {
    const user = useSelector(state => state.user.user);

    return (
        <div style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center"
        }}>
            <div className="nav-bar">
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
            {/*<div>*/}
            {/*    {user && (*/}
            {/*        <div>*/}
            {/*            //TODO To implement UserDrawer*/}
            {/*            /!*<Drawer open onClose={}>*!/*/}

            {/*            /!*</Drawer>*!/*/}
            {/*        </div>)}*/}
            {/*</div>*/}
        </div>
    );
};

export default Header;