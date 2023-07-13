import React, { useState } from 'react';
import Login from './components/pages/common/login';
import Error from "./components/pages/common/error";
import './components/pages/common/login.css'
import AvailabilityForm from "./components/pages/employee/availabilityForm";
import GenerateSchedule from "./components/pages/manager/generateSchedule";

// const router = createHashRouter([
//   { path: "*", element: <Error /> },
//   { path: "/login", element: <Login /> },
//   { path: "/availabilities", element: <AvailabilityForm /> },
//   { path: "/generate-schedules", element: <GenerateSchedule /> },
// ]);

/* Don't know how to use hash router */
const App: React.FC = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    if (name === 'username') {
      setUsername(value);
    } else if (name === 'password') {
      setPassword(value);
    }
  };

  const handleLogin = () => {
  };

  return (
    <div>
      <Login
        username={username}
        password={password}
        onInputChange={handleInputChange}
        onLogin={handleLogin}
      />
    </div>
  );
};

export default App;
