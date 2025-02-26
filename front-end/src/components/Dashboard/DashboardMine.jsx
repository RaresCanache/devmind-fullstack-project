import "./DashboardMine.css";
import CalendarComponent from "../CalendarComponent/CalendarComponent.jsx";
import {Accordion, AccordionDetails, AccordionSummary} from "@mui/material";
import {ArrowDownward} from "@mui/icons-material";
import ExpensesGrid from "../ExpensesGrid/ExpensesGrid.jsx";
import {useDispatch, useSelector} from "react-redux";
import {getExpensesByUserId} from "../../APIs/ExpensesAPI.js";
import {useEffect} from "react";
import {setExpenses} from "../../redux/reducers/expensesReducer.js";
import BankAccountsGrid from "../BankAccountsGrid/BankAccountsGrid.jsx";
import {getBankAccountsByUserId} from "../../APIs/BankAccountAPI.js";
import {setBankAccounts} from "../../redux/reducers/bankAccountsReducer.js";
import FinancialPlanGrid from "../FinancialPlanGrid/FinancialPlanGrid.jsx";

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

    const handleBankAccounts = async () => {
        try {
            const response = await getBankAccountsByUserId(userId, bearerToken);

            if (!response.ok) {
                throw new Error(`No bank accounts assigned to user id: ${userId}`);
            }
            const bankAccountsData = await response.json();
            dispatch(setBankAccounts({
                bankAccounts: bankAccountsData,
            }))
        } catch (error) {
            console.log("Error fetching bank accounts", error);
        }
    }

    useEffect(() => {
        handleExpenses();
        handleBankAccounts();
    }, []);

    return (
        <div>
            <div className="dashboard-left-container">
                <Accordion>
                    <AccordionSummary aria-controls="panel2-content" id="panel2-header" expandIcon={<ArrowDownward />}>
                        <p style={{color: "blueviolet"}}>See expenses</p>
                    </AccordionSummary>
                    <AccordionDetails>
                        <ExpensesGrid/>
                    </AccordionDetails>
                </Accordion>
                <Accordion>
                    <AccordionSummary aria-controls="panel3-content" id="panel3-header" expandIcon={<ArrowDownward />}>
                        <p style={{color: "blueviolet"}}>See bank accounts</p>
                    </AccordionSummary>
                    <AccordionDetails>
                        <BankAccountsGrid/>
                    </AccordionDetails>
                </Accordion>
                <Accordion disabled>
                    <AccordionSummary aria-controls="panel4-content" id="panel4-header" expandIcon={<ArrowDownward />}>
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