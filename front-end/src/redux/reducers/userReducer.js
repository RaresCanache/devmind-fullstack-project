import {createSlice} from "@reduxjs/toolkit";

const userSlice = createSlice({
    name: "user",
    initialState: {
        user: null,
        bearerToken: null,
    },
    reducers: {
        setUserAndToken: (state, action) => {
            state.user = action.payload.user;
            state.bearerToken = action.payload.bearerToken;
        },
    }
})

export const {setUserAndToken} = userSlice.actions;

export default userSlice.reducer;