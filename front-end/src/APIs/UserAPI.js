import {BASE_URL} from "./BASE-URL.js";

export const authenticateUser = async (email, password) => fetch(`${BASE_URL}/users/login`, {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
    },
    body: JSON.stringify({email, password}),
});

export const getUserByEmail = async (email, bearerToken) => fetch(`${BASE_URL}/users/email/${email}`, {
    headers: {
        Authorization: `Bearer ${bearerToken}`
    },
});

export const registerUser = async (newUser) => fetch(`${BASE_URL}/users/register`, {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify(newUser),
})