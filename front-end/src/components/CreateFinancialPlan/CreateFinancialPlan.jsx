import {useState} from "react";
import {useNavigate, useSearchParams} from "react-router";
import {TextField} from "@mui/material";
import {textFieldMuiStyles} from "../TextFieldMUIStyles/TextFieldMuiStyles.js";
import {createFinancialPlan} from "../../APIs/financialPlan.js";
import {useSelector} from "react-redux";

const CreateFinancialPlan = () => {
    const [searchParams] = useSearchParams();
    const [loadingFinancialPlan, setLoadingFinancialPlan] = useState(false);
    const [successfullFinancialPlan, setSuccessfullFinancialPlan] = useState(false);
    const bearerToken = useSelector(state => state.user.bearerToken);
    const navigate = useNavigate();
    const [newFinancialPlan, setNewFinancialPlan] = useState({
        userId: searchParams.get("userId"),
        type: "",
        savings: "",
        startDate: "",
        endDate: "",
    })

    const handleChange = (event) => {
        setNewFinancialPlan({
            ...newFinancialPlan,
            [event.target.name]: event.target.value
        })
    };

    const handleFinancialPlan = async () => {
        setSuccessfullFinancialPlan(false);
        setLoadingFinancialPlan(true);
        try {
            const response = await createFinancialPlan(newFinancialPlan, bearerToken);
            if (!response.ok) {
                throw new Error("Financial plan can't be created");
            }
            setSuccessfullFinancialPlan(true);
            setTimeout(() => navigate("/login"), 1000);
        } catch (error) {
            console.log("Financial plan can't be created", error);
        } finally {
            setLoadingFinancialPlan(false);
        }
    };

    return (
        <div className="add-expense-container">
            <div className="add-expense-inner-container">
                <form>
                    <select id="type"
                            name="type"
                            value={newFinancialPlan.type}
                            onChange={handleChange}
                            required
                            className="add-expense-select">
                        <option value="" disabled style={{color: "gray"}}>Select a type</option>
                        <option value="PERCENT">Percent</option>
                        <option value="FIXED">Fixed</option>
                    </select>
                </form>
                <TextField
                    label="Amount to be saved"
                    type="number"
                    margin="normal"
                    name="savings"
                    value={newFinancialPlan.savings}
                    onChange={handleChange}
                    sx={textFieldMuiStyles}
                    required
                    inputProps={{
                        min: 1
                    }}
                />
                <TextField
                    label="Select a starting date"
                    type="date"
                    name="startDate"
                    margin="normal"
                    value={newFinancialPlan.dateExpense || ""}
                    onChange={handleChange}
                    sx={{...textFieldMuiStyles, width: "100%"}}
                    required
                    slotProps={{inputLabel: {shrink: true}}}
                />
                <TextField
                    label="Select an ending date"
                    type="date"
                    name="endDate"
                    margin="normal"
                    value={newFinancialPlan.dateExpense || ""}
                    onChange={handleChange}
                    sx={{...textFieldMuiStyles, width: "100%"}}
                    required
                    slotProps={{inputLabel: {shrink: true}}}
                />
            </div>
            <button className="home-button" onClick={handleFinancialPlan}
                    disabled={loadingFinancialPlan || successfullFinancialPlan}>
                {successfullFinancialPlan ? <span style={{color: "chartreuse"}}>Created financial plan!</span> :
                    loadingFinancialPlan ? "Adding financial plan..." : "Add financial plan"}
            </button>
        </div>
    );
};

export default CreateFinancialPlan;