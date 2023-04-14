import { AppBar, Box, Toolbar, Typography, Stack, alpha } from "@mui/material";
import Container from "@mui/material/Container";
import { Outlet } from "react-router-dom";

export const AppDefaultLayout = () => {
  return (
    <Box>
      <AppBar>
        <Toolbar>
          <Typography variant="h5">Coursety</Typography>
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
            color: (theme) => alpha(theme.palette.common.white, 0.8),
            "& .footer-img": {
              objectFit: "cover",
              width: "100%",
              height: "100%",
              position: "absolute",
              top: 0,
              left: 0,
              zIndex: 0,
            },
          }}
          height="6.4rem"
          alignItems={"center"}
          justifyContent="center"
        >
          <Container>
            <Typography
              sx={{
                zIndex: 1,
              }}
            >
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
