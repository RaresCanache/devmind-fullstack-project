import {BASE_URL} from "./BASE-URL.js";

export const createFinancialPlan = (newFinancialPlan, bearerToken) => fetch(`${BASE_URL}/financial-plan/save`,{
    method: "POST",
    headers: {
        Authorization: `Bearer ${bearerToken}`,
        "Content-Type": "application/json",
    },
    body: JSON.stringify(newFinancialPlan),
})