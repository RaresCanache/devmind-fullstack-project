import {useSelector} from "react-redux";
import Calendar from "react-calendar";
import {useState} from "react";

const Dashboard = () => {
    const user = useSelector(state => state.user);
    const [date, setDate] = useState(new Date())

    return (
        <div>
            hei
        </div>
    )
};

export default Dashboard;