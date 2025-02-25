import {useSearchParams} from "react-router";
import {useEffect, useState} from "react";
import {textFieldMuiStyles} from "../TextFieldMUIStyles/TextFieldMuiStyles.js";
import {FormControl, InputLabel, MenuItem, Select, TextField} from "@mui/material";
import "./AddExpense.css";

const AddExpense = () => {
    const [searchParams, setSearchParams] = useSearchParams();
    const [newExpense, setNewExpense] = useState({
        userId: searchParams.get("userId"),
        type: "",
        name: "",
        dateExpense: "",
        amount: "",
        frequency: ""
    })

    useEffect(() => {
        console.log(newExpense.type);
    }, [newExpense.type]);
    const handleChange = (event) => {
        setNewExpense({
            ...newExpense,
            [event.target.name]: event.target.value
        })
    };

    return (
        <div className="add-expense-container">
            <form>
                <select id="type" name="type" value={newExpense.type} onChange={handleChange} required>
                    <option value="" disabled>Select a type</option>
                    <option value="GROCERIES">Groceries</option>
                    <option value="TRANSPORT">Transport</option>
                    <option value="UTILITIES">Utilities</option>
                    <option value="SUBSCRIPTIONS">Subscriptions</option>
                    <option value="DEBT">Debt</option>
                    <option value="OTHER">Other</option>
                </select>
            </form>
        </div>
    );
};

export default AddExpense;