# Financial Calendar app
This application aims to improve financial management by helping users track and optimize their savings over time. The app calculates a personalized daily spending limit based on the user’s monthly income, estimated expenses, and savings goals, enabling better budget control and a structured financial plan.

---

# Demo of the application
[Link to demo documentation](demo/README.md)

---

# Backend API Documentation

## Overview
This backend API provides user management, expense tracking, bank account handling, financial planning, and transactions management (WIP). It includes specific CRUD endpoints for records related to users, expenses, bank accounts, financial plans, and transactions.
Additionaly, the user controller also handles Spring Security specific endpoints: `/login` and `/register`, which generate and validate a unique Bearer Token.

As mentioned previously, Spring Security helps with implementing the **JWT (JSON Web Token) authentication** approach, ensuring that only authenticated and authorized users can access protected resources. It is structured into several components, including configurations for JWT setup, request filtering, and authentication services.

JWT authentication works by issuing a signed token upon successful user login. This token is included in subsequent requests, allowing the server to validate user identity without maintaining session state. A security filter intercepts requests, extracts, and verifies the token before granting access. Password encryption is also implemented to enhance security.

## Endpoints

### User Controller
**Base Path:** `/users`

| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/login` | Authenticates a user and returns a token |
| POST | `/register` | Registers a new user |
| GET | `/{userId}` | Fetches a user by ID |
| GET | `/email/{email}` | Fetches a user by email |
| GET | `/all` | Retrieves all users |
| PUT | `/update/{userId}` | Updates a user by ID |
| DELETE | `/delete/{userId}` | Deletes a user by ID |
| DELETE | `/delete-all` | Deletes all users |

---

### Expense Controller
**Base Path:** `/expenses`

| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/{expenseId}` | Fetches an expense by ID |
| GET | `/user/{userId}` | Retrieves all expenses for a user |
| POST | `/save` | Creates a new expense |
| PUT | `/update/{expenseId}` | Updates an expense by ID |
| DELETE | `/delete/{expenseId}` | Deletes an expense by ID |
| DELETE | `/delete-all/{userId}` | Deletes all expenses for a user |

---

### Bank Account Controller
**Base Path:** `/bank-accounts`

| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/{bankAccountId}` | Fetches a bank account by ID |
| GET | `/user/{userId}` | Retrieves all bank accounts for a user |
| POST | `/save` | Creates a new bank account |
| PUT | `/update/{bankAccountId}` | Updates a bank account by ID |
| DELETE | `/delete/{bankAccountId}` | Deletes a bank account by ID |
| DELETE | `/delete-all/{userId}` | Deletes all bank accounts for a user |

---

### Financial Plan Controller
**Base Path:** `/financial-plan`

| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/{userId}` | Fetches the financial plan for a user |
| POST | `/save` | Creates a new financial plan |
| PUT | `/update/{userId}` | Replaces the financial plan for a user |

---

### Transaction Controller
**Base Path:** `/transactions`

| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/{transactionId}` | Fetches a transaction by ID |
| GET | `/bank-account/{bankAccountId}` | Retrieves all transactions for a bank account |
| POST | `/save` | Creates a new transaction |
| DELETE | `/delete/{transactionId}` | Deletes a transaction by ID |
| DELETE | `/delete-all/{bankAccountId}` | Deletes all transactions for a bank account |

---

# Savings Logic

## Overview
The **SavingsServiceImpl** is responsible for computing the **daily required savings balance** based on a user's financial plan, expenses, and bank account balance. It ensures that the user stays on track with their financial goals by calculating a structured savings plan.

## Key Functionalities

1. **Calculate the Financial Plan Duration**  
   - Used `ChronoUnit.DAYS` to determine the total number of days in a financial plan, even if it spans multiple years.

2. **Compute Total Expenses**  
   - Utilized a **reduce function** to sum up the total amount of a user's expenses.

3. **Determine Daily Balance Adjustments**  
   - Computed the starting balance after subtracting total expenses and planned savings, then divided the remaining balance evenly over the financial plan duration. Moreover, I used **RoundingMode.HALF_UP** to prevent inaccuracies and negative values.

4. **Generate Daily Savings Requirements**  
   - Iterated through the resulted array, subtracting the daily amount computed for the planned period. These amounts will be rendered in the **Front-End**, on each tile of the calendar.
  
---

# Internal database diagram

![image](https://github.com/user-attachments/assets/2dab8642-6a55-4354-9b17-fdaca1da278a)

