import React, { useEffect, useState } from "react";
import Page from "../../page";
import { config } from "../../../config";
import { useLocation } from "react-router-dom";
import ScheduleTable from "../../schedule-table";
import { Schedule } from "../../../api-interfaces/schedules";

function ScheduleCard(props: { schedule: Schedule }): JSX.Element {
  return (
    <div className="card m-3">
      <div className="card-body">
        <h3 className="card-title">
          Schedule for the Week of {props.schedule.firstDayOfWeek}
        </h3>
        <ScheduleTable schedule={props.schedule} />
      </div>
    </div>
  );
}

export default function ViewSchedules() {
  const [week, setWeek] = useState("");
  const [schedule, setSchedule] = useState(undefined as Schedule | undefined);

  const [backendError, setBackendError] = useState(
    undefined as undefined | string
  );

  useEffect(() => {
    setSchedule(undefined);
    setBackendError(undefined);
    if (week.split("-").length == 3) {
      const getSchedule = async () => {
        let response;
        try {
          response = await fetch(
            `${config.apiBaseAddress}schedule/week?` +
              new URLSearchParams({
                year: week.split("-")[0],
                month: week.split("-")[1],
                day: week.split("-")[2],
              }),
            {
              method: "GET",
              credentials: "include",
              headers: {
                "Content-Type": "application/json",
              },
            }
          );
        } catch (ex) {
          setBackendError(
            "Error when retrieving schedule! Could not connect to backend."
          );
          return;
        }
        const s = (await response.json()) as Schedule;
        if (s === null || !s.firstDayOfWeek) {
          setBackendError(
            "Error when retrieving schedule! A schedule may not exist for this date."
          );
          return;
        }
        setSchedule(s);
      };
      getSchedule();
    }
  }, [week]);

  return (
    <Page>
      <div className="container">
        <div className="d-flex flex-column w-25">
          <h2>Week</h2>
          <label>Select a Monday.</label>
          <input
            type="date"
            onChange={(e) => setWeek(e.target.value)}
            style={{ marginBottom: "1rem" }}
          />
        </div>
        {schedule && <ScheduleCard schedule={schedule}></ScheduleCard>}
      </div>
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
