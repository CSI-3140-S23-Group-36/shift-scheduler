import { NavLink } from "react-router-dom";
import Page from "../../page";
import React from "react";

export default function ManagerHome() {
  return (
    <Page>
      <div className="container">
        <h1>Welcome, user</h1>
        <div className="button-container">
          <NavLink className="btn" to="/generate-schedules">
            Shift Schedule Generation
          </NavLink>
          <NavLink className="btn" to="/view-schedules">
            Shift Schedule Viewing
          </NavLink>
          <NavLink className="btn" to="/new-employee">
            New Employee Form
          </NavLink>
        </div>
      </div>
    </Page>
  );
}
