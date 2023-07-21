import React, { useState } from "react";
import "./login.css";
import Page from "../../page";
import { useNavigate } from "react-router-dom";
import { UserInfoResponse } from "../../../api-interfaces/responses/user-info";
import { config } from "../../../config";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../../../store";
import { authSlice } from "../../../authSlice";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [backendError, setBackendError] = useState(
    undefined as undefined | string
  );
  const dispatch = useDispatch<AppDispatch>();

  const navigate = useNavigate();

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    if (name === "username") {
      setUsername(value);
    } else if (name === "password") {
      setPassword(value);
    }
  };

  const handleLogin = async (event: any) => {
    event.preventDefault();
    setBackendError(undefined);
    let response;
    try {
      response = await fetch(`${config.apiBaseAddress}auth/signin`, {
        method: "POST",
        body: JSON.stringify({ username: username, password: password }),
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
      });
    } catch (ex) {
      setBackendError("Error when logging in! Could not connect to backend.");
      return;
    }
    const userInfoResponse = await response.json();
    if (userInfoResponse.error) {
      setBackendError("Error when logging in! Incorrect username / password.");
    } else {
      dispatch(authSlice.actions.setLogin(userInfoResponse));
      if (userInfoResponse.roles.includes("MANAGER")) {
        navigate("/manager-home");
      } else if (userInfoResponse.roles.includes("EMPLOYEE")) {
        navigate("/employee-home");
      }
    }
  };

  return (
    <div>
      <Page>
        <form
          className="login-container"
          onSubmit={(e) => {
            handleLogin(e);
            return false;
          }}
        >
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={handleInputChange}
            name="username"
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={handleInputChange}
            name="password"
          />
          <button type="submit" onClick={handleLogin}>
            Login
          </button>
        </form>
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
