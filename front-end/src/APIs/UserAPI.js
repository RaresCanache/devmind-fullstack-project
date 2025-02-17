import {BASE_URL} from "./BASE-URL.js";

export const getUserById = (userId) => fetch(`${BASE_URL}/users/${userId}`);