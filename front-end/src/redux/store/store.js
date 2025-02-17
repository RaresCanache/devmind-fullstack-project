import {configureStore} from "@reduxjs/toolkit";
import userReducer from "../reducers/userReducer.js";

export const store = configureStore({
    reducer: {
        user: userReducer,
    },
})