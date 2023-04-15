import React, { useState } from "react";
import {
  Stack,
  Card,
  CardContent,
  Typography,
  Container,
  TextField,
  Button,
  InputAdornment,
  IconButton,
} from "@mui/material";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import { Box } from "@mui/material";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  return (
    <Stack
      sx={{
        position: "absolute",
        left: "50%",
        top: "50%",
        transform: "translate(-50%, -50%)",
        minWidth: "48rem",
      }}
    >
      <Container>
        <Card>
          <CardContent sx={{ padding: "2.4rem" }}>
            <Stack spacing={"2.4rem"}>
              <Box sx={{ position: "relative" }}>
                <Typography textAlign="center" variant="h5">
                  登录
                </Typography>
                <IconButton
                  sx={{
                    position: "absolute",
                    left: 0,
                    top: "50%",
                    transform: "translateY(-50%)",
                  }}
                  onClick={() => navigate(-1)}
                >
                  <ArrowBackIosNewIcon />
                </IconButton>
              </Box>
              <Stack spacing="2rem">
                <TextField label="用户 ID" fullWidth />
                <TextField
                  label="密码"
                  type={showPassword ? "text" : "password"}
                  fullWidth
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="end">
                        <IconButton
                          aria-label="toggle password visibility"
                          onClick={() => setShowPassword((current) => !current)}
                        >
                          {showPassword ? <VisibilityOff /> : <Visibility />}
                        </IconButton>
                      </InputAdornment>
                    ),
                  }}
                />
              </Stack>
              <Button fullWidth variant="contained">
                登录
              </Button>
            </Stack>
          </CardContent>
        </Card>
      </Container>
    </Stack>
  );
};

export default Login;
