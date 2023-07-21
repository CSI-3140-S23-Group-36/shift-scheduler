import { NavLink } from "react-router-dom";
import Page from "../../page";
import React from "react";
import { useSelector } from "react-redux";
import { RootState } from "../../../store";

export default function EmployeeHome() {
  const name = useSelector((state: RootState) => state.auth.userInfo?.name);
  return (
    <Page>
      <div className="container">
        <h1>Welcome, {name}</h1>
        <NavLink className="btn btn-outline-primary m-1" to="/availabilities">
          View Availability Form
        </NavLink>
      </div>
    </Page>
  );
}
