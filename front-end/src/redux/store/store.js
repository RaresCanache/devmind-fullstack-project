import {configureStore} from "@reduxjs/toolkit";
import userReducer from "../reducers/userReducer.js";
import expensesReducer from "../reducers/expensesReducer.js";
import bankAccountsReducer from "../reducers/bankAccountsReducer.js";

export const store = configureStore({
    reducer: {
        user: userReducer,
        expenses: expensesReducer,
        bankAccounts: bankAccountsReducer,
    },
})