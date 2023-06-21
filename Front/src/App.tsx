import React from "react";
import logo from "./logo.svg";
import "./App.css";
import Error from "./components/pages/common/error";
import Login from "./components/pages/common/login";
import { RouterProvider, createHashRouter } from "react-router-dom";
import AvailabilityForm from "./components/pages/employee/availabilityForm";
import GenerateSchedule from "./components/pages/manager/generateSchedule";

const router = createHashRouter([
  { path: "*", element: <Error /> },
  { path: "/login", element: <Login /> },
  { path: "/availabilities", element: <AvailabilityForm /> },
  { path: "/generate-schedules", element: <GenerateSchedule /> },
]);

function App() {
  return (
    <div className="App">
      <div id="page-container">
        <RouterProvider router={router} />
      </div>
    </div>
  );
}

export default App;
