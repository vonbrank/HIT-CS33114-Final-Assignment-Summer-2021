import React from "react";
import { RouterProvider } from "react-router-dom";
import router from "./router";
import { AppThemeProvider } from "./theme";

function App() {
  return (
    <AppThemeProvider>
      <RouterProvider router={router} />
    </AppThemeProvider>
  );
}

export default App;
