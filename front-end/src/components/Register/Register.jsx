import "./Register.css"
import {textFieldMuiStyles} from "../TextFieldMUIStyles/TextFieldMuiStyles.js";
import {TextField} from "@mui/material";
import {useState} from "react";
import {registerUser} from "../../APIs/UserAPI.js";
import {useNavigate} from "react-router";

const Register = () => {
    const [newUser, setNewUser] = useState({
        username: "",
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        avatarUrl: "",
    });
    const [loadingRegister, setLoadingRegister] = useState(false);
    const [successfullRegistration, setSuccessfullRegistration] = useState(false);
    const navigate = useNavigate();

    const handleChange = (event) => {
        setNewUser({
            ...newUser,
            [event.target.name]: event.target.value
        });
    };

    const handleRegister = async () => {
        setSuccessfullRegistration(false);
        setLoadingRegister(true);
        try {
            const response = await registerUser(newUser);

            if (!response.ok) {
                throw new Error("Could not register user");
            }
            const userResponse = await response.json();
            console.log(userResponse);

            setSuccessfullRegistration(true);
            setTimeout(() => navigate("/dashboard"), 1000);
        } catch (error) {
            console.error("Error registering user: ", error);
        } finally {
            setLoadingRegister(false);
        }
    };

    return (
        <div className="register-container">
            <div className="register-container-inner">
                <div className="register-text-area">
                    <TextField
                        label="Username"
                        type="text"
                        margin="normal"
                        required
                        name="username"
                        value={newUser.username}
                        onChange={handleChange}
                        sx={textFieldMuiStyles}
                    />
                    <TextField
                        label="Email"
                        type="email"
                        margin="normal"
                        required
                        name="email"
                        value={newUser.email}
                        onChange={handleChange}
                        sx={textFieldMuiStyles}
                    />
                    <TextField
                        label="Password"
                        type="password"
                        margin="normal"
                        required
                        name="password"
                        value={newUser.password}
                        onChange={handleChange}
                        sx={textFieldMuiStyles}
                    />
                </div>
                <div className="register-text-area">
                    <TextField
                        label="First name"
                        type="text"
                        margin="normal"
                        required
                        name="firstName"
                        value={newUser.firstName}
                        onChange={handleChange}
                        sx={textFieldMuiStyles}
                    />
                    <TextField
                        label="Last name"
                        type="text"
                        margin="normal"
                        required
                        name="lastName"
                        value={newUser.lastName}
                        onChange={handleChange}
                        sx={textFieldMuiStyles}
                    />
                    <TextField
                        label="Avatar URL"
                        type="text"
                        margin="normal"
                        required
                        name="avatarUrl"
                        value={newUser.avatarUrl}
                        onChange={handleChange}
                        sx={textFieldMuiStyles}
                    />
                </div>
            </div>
            <button className="home-button" onClick={handleRegister} disabled={loadingRegister || successfullRegistration}>
                {successfullRegistration ? <span style={{color: "chartreuse"}}>Successfully registered!</span> :
                loadingRegister ? "Registering..." : "Register"}
            </button>
        </div>

    );
};

export default Register;