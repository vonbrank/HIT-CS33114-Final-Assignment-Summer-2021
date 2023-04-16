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
import { useAppDispatch } from "../../store/hooks";
import { login } from "../../store/slice/LoginSlice";

const Login = () => {
  const dispatch = useAppDispatch();

  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = () => {
    dispatch(
      login({
        userId: userId,
        password: password,
      })
    );
  };

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
                <TextField
                  label="用户 ID"
                  fullWidth
                  value={userId}
                  onChange={(e) => setUserId(e.target.value)}
                />
                <TextField
                  label="密码"
                  type={showPassword ? "text" : "password"}
                  fullWidth
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
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
              <Button fullWidth variant="contained" onClick={handleSubmit}>
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
