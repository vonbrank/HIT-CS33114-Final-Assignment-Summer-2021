import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import Axios from "../../utils/Axios";
import { AppDispatch } from "../store";
import { AppResponse } from "../../utils/Axios";

interface UserAuthorizationInfo {
  userId: string;
  password: string;
}

type userType = "Admin" | "Teacher" | "Student" | "None";

interface UserInfo {
  id: string;
  name: string;
  gender: string;
  major: string;
  profession: string;
  userType: userType;
}

interface LoginState {
  userAuthorizationInfo: UserAuthorizationInfo | null;
  userInfo: UserInfo | null;
}

const initialState: LoginState = {
  userAuthorizationInfo: null,
  userInfo: null,
};

export const loginSlice = createSlice({
  name: "navigation",
  initialState,
  reducers: {
    userLogin: (
      state,
      action: PayloadAction<{
        userInfo: UserInfo;
        password: string;
      }>
    ) => {
      const { userInfo, password } = action.payload;

      state.userAuthorizationInfo = {
        userId: userInfo.id,
        password: password,
      };
      state.userInfo = { ...userInfo };
    },
  },
});

export const login = (loginData: UserAuthorizationInfo) => {
  return async (dispatch: AppDispatch) => {
    try {
      const res = await Axios.post<AppResponse<UserInfo>>("/user/login", {
        ...loginData,
      });

      const { data } = res;

      if (data.code === 200) {
        const userInfo = data.data;
        dispatch(
          userLogin({ userInfo: userInfo, password: loginData.password })
        );
      } else {
      }
    } catch (e) {}
  };
};

export const { userLogin } = loginSlice.actions;

export default loginSlice.reducer;
