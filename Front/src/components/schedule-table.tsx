import React from "react";
import { Schedule, Shift } from "../api-interfaces/schedules";

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

export default function ScheduleTable(props: {
  schedule: Schedule;
}): JSX.Element {
  return (
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
  );
}
