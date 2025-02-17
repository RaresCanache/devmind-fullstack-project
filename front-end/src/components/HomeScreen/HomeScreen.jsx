import "./HomeScreen.css";
import {useNavigate} from "react-router";

const HomeScreen = () => {
    const navigate = useNavigate();

    return (
        <div className="home-container">
            <h1 style={{fontSize: "60px"}}>Welcome to my app</h1>
            <div>
                <button className="home-button" onClick={() => navigate("login")}>
                    Login
                </button>
                <button className="home-button" onClick={() => navigate("register")}>
                    Register
                </button>
            </div>
        </div>
    );
};

export default HomeScreen;