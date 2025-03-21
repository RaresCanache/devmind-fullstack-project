import Calendar from "react-calendar";
import {useEffect, useState} from "react";
import "./CalendarComponent.css";
import {useSelector} from "react-redux";
import {calculateSavings} from "../../APIs/SavingsAPI.js";

const CalendarComponent = () => {
    const [date, setDate] = useState(new Date());
    const bankAccount = useSelector(state => state.bankAccounts.bankAccounts);
    const financialPlan = useSelector(state => state.user.financialPlan)
    const user = useSelector(state => state.user.user);
    const token = useSelector(state => state.user.bearerToken);
    const startDate = financialPlan?.startDate ? new Date(financialPlan.startDate) : new Date();
    const endDate = financialPlan?.endDate ? new Date(financialPlan.endDate) : null;
    const [savings, setSavings] = useState([]);

    useEffect(() => {
            handleSavings();
    }, [bankAccount]);

    const formatDate = (date) => {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');

        return `${year}-${month}-${day}`;
    }

    const handleSavings = async () => {
        try {
            const response = await calculateSavings(user.id, bankAccount[0].id, token);
            if (!response.ok) {
                throw new Error(`No bank account with id: ${bankAccount[0].id}`);
            }
            const savingsData = await response.json();

            const savingsMap = {};
            let currentDate = new Date(startDate);
            //Maparea pe date a array-ului cu Integers intors din back-end
            for (let i = 0; i < savingsData.length; i++) {
                // functia formatDate schimba format-ul in "YYYY-MM-DD", pentru maparea in Calendar a datelor
                const formattedDate = formatDate(currentDate);
                savingsMap[formattedDate] = savingsData[i];
                currentDate.setDate(currentDate.getDate() + 1);
            }
            setSavings(savingsMap);
        } catch (error) {
            console.log("Error calculating savings: ", error)
        }
    }

    const handleTileContent = ({date, view}) => {
        if (view !== "month") return null;

        const formattedDate = formatDate(date);

        return savings[formattedDate] ? (
            <div style={{
                color: "darkblue",
                fontWeight: "bold",
                fontSize: "1.2em"
            }}>
                {savings[formattedDate]}
            </div>
        ) : 0
    };

    return (
        <Calendar
            onChange={setDate}
            value={date}
            defaultView="month"
            defaultActiveStartDate={startDate}
            minDate={startDate}
            maxDate={endDate}
            tileContent={handleTileContent}
        />
    );
};

export default CalendarComponent;