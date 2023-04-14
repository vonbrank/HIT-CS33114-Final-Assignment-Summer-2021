import React, { useEffect, useMemo, useState } from "react";
import { useMediaQuery } from "@mui/material";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";

interface AppThemeProps {
  darkMode?: "light" | "dark" | "follow-system";
  children?: React.ReactNode;
}

export const AppThemeProvider = (props: AppThemeProps) => {
  const { darkMode = "follow-system", children } = props;

  const prefersDarkMode = useMediaQuery("(prefers-color-scheme: dark)");
  const actualDarkMode = useMemo(() => {
    switch (darkMode) {
      case "follow-system":
        return prefersDarkMode;
      case "light":
        return false;
      case "dark":
        return true;
    }
  }, [prefersDarkMode, darkMode]);

  const theme = useMemo(
    () =>
      createTheme({
        typography: {
          htmlFontSize: 10,
        },
        palette: {
          mode: actualDarkMode ? "dark" : "light",
        },
      }),
    [actualDarkMode]
  );

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      {children}
    </ThemeProvider>
  );
};
