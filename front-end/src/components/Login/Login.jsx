import {useDispatch} from "react-redux";
import "./Login.css";
import {TextField} from "@mui/material";
import {useState} from "react";
import {setUserAndToken} from "../../redux/reducers/userReducer.js";
import {authenticateUser, getUserByEmail} from "../../APIs/UserAPI.js";
import {textFieldMuiStyles} from "../TextFieldMUIStyles/TextFieldMuiStyles.js";
import {useNavigate} from "react-router";

const Login = () => {
    const dispatch = useDispatch();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loadingLogin, setLoadingLogin] = useState(false);
    const [successfullLogin, setSuccessfullLogin] = useState(false);
    const navigate = useNavigate();

    const handleClick = async () => {
        setLoadingLogin(true);
        setSuccessfullLogin(false);

        try {
            const tokenResponse = await authenticateUser(email, password);
            if (!tokenResponse.ok) {
                throw new Error("Invalid credentials");
            }
            const tokenData = await tokenResponse.json();

            console.log(tokenData.token);

            const userResponse = await getUserByEmail(email, tokenData.token);
            if (!userResponse.ok) {
                throw new Error(`No existing user with email: ${email}`);
            }
            const userData = await userResponse.json();
            console.log(userData);

            dispatch(setUserAndToken({
                user: userData,
                bearerToken: tokenData.token,
            }))
            setSuccessfullLogin(true);
            setTimeout(() => navigate("/dashboard"), 2000);
        } catch (error) {
            console.error("Error authenticating user: ", error);
        } finally {
            setLoadingLogin(false);
        }
    };

    return (
            <div className="login-container">
                <div className="login-container-inner">
                    <TextField
                        label="Email"
                        type="email"
                        margin="normal"
                        required
                        value={email}
                        onChange={event => setEmail(event.target.value)}
                        sx={textFieldMuiStyles}
                    />
                    <TextField
                        label="Password"
                        type="password"
                        margin="normal"
                        required
                        value={password}
                        onChange={event => setPassword(event.target.value)}
                        sx={textFieldMuiStyles}
                    />
                </div>
                <button className="login-button" onClick={handleClick}>
                    {successfullLogin ? <span style={{color: "chartreuse"}}>Logged in</span>
                        : loadingLogin ? "Logging in..." : "Login"}
                </button>
            </div>
    );
};

export default Login;