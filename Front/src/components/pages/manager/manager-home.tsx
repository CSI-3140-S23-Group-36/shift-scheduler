import Page from "../../page";
import React from 'react';

export default function ManagerHome() {
  return <Page>
    <div className="container">
      <h1>Welcome, user</h1>
      <div className="button-container">
        <button className="btn">Shift Schedule Generation</button>
        <button className="btn">Shift Schedule Viewing</button>
        <button className="btn">New Employee Form</button>
      </div>
    </div>
  </Page>;
}
