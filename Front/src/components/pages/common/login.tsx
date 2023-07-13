import React from 'react';

interface LoginProps {
  username: string;
  password: string;
  onInputChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onLogin: () => void;
}

const Login: React.FC<LoginProps> = ({
  username,
  password,
  onInputChange,
  onLogin,
}) => {
  return (
    <div>
      <div className="navbar">
        <h1>Shift Scheduler</h1>
        <button>Help</button>
      </div>

      <div className="login-container">
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={onInputChange}
          name="username"
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={onInputChange}
          name="password"
        />
        <button onClick={onLogin}>Login</button>
      </div>
    </div>
  );
};

export default Login;
