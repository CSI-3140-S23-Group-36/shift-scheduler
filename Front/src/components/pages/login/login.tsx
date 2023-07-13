import React, { useState } from "react";
import "./login.css";
import Page from "../../page";
import { useNavigate } from "react-router-dom";
import { UserInfoResponse } from "../../../api-interfaces/responses/user-info";
import { config } from "../../../config";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

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
    const response = await fetch(`${config.apiBaseAddress}auth/signin`, {
      method: "POST",
      body: JSON.stringify({ username: username, password: password }),
      headers: {
        "Content-Type": "application/json",
      },
    });
    const userInfoResponse = (await response.json()) as UserInfoResponse;
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
