import React, { useState } from "react";
import Page from "../../page";
import { config } from "../../../config";
import { useLocation } from "react-router-dom";
import ScheduleTable from "../../schedule-table";
import { Schedule } from "../../../api-interfaces/schedules";

function ScheduleOption(props: {
  schedule: Schedule;
  index: number;
  week: string;
}): JSX.Element {
  return (
    <div className="card m-3">
      <div className="card-body">
        <h3 className="card-title">Schedule {props.index + 1}</h3>
        <ScheduleTable schedule={props.schedule} />
        <div className="d-flex justify-content-end">
          <button
            className="btn btn-primary"
            onClick={async () => {
              let response;
              try {
                response = await fetch(
                  `${config.apiBaseAddress}generate/save`,
                  {
                    credentials: "include",
                    method: "POST",
                    body: JSON.stringify({
                      ...props.schedule,
                      firstDayOfWeek: props.week,
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
            }}
          >
            Select
          </button>
        </div>
      </div>
    </div>
  );
}

export default function SelectSchedule() {
  const location = useLocation();
  const schedules: Schedule[] = location.state?.schedules;
  const week: string = location.state?.week;
  return (
    <Page>
      <div className="container">
        <h1>Select a schedule to save:</h1>
        {schedules && (
          <div>
            {schedules.map((x, i) => (
              <ScheduleOption
                key={i}
                schedule={x}
                index={i}
                week={week}
              ></ScheduleOption>
            ))}
          </div>
        )}
      </div>
    </Page>
  );
}
