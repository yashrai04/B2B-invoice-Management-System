import React, { useState } from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogContent from '@material-ui/core/DialogContent';
import DialogActions from '@material-ui/core/DialogActions';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import axios from 'axios';

const EditDialog = ({ open, rowData, onClose, onSave }) => {
  const [editedData, setEditedData] = useState({ ...rowData });

  const handleFieldChange = (e) => {
    const { name, value } = e.target;
    setEditedData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSave = () => {
    const config = {
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:3000',
      },
    };
  
    axios.put('h2h_milestone-3/updateUser', editedData)

      .then((response) => {
        console.log(response.data);
        onSave(editedData);
      })
      .catch((error) => {
        console.error(error);
      });
  };
  

  const handleClose = () => {
    onClose(null);
  };

  return (
    <Dialog open={open} onClose={handleClose}>
      <DialogTitle>Edit Row</DialogTitle>
      <DialogContent>
        {rowData && (
          <>
            <TextField
              name="orderCurrency"
              label="Order Currency"
              value={editedData.orderCurrency || ''}
              onChange={handleFieldChange}
              fullWidth
              margin="normal"
            />
            <TextField
              name="companyCode"
              label="Company Code"
              value={editedData.companyCode || ''}
              onChange={handleFieldChange}
              fullWidth
              margin="normal"
            />
            <TextField
              name="distributionChannel"
              label="Distribution Channel"
              value={editedData.distributionChannel || ''}
              onChange={handleFieldChange}
              fullWidth
              margin="normal"
            />
          </>
        )}
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Cancel
        </Button>
        <Button onClick={handleSave} color="primary">
          Save
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default EditDialog;
