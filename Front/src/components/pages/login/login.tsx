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

  const handleLogin = async () => {
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
      console.log("Could not communicate with db");
      return;
    }
    const userInfoResponse = (await response.json()) as UserInfoResponse;
    dispatch(authSlice.actions.setLogin(userInfoResponse));
    if (userInfoResponse.roles.includes("MANAGER")) {
      navigate("/manager-home");
    } else if (userInfoResponse.roles.includes("EMPLOYEE")) {
      navigate("/employee-home");
    }
  };

  return (
    <div>
      <Page>
        <div className="login-container">
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
          <button onClick={handleLogin}>Login</button>
        </div>
      </Page>
    </div>
  );
}
