import "./HomeScreen.css";
import {useNavigate} from "react-router";

const HomeScreen = () => {
    const navigate = useNavigate();

    return (
        <div className="app-container">
            <div className="blurred-background"/>
            <div className="content">
                <div className="home-container">
                    <h1 style={{fontSize: "60px"}}>Financial Calendar App</h1>
                    <div>
                        <button className="home-button" onClick={() => navigate("login")}>
                            Login
                        </button>
                        <button className="home-button" onClick={() => navigate("register")}>
                            Register
                        </button>
                    </div>
                </div>
            </div>
        </div>

    );
};

export default HomeScreen;