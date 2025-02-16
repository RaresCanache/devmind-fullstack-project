import './App.css'
import {Route, Routes} from "react-router";
import HomeScreen from "./components/HomeScreen.jsx";

function App() {

    return (
        <>
            <Routes>
                <Route path="" element={<HomeScreen/>}/>
                <Route path="login"/>
            </Routes>
        </>
    );
}

export default App
