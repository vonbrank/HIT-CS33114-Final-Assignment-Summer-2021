import React, { useState } from "react";
import {
  AppBar,
  Box,
  Toolbar,
  Typography,
  Stack,
  alpha,
  Button,
  Tabs,
  Tab,
  IconButton,
} from "@mui/material";
import Container from "@mui/material/Container";
import { Outlet, useNavigate } from "react-router-dom";
import GitHubIcon from "@mui/icons-material/GitHub";
import LightModeIcon from "@mui/icons-material/LightMode";
import ModeNightIcon from "@mui/icons-material/ModeNight";

type TabOptionBase = "Home" | "About";

interface TabState {
  currentTabOption: TabOptionBase;
  currentTabList: TabOptionBase[];
}

const baseTabList: TabOptionBase[] = ["Home", "About"];

export const AppDefaultLayout = () => {
  const navigate = useNavigate();

  const [tabState, setTabState] = useState<TabState>({
    currentTabOption: "Home",
    currentTabList: [...baseTabList],
  });

  const handleChangeTabOption = (
    event: React.SyntheticEvent,
    newOption: TabOptionBase
  ) => {
    setTabState((current) => {
      if (
        current.currentTabList.findIndex((option) => option === newOption) !==
        -1
      )
        return {
          ...current,
          currentTabOption: newOption,
        };
      return { ...current };
    });
  };

  const [darkMode, setDarkMode] = useState(false);

  return (
    <Box>
      <AppBar>
        <Toolbar>
          <Stack direction="row" width="100%" spacing="2.4rem">
            <Button
              variant="text"
              color="inherit"
              sx={{ textTransform: "none" }}
              onClick={() => navigate("/")}
            >
              <Typography variant="h5">HIT-Coursety</Typography>
            </Button>
            <Stack
              sx={{ flex: 1 }}
              direction="row"
              justifyContent="space-between"
            >
              <Box
                sx={{
                  color: (theme) => theme.palette.common.white,
                }}
              >
                <Tabs
                  value={tabState.currentTabOption}
                  onChange={handleChangeTabOption}
                  textColor="inherit"
                  sx={{
                    "& .MuiTabs-indicator": {
                      backgroundColor: (theme) => theme.palette.common.white,
                    },
                  }}
                >
                  {tabState.currentTabList.map((option) => (
                    <Tab
                      key={option}
                      label={option}
                      value={option}
                      sx={{
                        fontSize: "1.8rem",
                        textTransform: "none",
                      }}
                    />
                  ))}
                </Tabs>
              </Box>
              <Stack
                direction="row"
                sx={{
                  color: (theme) => theme.palette.common.white,
                  "& .MuiIconButton-root": {
                    width: "4.8rem",
                    height: "4.8rem",
                  },
                }}
              >
                <IconButton color="inherit">
                  <GitHubIcon />
                </IconButton>
                <IconButton
                  color="inherit"
                  onClick={() => setDarkMode((current) => !current)}
                >
                  {darkMode ? <ModeNightIcon /> : <LightModeIcon />}
                </IconButton>
              </Stack>
            </Stack>
          </Stack>
        </Toolbar>
      </AppBar>
      <Stack sx={{ minHeight: "100vh" }}>
        <Toolbar />
        <Stack
          sx={{
            zIndex: 2,
            flex: 1,
          }}
        >
          <Outlet />
        </Stack>
        <Stack
          className="footer"
          sx={{
            position: "relative",
            zIndex: 1,
            color: (theme) => alpha(theme.palette.common.white, 0.9),
            "& .footer-img": {
              objectFit: "cover",
              width: "100%",
              height: "100%",
              position: "absolute",
              top: 0,
              left: 0,
              zIndex: 1,
            },
          }}
          height="6.4rem"
          alignItems={"center"}
          justifyContent="center"
        >
          <Container
            sx={{
              zIndex: 2,
              textAlign: "center",
            }}
          >
            <Typography>
              © 2020 Copyright VonBrank All Rights Reserved.
            </Typography>
          </Container>
          <img
            className="footer-img"
            src="http://en.hit.edu.cn/public/images/img7.jpg"
          />
        </Stack>
      </Stack>
      <Box
        sx={{
          position: "fixed",
          height: "100vh",
          width: "100vw",
          top: 0,
          left: 0,
          "& .background-video": {
            width: "100%",
            height: "100%",
            objectFit: "cover",
          },
        }}
      >
        <Box
          sx={{
            position: "absolute",
            width: "100%",
            height: "100%",
            top: 0,
            left: 0,
            backgroundColor: (theme) => alpha(theme.palette.common.black, 0.6),
          }}
        />
        <video className="background-video" autoPlay muted loop>
          <source
            src="http://en.hit.edu.cn/public/video/z.mp4"
            type="video/mp4"
          />
        </video>
      </Box>
    </Box>
  );
};
