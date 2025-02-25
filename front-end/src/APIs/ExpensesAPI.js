import {BASE_URL} from "./BASE-URL.js";

export const getExpensesByUserId = async (userId, bearerToken) => fetch(`${BASE_URL}/expenses/user/${userId}`, {
    headers: {
        Authorization: `Bearer ${bearerToken}`,
    }
})

