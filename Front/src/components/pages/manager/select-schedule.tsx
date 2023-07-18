import React, { useState } from "react";
import Page from "../../page";
import { config } from "../../../config";
import { useLocation } from "react-router-dom";

type Day = "MON" | "TUE" | "WED" | "THU" | "FRI" | "SAT" | "SUN";

interface Shift {
  id: unknown;
  employee: number;
  date: unknown;
  day: Day;
  startHour: number;
  endHour: number;
  type: "SHIFT";
}

interface DailySchedule {
  day: Day;
  shifts: Shift[];
}

interface Schedule {
  firstDayOfWeek: string;
  dailySchedules: DailySchedule[];
}

function numberToHour(n: number) {
  return `${n.toString().padStart(2, "0")}:00`;
}

function ShiftCard(props: { shift: Shift }): JSX.Element {
  return (
    <div className="border p-2">
      <span>
        Employee {props.shift.employee}
        <br />
        {numberToHour(props.shift.startHour)} -{" "}
        {numberToHour(props.shift.endHour)}
      </span>
    </div>
  );
}

function ScheduleOption(props: { schedule: Schedule }): JSX.Element {
  return (
    <div className="card m-3">
      <div className="card-body">
        <table className="table">
          <thead>
            <tr>
              {props.schedule.dailySchedules.map((scheduleDay) => (
                <th key={scheduleDay.day} scope="col">
                  {scheduleDay.day}
                </th>
              ))}
            </tr>
          </thead>
          <tbody>
            <tr>
              {props.schedule.dailySchedules.map((scheduleDay) => (
                <td className="col" key={scheduleDay.day}>
                  <div className="d-flex flex-column">
                    {scheduleDay.shifts.map((shift, shiftIndex) => (
                      <ShiftCard key={shiftIndex} shift={shift} />
                    ))}
                  </div>
                </td>
              ))}
            </tr>
          </tbody>
        </table>
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
                    body: JSON.stringify(props.schedule),
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
  return (
    <Page>
      <div className="container">
        <h1>Select a schedule:</h1>
        {schedules && (
          <div>
            {schedules.map((x, i) => (
              <ScheduleOption key={i} schedule={x}></ScheduleOption>
            ))}
          </div>
        )}
      </div>
    </Page>
  );
}
