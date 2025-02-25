import {BASE_URL} from "./BASE-URL.js";

export const getBankAccountsByUserId = (userId, bearerToken) => fetch(`${BASE_URL}/bank-accounts/user/${userId}`, {
    headers: {
        Authorization: `Bearer ${bearerToken}`,
    }
})

export const addBankAccount = (newBankAccount, bearerToken) => fetch(`${BASE_URL}/bank-accounts/save`, {
    method: "POST",
    headers: {
        Authorization: `Bearer ${bearerToken}`,
        "Content-Type": "application/json"
    },
    body: JSON.stringify(newBankAccount),
})


