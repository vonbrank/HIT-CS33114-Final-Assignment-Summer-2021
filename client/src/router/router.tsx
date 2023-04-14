import Button from "@mui/material/Button";
import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { AppDefaultLayout } from "../components/Container";
import Home from "../pages/Home";

const customRouter = createBrowserRouter([
  {
    path: "/",
    element: <AppDefaultLayout />,
    children: [
      {
        path: "/",
        element: <Home />,
      },
    ],
  },
]);

export default customRouter;
