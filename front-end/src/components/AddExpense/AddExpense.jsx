import {useNavigate, useSearchParams} from "react-router";
import {useEffect, useState} from "react";
import {textFieldMuiStyles} from "../TextFieldMUIStyles/TextFieldMuiStyles.js";
import "./AddExpense.css";
import {TextField} from "@mui/material";
import {useSelector} from "react-redux";
import {addExpense} from "../../APIs/ExpensesAPI.js";

const AddExpense = () => {
    const [searchParams, setSearchParams] = useSearchParams();
    const financialPlan = useSelector(state => state.user.user.financialPlan);
    const bearerToken = useSelector(state => state.user.bearerToken);
    const [loadingExpense, setLoadingExpense] = useState(false);
    const [successfullAdd, setSuccessfullAdd] = useState(false);
    const navigate = useNavigate();

    const [newExpense, setNewExpense] = useState({
        userId: searchParams.get("userId"),
        type: "",
        name: "",
        dateExpense: "",
        amount: "",
        frequency: "",
    })

    const handleChange = (event) => {
        setNewExpense({
            ...newExpense,
            [event.target.name]: event.target.value
        })
    };

    const handleExpense = async () => {
        setSuccessfullAdd(false);
        setLoadingExpense(true);
        try {
            const response = await addExpense(newExpense, bearerToken);

            if (!response.ok) {
                throw new Error("Expense can't be added");
            }
            setSuccessfullAdd(true);
            setTimeout(() => navigate("/dashboard"), 1000);
        } catch (error) {
            console.log("Expense can't be added", error);
        } finally {
            setLoadingExpense(false);
        }
    };

    return (
        <div className="add-expense-container">
            <div className="add-expense-inner-container">
                <form>
                    <select id="type" name="type" value={newExpense.type} onChange={handleChange} required
                            className="add-expense-select">
                        <option value="" disabled style={{color: "gray"}}>Select a type</option>
                        <option value="GROCERIES">Groceries</option>
                        <option value="TRANSPORT">Transport</option>
                        <option value="UTILITIES">Utilities</option>
                        <option value="SUBSCRIPTIONS">Subscriptions</option>
                        <option value="DEBT">Debt</option>
                        <option value="OTHER">Other</option>
                    </select>
                </form>
                <TextField
                    label="Name of expense"
                    type="text"
                    margin="normal"
                    name="name"
                    value={newExpense.name}
                    onChange={handleChange}
                    sx={textFieldMuiStyles}
                />
                <TextField
                    label="Select a date"
                    type="date"
                    name="dateExpense"
                    margin="normal"
                    value={newExpense.dateExpense || ""}
                    onChange={handleChange}
                    sx={{...textFieldMuiStyles, width: "100%"}}
                    required
                    slotProps={{inputLabel: {shrink: true}}}
                    inputProps={{
                        min: financialPlan.startDate,
                        max: financialPlan.endDate,
                    }}
                />
                <TextField
                    label="Amount of expense"
                    type="number"
                    margin="normal"
                    name="amount"
                    value={newExpense.amount}
                    onChange={handleChange}
                    sx={textFieldMuiStyles}
                    required
                    inputProps={{
                        min: 1
                    }}
                />
                <form>
                    <select id="frequency" name="frequency" value={newExpense.frequency} onChange={handleChange}
                            required
                            className="add-expense-select">
                        <option value="" disabled style={{color: "gray"}}>Select a frequency</option>
                        <option value="DAILY">Daily</option>
                        <option value="WEEKLY">Weekly</option>
                        <option value="MONTHLY">Monthly</option>
                    </select>
                </form>
            </div>
            <button className="home-button" onClick={handleExpense} disabled={loadingExpense || successfullAdd}>
                {successfullAdd ? <span style={{color: "chartreuse"}}>Added expense!</span> :
                    loadingExpense ? "Adding expense..." : "Add expense"}
            </button>
        </div>
    );
};

export default AddExpense;