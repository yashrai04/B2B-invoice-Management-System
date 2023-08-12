import React, { useState, useEffect } from 'react';
import { DataGrid } from '@material-ui/data-grid';
import EditDialog from './EditDialog';
import AdvancedSearchDialog from './AdvancedSearchDialog';
import axios from 'axios';
import Button from '@material-ui/core/Button';
function DataGridComponent({ searchQuery }) {
  const [rowData, setRowData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [refresh, setRefresh] = useState(false);
  const [selectedRows, setSelectedRows] = useState([]);
  const [isEditDialogOpen, setIsEditDialogOpen] = useState(false);
  const [isAdvancedSearchOpen, setIsAdvancedSearchOpen] = useState(false);
  const [advancedSearchData, setAdvancedSearchData] = useState(null);
  const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);

  const columns = [
    { field: 'Sl_no', headerName: 'Sl_no', width: 120 },
    { field: 'customerOrderID', headerName: 'Customer Order ID', width: 200 },
    { field: 'salesOrg', headerName: 'Sales Org', width: 160 },
    { field: 'distributionChannel', headerName: 'Distribution Channel', width: 240 },
    { field: 'releasedCreditValue', headerName: 'Released Credit Value', width: 200 },
    { field: 'companyCode', headerName: 'Company Code', width: 160 },
    { field: 'orderCreationDate', headerName: 'Order Creation Date', width: 200 },
    { field: 'soldToParty', headerName: 'Sold To Party', width: 160 },
    { field: 'orderAmount', headerName: 'Order Amount', width: 160 },
    { field: 'orderCurrency', headerName: 'Order Currency', width: 180 },
    { field: 'customerNumber', headerName: 'Customer Number', width: 180 },
    { field: 'amountInUsd', headerName: 'Amount in USD', width: 160 },
    { field: 'uniqueCustNumber', headerName: 'Unique Customer Number', width: 220 },
  ];

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/h2h_milestone-3/fetchUsers?customerId=${searchQuery}`);
        const jsonData = response.data;

        // Apply advanced search filter if data is available
        let filteredData = jsonData;
        if (advancedSearchData) {
          const { customerOrderId, customerNumber, salesOrg } = advancedSearchData;
          filteredData = jsonData.filter((row) => {
            const rowSalesOrg = String(row.salesOrg); // Convert salesOrg to string
            return (
              row.customerOrderID.toString().includes(customerOrderId) &&
              row.customerNumber.toString().includes(customerNumber) &&
              rowSalesOrg.includes(salesOrg)
            );
          });
        }

        const rowsWithId = filteredData.map((row, index) => ({ id: index + 1, ...row }));
        setRowData(rowsWithId);
        if (searchQuery) {

          const filteredData1 = jsonData.filter((row) =>
            row.customerOrderID.toString().includes(searchQuery)
          );

          const rowsWithId1 = filteredData1.map((row, index) => ({ id: index + 1, ...row }));
          setRowData(rowsWithId1);
        }
        setLoading(false);
      } catch (error) {
        console.error('Error fetching data:', error);
        setLoading(false);
      }
    };

    fetchData();
  }, [searchQuery, refresh, advancedSearchData]);

  const handleRefresh = () => {
    setRefresh(!refresh);
  };

  const handleEdit = () => {
    setIsEditDialogOpen(true);
  };

  const handleCloseEditDialog = () => {
    setIsEditDialogOpen(false);
  };

  const handleSaveEditDialog = (editedRowData) => {
    axios
      .put('h2h_milestone-3/updateUser', editedRowData)
      .then((response) => {
        console.log(response.data);
        setRowData((prevRowData) => {
          const updatedRowData = [...prevRowData];
          const rowIndex = updatedRowData.findIndex((row) => row.id === editedRowData.id);
          if (rowIndex !== -1) {
            updatedRowData[rowIndex] = editedRowData;
          }
          return updatedRowData;
        });
        setIsEditDialogOpen(false);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const handleAdvancedSearch = () => {
    setIsAdvancedSearchOpen(true);
  };

  const handleCloseAdvancedSearch = () => {
    setIsAdvancedSearchOpen(false);
  };

  const handleSearch = (searchData) => {
    setAdvancedSearchData(searchData);
  };

  const handleDelete = () => {
    setShowDeleteConfirmation(true);
  };

  const handleDeleteConfirmation = () => {
    // Perform deletion logic here
    const updatedRowData = rowData.filter((row) => !selectedRows.includes(row.id));
    setRowData(updatedRowData);
    setSelectedRows([]);
    setShowDeleteConfirmation(false);
  };

  const handleDeleteCancel = () => {
    setShowDeleteConfirmation(false);
  };

  const handlePrediction = () => {
    // Handle prediction logic here
  };

  return (
    <div>
      <Button
        variant="contained"
        color="secondary"
        onClick={handleAdvancedSearch}
        style={{
          width: '150px',
          height: '45px',
          position: 'absolute',
          top: '30%',
          right: '10px',
          backgroundColor: 'lightgreen',
        }}
      >
        Advanced Search
      </Button>

      <div style={{ height: 400, width: '100%', background: "grey" }}>
        <DataGrid
          rows={rowData}
          columns={columns}
          checkboxSelection
          onSelectionModelChange={(selectionModel) => setSelectedRows(selectionModel)}
        />
      </div>
      <Button
        variant="contained"
        color="secondary"
        onClick={handleRefresh}
        style={{ marginLeft: '10px', borderRadius: '4px', backgroundColor: 'orange' }}
      >
        Refresh
      </Button>
      <Button
        variant="contained"
        color="secondary"
        onClick={handleEdit}
        style={{ marginLeft: '10px', borderRadius: '4px', backgroundColor: 'orange' }}
        disabled={selectedRows.length !== 1}
      >
        Edit
      </Button>
      <Button
        variant="contained"
        color="secondary"
        onClick={handleDelete}
        style={{ marginLeft: '10px', borderRadius: '4px', backgroundColor: 'orange' }}
        disabled={selectedRows.length === 0}
      >
        Delete
      </Button>
      <Button
        variant="contained"
        color="secondary"
        onClick={handlePrediction}
        style={{ marginLeft: '10px', borderRadius: '4px', backgroundColor: '#fc7500' }}
        disabled={selectedRows.length === 0}
      >
        Predict
      </Button>
      {showDeleteConfirmation && (
        <div className="delete-confirmation" style={{ position: 'fixed', top: '30%', left: '50%', transform: 'translate(-50%, -50%)' }}>
          <div className="confirmation-box" style={{ backgroundColor: 'white', padding: '30px', borderRadius: '8px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.2)', width: '400px' }}>
            <div className="confirmation-message" style={{ color: 'grey' }}>Delete Record? Are you sure you want to delete the selected rows?</div>
            <div className="confirmation-buttons">
              <button className="confirm-button" onClick={handleDeleteConfirmation} style={{ color: 'grey' }}>
                Delete
              </button>
              <button className="cancel-button" onClick={handleDeleteCancel} style={{ color: 'grey' }}>
                Cancel
              </button>
            </div>
          </div>
        </div>


      )}
      <EditDialog
        open={isEditDialogOpen}
        selectedRows={selectedRows}
        rowData={
          selectedRows.length === 1 ? rowData.find((row) => row.id === selectedRows[0]) : null
        }
        onClose={handleCloseEditDialog}
        onSave={handleSaveEditDialog}
      />
      <AdvancedSearchDialog
        open={isAdvancedSearchOpen}
        onClose={handleCloseAdvancedSearch}
        onSearch={handleSearch}
      />
    </div>
  );
}

export default DataGridComponent;