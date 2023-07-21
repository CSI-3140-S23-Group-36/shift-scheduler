import { useSelector } from "react-redux";
import { config } from "../../../config";
import Page from "../../page";
import React, { useState } from "react";
import { RootState } from "../../../store";
import HourSelect from "../../hour-select";

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
  const [backendError, setBackendError] = useState(
    undefined as undefined | string
  );
  const username = useSelector(
    (state: RootState) => state.auth.userInfo?.username
  );
  const name = useSelector((state: RootState) => state.auth.userInfo?.name);
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
          setBackendError(undefined);
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
              setBackendError(
                "Error when adding availabilities! Could not connect to backend."
              );
              return;
            }
            const jsonResponse = await response.json();
            if (jsonResponse.error) {
              setBackendError(
                "Error when adding availabilities! An error occurred."
              );
            }
          }
        }}
      >
        <h1>Availability Form</h1>
        <div>
          Modifying availabilities for {name} ({username})
        </div>
        <div className="m-3 row align-items-center">
          {days.map((x) => (
            <div key={x.name} className="col">
              <h4>{x.name}</h4>
              <div className="m-1">
                <label htmlFor="from">From</label>
                <HourSelect
                  handleChange={(y) =>
                    setDays(
                      days.map((day) =>
                        day.name === x.name
                          ? {
                              name: day.name,
                              backendParams: {
                                day: day.backendParams.day,
                                from: y,
                                to: day.backendParams.to,
                              },
                            }
                          : day
                      )
                    )
                  }
                />
              </div>
              <div className="m-1">
                <label htmlFor="to">To</label>
                <HourSelect
                  handleChange={(y) =>
                    setDays(
                      days.map((day) =>
                        day.name === x.name
                          ? {
                              name: day.name,
                              backendParams: {
                                day: day.backendParams.day,
                                from: day.backendParams.from,
                                to: y,
                              },
                            }
                          : day
                      )
                    )
                  }
                />
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
      {backendError && (
        <div className="d-flex flex-column align-items-center">
          <div className="alert alert-danger w-25" role="alert">
            {backendError}
          </div>
        </div>
      )}
    </Page>
  );
}
