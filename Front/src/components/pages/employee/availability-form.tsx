import { useSelector } from "react-redux";
import { config } from "../../../config";
import Page from "../../page";
import React, { useState } from "react";
import { RootState } from "../../../store";

interface DailyAvailability {
  day: "MON" | "TUE" | "WED" | "THU" | "FRI" | "SAT" | "SUN";
  from: number;
  to: number;
  username?: string;
}

interface Day {
  name: string;
  backendParams: DailyAvailability;
}

export default function AvailabilityForm() {
  const username = useSelector(
    (state: RootState) => state.auth.userInfo?.username
  );
  const [days, setDays] = useState([
    { name: "Monday", backendParams: { day: "MON", from: 0, to: 0 } },
    { name: "Tuesday", backendParams: { day: "TUE", from: 0, to: 0 } },
    { name: "Wednesday", backendParams: { day: "WED", from: 0, to: 0 } },
    { name: "Thursday", backendParams: { day: "THU", from: 0, to: 0 } },
    { name: "Friday", backendParams: { day: "FRI", from: 0, to: 0 } },
    { name: "Saturday", backendParams: { day: "SAT", from: 0, to: 0 } },
    { name: "Sunday", backendParams: { day: "SUN", from: 0, to: 0 } },
  ] as Day[]);
  return (
    <Page>
      <form
        className="container"
        onSubmit={async () => {
          for (const day of days) {
            let response;
            try {
              response = await fetch(
                `${config.apiBaseAddress}availabilities/add`,
                {
                  credentials: "include",
                  method: "POST",
                  body: JSON.stringify({
                    ...day.backendParams,
                    username: username,
                  }),
                  headers: {
                    "Content-Type": "application/json",
                  },
                }
              );
            } catch (ex) {
              console.log("Could not communicate with db");
              return;
            }
          }
        }}
      >
        <h1>Availability Form</h1>
        <div>Modifying availabilities for {username}</div>
        <div className="m-3 row align-items-center">
          {days.map((x) => (
            <div key={x.name} className="col">
              <h4>{x.name}</h4>
              <div className="m-1">
                <label htmlFor="from">From</label>
                <select
                  id="from"
                  className="form-control"
                  onChange={(e) =>
                    setDays(
                      days.map((day) =>
                        day.name === x.name
                          ? {
                              name: day.name,
                              backendParams: {
                                day: day.backendParams.day,
                                from: Number.parseInt(e.target.value),
                                to: day.backendParams.to,
                              },
                            }
                          : day
                      )
                    )
                  }
                >
                  {[...Array(24)].map((_, i) => (
                    <option key={i} value={i}>
                      {i.toString().padStart(2, "0")}:00
                    </option>
                  ))}
                </select>
              </div>
              <div className="m-1">
                <label htmlFor="to">To</label>
                <select
                  id="to"
                  className="form-control"
                  onChange={(e) =>
                    setDays(
                      days.map((day) =>
                        day.name === x.name
                          ? {
                              name: day.name,
                              backendParams: {
                                day: day.backendParams.day,
                                from: day.backendParams.from,
                                to: Number.parseInt(e.target.value),
                              },
                            }
                          : day
                      )
                    )
                  }
                >
                  {[...Array(24)].map((_, i) => (
                    <option key={i} value={i}>
                      {i.toString().padStart(2, "0")}:00
                    </option>
                  ))}
                </select>
              </div>
            </div>
          ))}
        </div>
        <div className="d-flex justify-content-end">
          <button className="btn btn-primary" type="submit">
            Submit
          </button>
        </div>
      </form>
    </Page>
  );
}
