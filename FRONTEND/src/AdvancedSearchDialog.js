import React, { useState } from 'react';

function AdvancedSearchDialog({ open, onClose, onSearch }) {
  const [customerOrderId, setCustomerOrderId] = useState('');
  const [customerNumber, setCustomerNumber] = useState('');
  const [salesOrg, setSalesOrg] = useState('');

  const handleCustomerOrderIdChange = (event) => {
    setCustomerOrderId(event.target.value);
  };

  const handleCustomerNumberChange = (event) => {
    setCustomerNumber(event.target.value);
  };

  const handleSalesOrgChange = (event) => {
    setSalesOrg(event.target.value);
  };

  const handleSearch = () => {
    const searchData = {
      customerOrderId,
      customerNumber,
      salesOrg
    };
    onSearch(searchData);
  };

  const handleClose = () => {
    setCustomerOrderId('');
    setCustomerNumber('');
    setSalesOrg('');
    onClose();
  };

  return (
    <div style={{ display: open ? 'block' : 'none', position: 'fixed', top: '50%', left: '50%', transform: 'translate(-50%, -50%)', background: '#fff', padding: '30px', borderRadius: '8px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.2)', zIndex: 9999 }}>
      <h3 style={{ marginLeft: '0' ,color:'grey'}}>Advanced Search</h3>
      <div style={{ marginBottom: '15px' }}>
        <input type="text" value={customerOrderId} onChange={handleCustomerOrderIdChange} placeholder="Customer Order ID" style={{ width: '100%', height: '40px', borderRadius: '4px', paddingLeft: '10px' }} />
      </div>
      <div style={{ marginBottom: '15px' }}>
        <input type="text" value={customerNumber} onChange={handleCustomerNumberChange} placeholder="Customer Number" style={{ width: '100%', height: '40px', borderRadius: '4px', paddingLeft: '10px' }} />
      </div>
      <div style={{ marginBottom: '15px' }}>
        <input type="text" value={salesOrg} onChange={handleSalesOrgChange} placeholder="Sales Org" style={{ width: '100%', height: '40px', borderRadius: '4px', paddingLeft: '10px' }} />
      </div>
      <div style={{ display: 'flex', justifyContent: 'space-between' }}>
        <button onClick={handleSearch} style={{ outline: 'none', border: '1px solid grey', borderRadius: '4px', padding: '8px 16px', cursor: 'pointer' }}>Search</button>
        <button onClick={handleClose} style={{ outline: 'none', border: '1px solid grey', borderRadius: '4px', padding: '8px 16px', cursor: 'pointer' }}>Close</button>
      </div>
    </div>
  );
}

export default AdvancedSearchDialog;
