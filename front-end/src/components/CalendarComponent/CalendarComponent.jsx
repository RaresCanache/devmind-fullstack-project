import Calendar from "react-calendar";
import {useEffect, useState} from "react";
import "./CalendarComponent.css";
import {useSelector} from "react-redux";
import {calculateSavings} from "../../APIs/SavingsAPI.js";

const CalendarComponent = () => {
    const [date, setDate] = useState(new Date());
    const user = useSelector(state => state.user.user);
    const token = useSelector(state => state.user.bearerToken);
    const startDate = user?.financialPlan?.startDate ? new Date(user.financialPlan.startDate) : new Date();
    const endDate = user?.financialPlan?.endDate ? new Date(user.financialPlan.endDate) : null;
    const [savings, setSavings] = useState([]);
    const [loadingSavings, setLoadingSavings] = useState(false);

    const handleSavings = async () => {
        setLoadingSavings(true);

        try {
            const response = await calculateSavings(user.id, 1, token);
            if (!response.ok) {
                throw new Error("No bank account with id: 1");
            }
            const savingsData = await response.json();
            setSavings(savingsData);
        } catch (error) {
            console.log("Error calculating savings: ", error)
        } finally {
            setLoadingSavings(false);
        }
    }

    useEffect(() => {
        console.log(savings);
    }, [savings]);

    useEffect(() => {
        handleSavings()
    }, []);

    return (
        <Calendar
            onChange={setDate}
            value={date}
            defaultView="month"
            defaultActiveStartDate={startDate}
            minDate={startDate}
            maxDate={endDate}
        />
    );
};

export default CalendarComponent;