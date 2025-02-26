import {createSlice} from "@reduxjs/toolkit";

const userSlice = createSlice({
    name: "user",
    initialState: {
        user: null,
        bearerToken: null,
        financialPlan: null
    },
    reducers: {
        setUserAndToken: (state, action) => {
            state.user = action.payload.user;
            state.bearerToken = action.payload.bearerToken;
        },
        setFinancialPlan: (state, action) => {
            state.financialPlan = action.payload.financialPlan
        },
    }
})

export const {setUserAndToken, setFinancialPlan} = userSlice.actions;

export default userSlice.reducer;