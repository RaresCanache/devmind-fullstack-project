import './App.css'
import {Route, Routes} from "react-router";
import HomeScreen from "./components/HomeScreen/HomeScreen.jsx";
import Login from "./components/Login/Login.jsx";
import Header from "./components/Header/Header.jsx";
import Register from "./components/Register/Register.jsx";
import DashboardMine from "./components/Dashboard/DashboardMine.jsx";

function App() {

    return (
        <>
            <Header/>
            <Routes>
                <Route path="" element={<HomeScreen/>}/>
                <Route path="login" element={<Login/>}/>
                <Route path="register" element={<Register/>}/>
                <Route path="dashboard" element={<DashboardMine/>}/>
            </Routes>
        </>
    );
}

export default App
