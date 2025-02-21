import {useDispatch} from "react-redux";
import "./Login.css";
import {TextField} from "@mui/material";
import {useState} from "react";
import {setUserAndToken} from "../../redux/reducers/userReducer.js";
import {authenticateUser, getUserByEmail} from "../../APIs/UserAPI.js";

const Login = () => {
    const dispatch = useDispatch();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleClick = async () => {
        try {
            const tokenResponse = await authenticateUser(email, password);
            const token = await tokenResponse.text();

            const userResponse = await getUserByEmail(email, token);
            const userData = await userResponse.json();

            dispatch(setUserAndToken({
                user: userData,
                bearerToken: token,
            }))

        } catch (error) {
            console.error("Error authenticating user: ", error);
        }
    };

    return (
            <div className="login-container">
                <TextField
                    label="Email"
                    type="email"
                    margin="normal"
                    required
                    value={email}
                    onChange={event => setEmail(event.target.value)}
                    sx={{
                        "& .MuiInputLabel-root": {
                            color: "blueviolet",
                        },
                        "& .MuiInputBase-input": {
                            color: "blueviolet",
                        },
                        "& .MuiOutlinedInput-root": {
                            "& fieldset": {
                                border: "3px solid black",
                            },
                            "&:hover fieldset": {
                                borderColor: "blueviolet",
                            },
                            "&.Mui-focused fieldset": {
                                borderColor: "blueviolet",
                            },
                        },
                    }}
                />
                <TextField
                    label="Password"
                    type="password"
                    margin="normal"
                    required
                    value={password}
                    onChange={event => setPassword(event.target.value)}
                    sx={{
                        "& .MuiInputLabel-root": {
                            color: "blueviolet"
                        },
                        "& .MuiInputBase-input": {
                            color: "blueviolet",
                        },
                        "& .MuiOutlinedInput-root": {
                            "& fieldset": {
                                border: "3px solid black",
                            },
                            "&:hover fieldset": {
                                borderColor: "blueviolet",
                            },
                            "&.Mui-focused fieldset": {
                                borderColor: "blueviolet",
                            },
                        },
                    }}
                />
                <div style={{
                    margin: "15px"
                }}>
                    <button className="login-button" onClick={handleClick}>
                        Login
                    </button>
                </div>
            </div>
    );
};

export default Login;