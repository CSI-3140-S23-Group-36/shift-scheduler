import Navbar from "./navbar";

import React from "react";

export default function Page(props: { children: React.ReactNode }) {
  return (
    <div className="h-100 w-100 d-flex flex-column">
      <Navbar />
      <div className="col">{props.children}</div>
    </div>
  );
}
