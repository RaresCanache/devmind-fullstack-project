import "./DashboardMine.css";
import CalendarComponent from "../CalendarComponent/CalendarComponent.jsx";
import {Accordion, AccordionDetails, AccordionSummary} from "@mui/material";
import {ArrowDownward} from "@mui/icons-material";
import ExpensesGrid from "../ExpensesGrid/ExpensesGrid.jsx";
import {useDispatch, useSelector} from "react-redux";
import {getExpensesByUserId} from "../../APIs/ExpensesAPI.js";
import {useEffect} from "react";
import {setExpenses} from "../../redux/reducers/expensesReducer.js";

const DashboardMine = () => {
    const userId = useSelector(state => state.user.user.id);
    const bearerToken = useSelector(state => state.user.bearerToken);
    const dispatch = useDispatch();

    const handleExpenses = async () => {
        try {
            const response = await getExpensesByUserId(userId, bearerToken);
            if (!response.ok) {
                throw new Error(`No expenses assigned to user id: ${userId}`);
            }
            const expensesData = await response.json();

            dispatch(setExpenses({
                expenses: expensesData,
            }));
        } catch (error) {
            console.log("Error fetching expenses", error);
        }
    };

    useEffect(() => {
        handleExpenses();
    }, []);

    return (
        <div>
            <div className="dashboard-left-container">
                <Accordion>
                    <AccordionSummary aria-controls="panel1-content" id="panel1-header" expandIcon={<ArrowDownward />}>
                        <p style={{color: "blueviolet"}}>See expenses</p>
                    </AccordionSummary>
                    <AccordionDetails>
                        <ExpensesGrid/>
                    </AccordionDetails>
                </Accordion>
                <Accordion disabled>
                    <AccordionSummary aria-controls="panel2-content" id="panel2-header" expandIcon={<ArrowDownward />}>
                        <p style={{
                            color: "white",
                        }}>See transactions</p>
                    </AccordionSummary>
                </Accordion>
            </div>
            <div className="dashboard-right-container">
                <CalendarComponent/>
            </div>
        </div>
    )
};

export default DashboardMine;