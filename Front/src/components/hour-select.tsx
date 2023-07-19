import React from "react";

export default function HourSelect(props: {
  handleChange: (x: number) => unknown;
}) {
  return (
    <select
      className="form-control"
      onChange={(e) => props.handleChange(Number.parseInt(e.target.value))}
    >
      {[...Array(24 / 4)]
        .map((_, i) => i * 4)
        .map((i) => (
          <option key={i} value={i}>
            {i.toString().padStart(2, "0")}:00
          </option>
        ))}
    </select>
  );
}
