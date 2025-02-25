import {createSlice} from "@reduxjs/toolkit";

const bankAccountsSlice = createSlice({
    name: "bankAccounts",
    initialState: {
        bankAccounts: [],
    },
    reducers: {
        setBankAccounts: (state, action) => {
            state.bankAccounts = action.payload.bankAccounts;
        },
    }
})

export const {setBankAccounts} = bankAccountsSlice.actions;

export default bankAccountsSlice.reducer;