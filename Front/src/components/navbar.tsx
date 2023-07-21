import React from "react";
import { NavLink } from "react-router-dom";
export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid">
        <a className="navbar-brand" href="#">
          Shift Scheduler
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0"></ul>
          <NavLink
            to="/new-user"
            className="btn btn-outline-success m-1"
            type="submit"
          >
            Register
          </NavLink>
          <NavLink
            to="/login"
            className="btn btn-outline-success m-1"
            type="submit"
          >
            Log in
          </NavLink>
        </div>
      </div>
    </nav>
  );
}
