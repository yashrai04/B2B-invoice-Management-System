// MyTabs.js
import React, { useState } from 'react';
import { Tabs, Tab } from '@material-ui/core';
import DataGridComponent from './DataGridComponents';
import AddUserForm from './AddUserForm';
import AnalyticsView from './Analytics';
import AdvancedSearch from './AdvancedSearchDialog';

function MyTabs() {
  const [value, setValue] = useState(0);
  const [searchQuery, setSearchQuery] = useState('');

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleSearchChange = (e) => {
    setSearchQuery(e.target.value);
  };

  return (
    <div style={{ textAlign: 'left',backgroundColor:"grey",color:"white" }}>
      <Tabs value={value} onChange={handleChange} centered={false} style={{ paddingRight: '0px' }}>
        <Tab label="HOME" />
        <Tab label="ADD DATA" />
        <Tab label="ANALYTICS" />
        <AdvancedSearch />
      </Tabs>
      <div style={{ display: 'flex', justifyContent: 'flex-end', marginBottom: '10px', marginLeft: '700px' }}>
        <input
          type="text"
          placeholder="Search"
          value={searchQuery}
          onChange={handleSearchChange}
          style={{
            width: '200px',
            height: '30px',
            position: 'absolute',
            top: '31%',
            right: '210px',
          }}
        />
      </div>

      {value === 0 && <DataGridComponent searchQuery={searchQuery} />} {/* Pass searchQuery and setSearchQuery as props */}
      {value === 1 && <AddUserForm />}
      {value === 2 && <AnalyticsView />}
    </div>
  );
}

export default MyTabs;
