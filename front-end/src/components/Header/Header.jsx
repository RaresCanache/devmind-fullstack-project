import {NavLink} from "react-router";
import "./Header.css"
import {useSelector} from "react-redux";

const Header = () => {
    const user = useSelector(state => state.user.user);

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
        </div>
    );
};

export default Header;