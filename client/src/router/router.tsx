import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { AppDefaultLayout } from "../components/Container";
import Home from "../pages/Home";
import Login from "../pages/Login";

const customRouter = createBrowserRouter([
  {
    path: "/",
    element: <AppDefaultLayout />,
    children: [
      {
        path: "/",
        element: <Home />,
      },
      {
        path: "login",
        element: <Login />,
      },
    ],
  },
]);

export default customRouter;
