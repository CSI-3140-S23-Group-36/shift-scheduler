import Page from "../../page";

import React from "react";
export default function GenerateSchedule() {
  return <Page>
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
      <h2>Week</h2>
      <input
        type="date"
        // value={startDate}
        style={{ marginBottom: "1rem" }}
      />

      <h2>Employee Details</h2>
      <label>Number of employees per hour:</label>
      <input
        type="number"
        // value={Number}
        style={{ marginBottom: "1rem" }}
      />

      <h2>Time Range</h2>
      <label>Start Time:</label>
      <input
        type="time"
        // value={startTime}
        style={{ marginBottom: "1rem" }}
      />

      <label>End Time:</label>
      <input
        type="time"
        // value={endTime}
        style={{ marginBottom: "1rem" }}
      />

      <button>Generate Schedules</button>
    </div>
  </Page>;
}
