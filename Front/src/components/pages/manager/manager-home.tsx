import { NavLink } from "react-router-dom";
import Page from "../../page";
import React from "react";
import { RootState } from "../../../store";
import { useSelector } from "react-redux";

export default function ManagerHome() {
  const name = useSelector((state: RootState) => state.auth.userInfo?.name);
  return (
    <Page>
      <div className="container">
        <h1>Welcome, {name}</h1>
        <div className="d-flex flex-column align-items-center">
          <div className="d-flex flex-column w-50">
            <NavLink
              className="btn btn-outline-primary m-1"
              to="/generate-schedules"
            >
              Shift Schedule Generation
            </NavLink>
            <NavLink
              className="btn btn-outline-primary m-1"
              to="/view-schedules"
            >
              Shift Schedule Viewing
            </NavLink>
            <NavLink className="btn btn-outline-primary m-1" to="/new-employee">
              New Employee Form
            </NavLink>
          </div>
        </div>
      </div>
    </Page>
  );
}
