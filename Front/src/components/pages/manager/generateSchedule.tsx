import { useNavigate } from "react-router-dom";
import { config } from "../../../config";
import Page from "../../page";

import React, { useState } from "react";
import { days } from "../../../api-interfaces/schedules";
import HourSelect from "../../hour-select";
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
        <label>Select a Monday.</label>
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
        <div className="w-25">
          <HourSelect handleChange={(x) => setStart(x)} />
        </div>

        <label>End Time:</label>
        <div className="w-25">
          <HourSelect handleChange={(x) => setEnd(x)} />
        </div>

        <button
          className="btn btn-primary m-3"
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
                  state: { schedules: schedules, week: week },
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
