import Page from "../../page";
import { useNavigate } from "react-router-dom";

import React, { useState } from "react";
import { config } from "../../../config";

export default function NewUser() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [role, setRole] = useState("");

  const [backendError, setBackendError] = useState(
    undefined as undefined | string
  );

  const handleInputChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = event.target;
    switch (name) {
      case "username":
        setUsername(value);
        break;
      case "password":
        setPassword(value);
        break;
      case "name":
        setName(value);
        break;
      case "role":
        setRole(value);
        break;
    }
  };

  const handleNewUser = async (event: any) => {
    event.preventDefault();
    setBackendError(undefined);
    let response;
    try {
      response = await fetch(`${config.apiBaseAddress}auth/signup`, {
        method: "POST",
        body: JSON.stringify({
          username: username,
          password: password,
          role: role,
          name: name,
        }),
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
      });
    } catch (ex) {
      setBackendError(
        "Error when creating new user! Could not connect to backend."
      );
      return;
    }
  };
  return (
    <div>
      <Page>
        <div className="container ">
          <h1>Employee Registration</h1>
          <div className="d-flex flex-column align-items-center">
            <form onSubmit={handleNewUser} className=" d-flex flex-column w-50">
              <label htmlFor="username" className="input-label">
                Username:
              </label>
              <input
                type="text"
                id="username"
                className="m-1 form-control"
                name="username"
                onChange={handleInputChange}
                required
              />
              <label htmlFor="username" className="input-label">
                Name:
              </label>
              <input
                type="text"
                id="name"
                className="m-1 form-control"
                name="name"
                onChange={handleInputChange}
                required
              />
              <label htmlFor="password" className="input-label">
                Password:
              </label>
              <input
                type="password"
                id="password"
                className="m-1 form-control"
                name="password"
                onChange={handleInputChange}
                required
              />
              <label htmlFor="role" className="input-label">
                Role:
              </label>
              <select
                id="role"
                className="m-1 form-control"
                name="role"
                onChange={handleInputChange}
              >
                <option value="EMPLOYEE">Employee</option>
                <option value="Manager">Manager</option>
              </select>
              <div className="d-flex justify-content-end mt-3">
                <button className="btn btn-primary" type="submit">
                  Submit
                </button>
              </div>
            </form>
          </div>
        </div>
        {backendError && (
          <div className="d-flex flex-column align-items-center">
            <div className="alert alert-danger w-25" role="alert">
              {backendError}
            </div>
          </div>
        )}
      </Page>
    </div>
  );
}
