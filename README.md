# Financial Calendar App
This application aims to improve financial management by helping users track and optimize their savings over time. The app calculates a personalized daily spending limit based on the user’s monthly income, estimated expenses, and savings goals, enabling better budget control and a structured financial plan.

---

# Demo Presentation

## Main Page

![image](https://github.com/user-attachments/assets/964fa17b-748a-4c93-9cf6-8c376fdc2ee2)

---

## Register Page

![image](https://github.com/user-attachments/assets/660ec009-0d1d-49a3-819c-3f5f243d2423)

---

## Add Bank Account

The account balance is the monthly income of a user

![image](https://github.com/user-attachments/assets/9281865f-64b3-4c90-9983-fd0be1828f16)

---

## Add Financial Plan

Starting date: 01-04-2025  
End date: 30-04-2025  
Amount to be saved: 1000 RON

![image](https://github.com/user-attachments/assets/3992174e-2767-4c18-8ce7-785d08284d0e)

---

## Add Expense/expenses

![image](https://github.com/user-attachments/assets/7790a532-d9c6-40a5-aa16-b9f4ac6623d1)

---

## Dashboard page

According to the implemented savings logic, the daily spending limit is shown for each day of the calendar for the selected period

![image](https://github.com/user-attachments/assets/807f4404-afc7-4676-b7c9-bc799c739c41)

---

# Backend API Documentation

## Overview
This backend API provides user management, expense tracking, bank account handling, financial planning, and transactions management (WIP). It includes specific CRUD endpoints for records related to users, expenses, bank accounts, financial plans, and transactions.
Additionaly, the user controller also handles Spring Security specific endpoints: `/login` and `/register`, which generate and validate a unique Bearer Token.

As mentioned previously, Spring Security helps with implementing the **JWT (JSON Web Token) authentication** approach, ensuring that only authenticated and authorized users can access protected resources. It is structured into several components, including configurations for JWT setup, request filtering, and authentication services.

---

## Internal Database Diagram

![image](https://github.com/user-attachments/assets/2dab8642-6a55-4354-9b17-fdaca1da278a)

---

## Technologies Used

* Back-End: Java, Spring Boot, Spring MVC, REST API, Spring Data with Hibernate ORM, Spring Security with JWT Authentication
* Front-End: Node.js, React.js, Javascript, HTML, CSS
* Database: MySQL Workbench

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

## How It Works

* The financial plan duration is calculated using **ChronoUnit.DAYS** to determine the total number of days.
* Total expenses are summed using a **reduce function**.
* The daily balance adjustment is computed by subtracting expenses and savings from the starting balance, then distributing the remaining amount evenly with **RoundingMode.HALF_UP**.
* Daily savings requirements are generated by iterating through the period and adjusting amounts for display on the **Front-End** calendar.
