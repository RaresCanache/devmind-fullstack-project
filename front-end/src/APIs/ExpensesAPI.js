import {BASE_URL} from "./BASE-URL.js";

export const getExpensesByUserId = async (userId, bearerToken) => fetch(`${BASE_URL}/expenses/user/${userId}`, {
    headers: {
        Authorization: `Bearer ${bearerToken}`,
    }
})

export const addExpense = async (newExpense, bearerToken) => fetch(`${BASE_URL}/expenses/save`, {
    method: "POST",
    headers: {
        Authorization: `Bearer ${bearerToken}`,
        "Content-Type": "application/json"
    },
    body: JSON.stringify(newExpense)
})

