import "./HomeScreen.css";
import {useNavigate} from "react-router";

const HomeScreen = () => {
    const navigate = useNavigate();

    return (
        <div className="container">
            <h1 style={{fontSize: "60px"}}>Welcome to my app</h1>
            <div>
                <button onClick={() => navigate("login")}>
                    Login
                </button>
                <button onClick={() => navigate("register")}>
                    Register
                </button>
            </div>
        </div>
    );
};

export default HomeScreen;