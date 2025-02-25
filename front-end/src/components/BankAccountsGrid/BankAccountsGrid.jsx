import {useSelector} from "react-redux";
import {useNavigate} from "react-router";
import {DataGrid, GridAddIcon, GridToolbarContainer} from "@mui/x-data-grid";
import {Button} from "@mui/material";
import {useEffect} from "react";

const BankAccountsGrid = () => {
    const userId = useSelector(state => state.user.user.id);
    const bankAccounts = useSelector(state => state.bankAccounts.bankAccounts);
    const navigate = useNavigate();

    const bankAccountsColumns = [
        {field: "id", headerName: "Id", width: 10},
        {field: "bankName", headerName: "Name", width: 150},
        {field: "balance", headerName: "Balance", width: 120},
        {field: "currency", headerName: "Currency", width: 80},
    ]

    const customToolbar = () => (
        <GridToolbarContainer>
            <Button color="primary" startIcon={<GridAddIcon />} onClick={() => navigate(`/add-bank-account?userId=${userId}`)}>
                Add bank account
            </Button>
        </GridToolbarContainer>
    )

    return (
        <div>
            <DataGrid columns={bankAccountsColumns} rows={bankAccounts} slots={{toolbar: customToolbar}}/>
        </div>
    );
};

export default BankAccountsGrid;