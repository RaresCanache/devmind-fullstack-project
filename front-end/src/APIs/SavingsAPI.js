import {BASE_URL} from "./BASE-URL.js";

export const calculateSavings = async (userId, bankAccountId, bearerToken) =>
    fetch(`${BASE_URL}/savings/calculate?userId=${userId}&bankAccountId=${bankAccountId}`, {
    headers: {
        Authorization: `Bearer ${bearerToken}`
    }
})