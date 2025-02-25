import {useSelector} from "react-redux";
import {useEffect} from "react";
import {DataGrid, GridAddIcon, GridToolbarContainer} from "@mui/x-data-grid";
import {Button} from "@mui/material";
import {useNavigate} from "react-router";

const ExpensesGrid = () => {
    const userId = useSelector(state => state.user.user.id);
    const expenses = useSelector(state => state.expenses.expenses);
    const navigate = useNavigate();

    const expensesColumns = [
        {field: "id", headerName: "Id", width: 10},
        {field: "type", headerName: "Type", width: 150},
        {field: "name", headerName: "Name", width: 120},
        {field: "amount", headerName: "Amount", width: 80},
        {field: "dateExpense", headerName: "Date of expense", width: 150},
        {field: "frequency", headerName: "Frequency"}
    ]

    const customToolbar = () => (
        <GridToolbarContainer>
            <Button color="primary" startIcon={<GridAddIcon />} onClick={() => navigate(`/add-expense?userId=${userId}`)}>
                Add expense
            </Button>
        </GridToolbarContainer>
        )

    return (
        <div>
            <DataGrid columns={expensesColumns} rows={expenses} slots={{toolbar: customToolbar}}/>
        </div>
    );
};

export default ExpensesGrid;