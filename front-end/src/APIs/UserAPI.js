import {BASE_URL} from "./BASE-URL.js";

export const getUserById = (userId) => fetch(`${BASE_URL}/users/${userId}`);

export const placeholder = (userId) => fetch(`https://jsonplaceholder.typicode.com/users/${userId}`);