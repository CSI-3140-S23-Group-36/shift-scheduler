import { useNavigate } from "react-router-dom";
import { config } from "../../../config";
import Page from "../../page";

const days = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"] as const;
type Day = (typeof days)[number];

import React, { useState } from "react";
export default function GenerateSchedule() {
  const [week, setWeek] = useState("");
  const [perHour, setPerHour] = useState(0);
  const [start, setStart] = useState(0);
  const [end, setEnd] = useState(0);

  const navigate = useNavigate();

  return (
    <Page>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <h2>Week</h2>
        <input
          type="date"
          onChange={(e) => setWeek(e.target.value)}
          style={{ marginBottom: "1rem" }}
        />

        <h2>Employee Details</h2>
        <label>Number of employees per hour:</label>
        <input
          type="number"
          onChange={(e) => setPerHour(Number.parseInt(e.target.value))}
          style={{ marginBottom: "1rem" }}
        />

        <h2>Time Range</h2>
        <label>Start Time:</label>
        <select
          id="from"
          className="form-control w-25"
          onChange={(e) => setStart(Number.parseInt(e.target.value))}
        >
          {[...Array(24)].map((_, i) => (
            <option key={i} value={i}>
              {i.toString().padStart(2, "0")}:00
            </option>
          ))}
        </select>

        <label>End Time:</label>
        <select
          id="to"
          className="form-control w-25"
          onChange={(e) => setEnd(Number.parseInt(e.target.value))}
        >
          {[...Array(24)].map((_, i) => (
            <option key={i} value={i}>
              {i.toString().padStart(2, "0")}:00
            </option>
          ))}
        </select>

        <button
          onClick={async () => {
            const send = days.map((x) => {
              return {
                day: x,
                numberOfEmployeesPerHour: perHour,
                startHour: start,
                endHour: end,
              };
            });
            let response;
            try {
              response = await fetch(`${config.apiBaseAddress}generate/week`, {
                credentials: "include",
                method: "POST",
                body: JSON.stringify(send),
                headers: {
                  "Content-Type": "application/json",
                },
              });
              const schedules = await response.json();
              if (!schedules.error) {
                navigate("/select-schedule", {
                  state: { schedules: schedules },
                });
              } else {
                console.log("Error generating!");
              }
            } catch (ex) {
              console.log("Could not communicate with db");
              return;
            }
          }}
        >
          Generate Schedules
        </button>
      </div>
    </Page>
  );
}
