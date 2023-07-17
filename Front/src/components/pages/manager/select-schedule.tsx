import React from "react";
import Page from "../../page";

const testSchedules: Schedule[] = [
  {
    firstDayOfWeek: null,
    dailySchedules: [
      {
        day: "MON",
        shifts: [
          {
            id: null,
            employee: 5,
            date: null,
            day: "MON",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "MON",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "TUE",
        shifts: [
          {
            id: null,
            employee: 3,
            date: null,
            day: "TUE",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "TUE",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "WED",
        shifts: [
          {
            id: null,
            employee: 8,
            date: null,
            day: "WED",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "WED",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "THU",
        shifts: [
          {
            id: null,
            employee: 3,
            date: null,
            day: "THU",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "THU",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "FRI",
        shifts: [
          {
            id: null,
            employee: 9,
            date: null,
            day: "FRI",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 1,
            date: null,
            day: "FRI",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "SAT",
        shifts: [
          {
            id: null,
            employee: 9,
            date: null,
            day: "SAT",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 3,
            date: null,
            day: "SAT",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "SUN",
        shifts: [
          {
            id: null,
            employee: 9,
            date: null,
            day: "SUN",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 4,
            date: null,
            day: "SUN",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
    ],
  },
  {
    firstDayOfWeek: null,
    dailySchedules: [
      {
        day: "MON",
        shifts: [
          {
            id: null,
            employee: 5,
            date: null,
            day: "MON",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "MON",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "TUE",
        shifts: [
          {
            id: null,
            employee: 3,
            date: null,
            day: "TUE",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "TUE",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "WED",
        shifts: [
          {
            id: null,
            employee: 8,
            date: null,
            day: "WED",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "WED",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "THU",
        shifts: [
          {
            id: null,
            employee: 3,
            date: null,
            day: "THU",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "THU",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "FRI",
        shifts: [
          {
            id: null,
            employee: 9,
            date: null,
            day: "FRI",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 1,
            date: null,
            day: "FRI",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "SAT",
        shifts: [
          {
            id: null,
            employee: 9,
            date: null,
            day: "SAT",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 3,
            date: null,
            day: "SAT",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "SUN",
        shifts: [
          {
            id: null,
            employee: 9,
            date: null,
            day: "SUN",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 4,
            date: null,
            day: "SUN",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
    ],
  },
  {
    firstDayOfWeek: null,
    dailySchedules: [
      {
        day: "MON",
        shifts: [
          {
            id: null,
            employee: 5,
            date: null,
            day: "MON",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "MON",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "TUE",
        shifts: [
          {
            id: null,
            employee: 3,
            date: null,
            day: "TUE",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "TUE",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "WED",
        shifts: [
          {
            id: null,
            employee: 8,
            date: null,
            day: "WED",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "WED",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "THU",
        shifts: [
          {
            id: null,
            employee: 3,
            date: null,
            day: "THU",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 2,
            date: null,
            day: "THU",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "FRI",
        shifts: [
          {
            id: null,
            employee: 9,
            date: null,
            day: "FRI",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 1,
            date: null,
            day: "FRI",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "SAT",
        shifts: [
          {
            id: null,
            employee: 9,
            date: null,
            day: "SAT",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 3,
            date: null,
            day: "SAT",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
      {
        day: "SUN",
        shifts: [
          {
            id: null,
            employee: 9,
            date: null,
            day: "SUN",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
          {
            id: null,
            employee: 5,
            date: null,
            day: "SUN",
            startHour: 12,
            endHour: 20,
            type: "SHIFT",
          },
        ],
      },
    ],
  },
];

type Day = "MON" | "TUE" | "WED" | "THU" | "FRI" | "SAT" | "SUN";

interface Shift {
  id: any;
  employee: number;
  date: any;
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
  firstDayOfWeek: any;
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
          <button className="btn btn-primary" type="submit">
            Select
          </button>
        </div>
      </div>
    </div>
  );
}

export default function SelectSchedule() {
  return (
    <Page>
      <div className="container">
        <h1>Select a schedule:</h1>
        <div>
          {testSchedules.map((x, i) => (
            <ScheduleOption key={i} schedule={x}></ScheduleOption>
          ))}
        </div>
      </div>
    </Page>
  );
}
