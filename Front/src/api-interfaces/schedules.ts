export const days = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"] as const;
export type Day = (typeof days)[number];

export interface Shift {
  id: unknown;
  employee: number;
  date: unknown;
  day: Day;
  startHour: number;
  endHour: number;
  type: "SHIFT";
}

export interface DailySchedule {
  day: Day;
  shifts: Shift[];
}

export interface Schedule {
  firstDayOfWeek: string;
  dailySchedules: DailySchedule[];
}
