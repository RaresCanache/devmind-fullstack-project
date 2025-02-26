import "./Register.css"
import {textFieldMuiStyles} from "../TextFieldMUIStyles/TextFieldMuiStyles.js";
import {TextField} from "@mui/material";
import {useState} from "react";
import {authenticateUser, getUserByEmail, registerUser} from "../../APIs/UserAPI.js";
import {useNavigate} from "react-router";
import {useDispatch} from "react-redux";
import {setUserAndToken} from "../../redux/reducers/userReducer.js";

const Register = () => {
    const dispatch = useDispatch();
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
            const registerResponse = await registerUser(newUser);

            if (!registerResponse.ok) {
                throw new Error("Could not register user");
            }
            setSuccessfullRegistration(true);

            const tokenResponse = await authenticateUser(newUser.email, newUser.password);
            const tokenData = await tokenResponse.json();

            const userResponse = await getUserByEmail(newUser.email, tokenData.token);
            const userData = await userResponse.json();

            dispatch(setUserAndToken({
                user: userData,
                bearerToken: tokenData.token
            }))
            setTimeout(() => navigate(`/add-bank-account?userId=${userData.id}`), 2000);
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