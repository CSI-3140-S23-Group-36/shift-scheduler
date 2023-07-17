import React, { useState } from "react";
import Error from "./components/pages/common/error";
import AvailabilityForm from "./components/pages/employee/availability-form";
import GenerateSchedule from "./components/pages/manager/generateSchedule";
import { RouterProvider, createHashRouter } from "react-router-dom";
import Login from "./components/pages/login/login";
import ManagerHome from "./components/pages/manager/manager-home";
import NewUser from "./components/pages/manager/newUser";
import EmployeeHome from "./components/pages/employee/employee-home";
import SelectSchedule from "./components/pages/manager/select-schedule";

const router = createHashRouter([
  { path: "*", element: <Error /> },
  { path: "/", element: <Login /> },
  { path: "/login", element: <Login /> },
  { path: "/manager-home", element: <ManagerHome /> },
  { path: "/new-user", element: <NewUser /> },
  { path: "/employee-home", element: <EmployeeHome /> },
  { path: "/availabilities", element: <AvailabilityForm /> },
  { path: "/generate-schedules", element: <GenerateSchedule /> },
  { path: "/select-schedule", element: <SelectSchedule /> },
]);

export default function App() {
  return (
    <div className="App">
      <div id="page-container">
        <RouterProvider router={router} />
      </div>
    </div>
  );
}
