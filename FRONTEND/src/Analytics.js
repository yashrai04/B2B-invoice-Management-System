import React, { useState, useEffect } from "react";
import Highcharts from "highcharts";
import HighchartsReact from "highcharts-react-official";
import axios from "axios";

const AnalyticsView = () => {
  const [distributionChannel, setDistributionChannel] = useState("");
  const [customerNumber, setCustomerNumber] = useState("");
  const [showCharts, setShowCharts] = useState(false);
  const [barChartData, setBarChartData] = useState([]);
  const [pieChartData, setPieChartData] = useState([]);

  const handleViewClick = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/h2h_milestone-3/fetchUsers?distributionChannel=${distributionChannel}&customerNumber=${customerNumber}`
      );
      const jsonData = response.data;

      // Process the fetched data to extract chart data
      const fetchedBarChartData = jsonData.map((data) => ({
        name: data.customerOrderID,
        value: data.releasedCreditValue,
      }));
      const fetchedPieChartData = jsonData.map((data) => ({
        name: data.customerOrderID,
        value: data.orderAmount,
      }));

      setBarChartData(fetchedBarChartData);
      setPieChartData(fetchedPieChartData);
      setShowCharts(true);
    } catch (error) {
      console.error("Error fetching chart data:", error);
    }
  };

  const renderCharts = () => {
    // Render the charts
    return (
      <div style={{ display: "flex", justifyContent: "center" }}>
        <div style={{ marginRight: "10px" }}>
          <HighchartsReact highcharts={Highcharts} options={getBarChartOptions()} />
        </div>
        <div>
          <HighchartsReact highcharts={Highcharts} options={getPieChartOptions()} />
        </div>
      </div>
    );
  };

  const getBarChartOptions = () => {
    return {
      chart: {
        type: "bar",
        width: 600, // Adjust the width as needed
      },
      title: {
        text: "Bar Chart",
      },
      xAxis: {
        categories: barChartData.map((data) => data.name),
      },
      yAxis: {
        title: {
          text: "Value",
        },
      },
      series: [
        {
          name: "Value",
          data: barChartData.map((data) => data.value),
        },
      ],
    };
  };

  const getPieChartOptions = () => {
    return {
      chart: {
        type: "pie",
        width: 300, // Adjust the width as needed
      },
      title: {
        text: "Pie Chart",
      },
      series: [
        {
          name: "Value",
          data: pieChartData.map((data) => ({
            name: data.name,
            y: data.value,
          })),
        },
      ],
    };
  };

  return (
    <div>
      <div>
        <div style={{ marginBottom: "10px" }}>
          <input
            type="text"
            placeholder="Distribution Channel"
            value={distributionChannel}
            onChange={(e) => setDistributionChannel(e.target.value)}
          />
        </div>
        <div style={{ marginBottom: "10px" }}>
          <input
            type="text"
            placeholder="Customer Number"
            value={customerNumber}
            onChange={(e) => setCustomerNumber(e.target.value)}
          />
        </div>
        <div style={{ marginBottom: "10px" }}>
          <button onClick={handleViewClick} style={{ color: "grey" }}>
            View
          </button>
        </div>
      </div>
      {showCharts && (
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            marginTop: "20px",
          }}
        >
          {renderCharts()}
        </div>
      )}
    </div>
  );
};

export default AnalyticsView;