import React, { useState } from 'react';
import axios from 'axios';

const InvoiceForm = () => {
  const [formData, setFormData] = useState({
    customerOrderID: '',
    salesOrg: '',
    distributionChannel: '',
    customerNumber: '',
    companyCode: '',
    orderCurrency: '',
    amountInUSD: '',
    orderCreationDate: '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axios
      .post('http://localhost:8080/h2h_milestone-3/addUser', formData)
      .then((response) => {
        console.log(response.data);
        // Handle success or show a success message
      })
      .catch((error) => {
        console.error(error);
        // Handle error or show an error message
      });
  };

  const handleClear = () => {
    setFormData({
      customerOrderID: '',
      salesOrg: '',
      distributionChannel: '',
      customerNumber: '',
      companyCode: '',
      orderCurrency: '',
      amountInUSD: '',
      orderCreationDate: '',
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
        <div style={{ display: 'flex', gap: '10px' }}>
          <input
            type="text"
            name="customerOrderID"
            value={formData.customerOrderID}
            onChange={handleChange}
            placeholder="Customer Order ID"
            style={{ padding: '10px', width: '200px' }}
          />
          <input
            type="text"
            name="salesOrg"
            value={formData.salesOrg}
            onChange={handleChange}
            placeholder="Sales Org"
            style={{ padding: '10px', width: '200px' }}
          />
          <input
            type="text"
            name="distributionChannel"
            value={formData.distributionChannel}
            onChange={handleChange}
            placeholder="Distribution Channel"
            style={{ padding: '10px', width: '662px' }}
          />
        </div>
        <div style={{ display: 'flex', gap: '10px' }}>
          <input
            type="text"
            name="customerNumber"
            value={formData.customerNumber}
            onChange={handleChange}
            placeholder="Customer Number"
            style={{ padding: '10px', width: '200px' }}
          />
          <input
            type="text"
            name="companyCode"
            value={formData.companyCode}
            onChange={handleChange}
            placeholder="Company Code"
            style={{ padding: '10px', width: '200px' }}
          />
          <input
            type="text"
            name="orderCurrency"
            value={formData.orderCurrency}
            onChange={handleChange}
            placeholder="Order Currency"
            style={{ padding: '10px', width: '200px' }}
          />
          <input
            type="text"
            name="amountInUSD"
            value={formData.amountInUSD}
            onChange={handleChange}
            placeholder="Amount in USD"
            style={{ padding: '10px', width: '200px' }}
          />
          <input
            type="text"
            name="orderCreationDate"
            value={formData.orderCreationDate}
            onChange={handleChange}
            placeholder="Order Creation Date"
            style={{ padding: '10px', width: '200px' }}
          />
        </div>
      </div>
      <div style={{ display: 'flex', gap: '10px', marginTop: '10px' }}>
        <button type="submit" style={{ width: '50%', backgroundColor: '#fc7500', color: 'white', padding: '7px', fontSize: '16px' }}>
          Add
        </button>
        <button type="button" onClick={handleClear} style={{ width: '50%', backgroundColor: 'red', color: 'white', padding: '7px', fontSize: '16px' }}>
          Clear Data
        </button>
      </div>
    </form>
  );
};

export default InvoiceForm;
