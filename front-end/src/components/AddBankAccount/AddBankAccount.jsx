import {useEffect, useState} from "react";
import {useNavigate, useSearchParams} from "react-router";
import {addBankAccount} from "../../APIs/BankAccountAPI.js";
import {useSelector} from "react-redux";
import {TextField} from "@mui/material";
import {textFieldMuiStyles} from "../TextFieldMUIStyles/TextFieldMuiStyles.js";

const AddBankAccount = () => {
    const [searchParams] = useSearchParams();
    const [loadingBankAccount, setLoadingBankAccount] = useState(false);
    const [successfullBankAccount, setSuccessfullBankAccount] = useState(false);
    const navigate = useNavigate();
    const bearerToken = useSelector(state => state.user.bearerToken);
    const [newBankAccount, setNewBankAccount] = useState({
        userId: searchParams.get("userId"),
        bankName: "",
        iban: "",
        currency: "",
        balance: "",
    })

    useEffect(() => {
        console.log(newBankAccount)
    }, [newBankAccount]);
    const handleChange = (event) => {
        setNewBankAccount({
            ...newBankAccount,
            [event.target.name]: event.target.value
        })
    };

    const handleBankAccount = async () => {
        setSuccessfullBankAccount(false);
        setLoadingBankAccount(true);
        try {
            const response = await addBankAccount(newBankAccount, bearerToken);

            if (!response.ok) {
                throw new Error("Bank account can't be added");
            }
            setSuccessfullBankAccount(true);
            setTimeout(() => navigate("/dashboard"), 1000);
        } catch (error) {
            console.log("Bank account can't be added", error);
        } finally {
            setLoadingBankAccount(false);
        }
    };

    return (
        <div className="add-expense-container">
            <div className="add-expense-inner-container">
                <TextField
                    label="Name of bank account"
                    type="text"
                    margin="normal"
                    name="bankName"
                    required
                    value={newBankAccount.bankName}
                    onChange={handleChange}
                    sx={textFieldMuiStyles}
                />
                <TextField
                    label="IBAN"
                    type="text"
                    margin="normal"
                    name="iban"
                    required
                    value={newBankAccount.iban}
                    onChange={handleChange}
                    sx={textFieldMuiStyles}
                />
                <TextField
                    label="Currency"
                    type="text"
                    margin="normal"
                    name="currency"
                    required
                    value={newBankAccount.currency}
                    onChange={handleChange}
                    sx={textFieldMuiStyles}
                />
                <TextField
                    label="Bank account balance"
                    type="number"
                    margin="normal"
                    name="balance"
                    value={newBankAccount.balance}
                    onChange={handleChange}
                    sx={textFieldMuiStyles}
                    required
                    inputProps={{
                        min: 1
                    }}
                />
            </div>
            <button className="home-button" onClick={handleBankAccount} disabled={loadingBankAccount || successfullBankAccount}>
                {successfullBankAccount ? <span style={{color: "chartreuse"}}>Added bank account!</span> :
                    loadingBankAccount ? "Adding bank account..." : "Add bank account"}
            </button>
        </div>
    )
};

export default AddBankAccount;