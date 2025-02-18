import {useDispatch} from "react-redux";
import "./Login.css";
import {TextField} from "@mui/material";
import {useState} from "react";
import {setUser} from "../../redux/reducers/userReducer.js";
import {getUserById} from "../../APIs/UserAPI.js";

const Login = () => {
    const [userId, setUserId] = useState(0);
    const dispatch = useDispatch();

    const handleClick = async () => {
        try {
            const response = await getUserById(userId);
            const data = await response.json();

            dispatch(setUser(data));
            console.log("Fetched User:", data);
        } catch (error) {
            console.error("Error fetching user:", error);
        }
    };

    return (
        <div className="login-container">
            <TextField
                label="Email"
                type="email"
                margin="normal"
                required="required"
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
                required="required"
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
                <TextField
                    id="outlined-number"
                    label="User ID"
                    type="number"
                    value={userId}
                    onChange={(event) => setUserId(event.target.value)}
                    slotProps={{
                        inputLabel: {
                            shrink: true,
                        },
                    }}
                    sx={{
                        "& .MuiFormLabel-root": {
                            color: "blueviolet",
                        },
                        "& .MuiInputBase-input": {
                            color: "white",
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
                                color: "blueviolet"
                            },
                        }
                    }}/>
            </div>
        </div>
    );
};

export default Login;