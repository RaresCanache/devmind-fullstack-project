# devmind-fullstack-project
This application aims to improve financial management by helping users track and optimize their savings over time. The app calculates a personalized daily spending limit based on the user’s monthly income, estimated expenses, and savings goals, enabling better budget control and a structured financial plan.

# Backend API Documentation

## Overview
This backend API provides user management, expense tracking, bank account handling, financial planning, and transactions management (WIP). It includes specific CRUD endpoints for records related to users, expenses, bank accounts, financial plans, and transactions.
Additionaly, the user controller also handles Spring Security specific endpoints: /login and /register, which generate and validate a unique Bearer Token.

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
