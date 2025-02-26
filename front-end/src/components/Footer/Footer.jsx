import "./Footer.css";
import {Link} from "react-router";

const Footer = () => {

    return (
        <div className="footer-container">
            <div className="footer-container-inner">
                <p>Financial Calendar ©</p>
                <Link to="https://github.com/RaresCanache" style={{color: "white"}}>Github</Link>
            </div>
        </div>
    )
};

export default Footer;