import React from "react";
import {
  AppBar,
  Box,
  Toolbar,
  Typography,
  Stack,
  alpha,
  Container,
  Button,
} from "@mui/material";

const Home = () => {
  return (
    <Stack
      sx={{
        position: "absolute",
        left: "50%",
        top: "50%",
        transform: "translate(-50%, -50%)",
        width: "100%",
        color: (theme) => alpha(theme.palette.common.white, 0.9),
      }}
    >
      <Container>
        <Stack alignItems={"center"} spacing="2.4rem">
          <Typography
            variant="h4"
            sx={{
              textAlign: "center",
              "& span": {
                display: "block",
                marginY: "1.2rem",
              },
            }}
          >
            <span>现代 Web 技术驱动的</span>
            <span>学生成绩管理系统</span>
          </Typography>
          <Button variant="outlined" color="inherit">
            登陆
          </Button>
        </Stack>
      </Container>
    </Stack>
  );
};

export default Home;
