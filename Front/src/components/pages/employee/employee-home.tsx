import { NavLink } from "react-router-dom";
import Page from "../../page";
import React from "react";

export default function EmployeeHome() {
  return (
    <Page>
      <div className="container">
        <h1>Welcome, user</h1>
        <NavLink className="btn" to="/availabilities">
          View Availability Form
        </NavLink>
      </div>
    </Page>
  );
}
