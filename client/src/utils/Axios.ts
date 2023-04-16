import _axios, { AxiosInstance } from "axios";
import { getServiceBaseURL } from "../config/ServerBaseApiUrl";

const Axios = (baseURL: string): AxiosInstance => {
  const instance = _axios.create({
    baseURL: baseURL || getServiceBaseURL(), //JSON-Server端口位置
    timeout: 2000,
  });

  return instance;
};

enum ResponseCode {
  SUCCESS = 200,
  FAIL = 400,
  UNAUTHORIZED = 401,
  NOT_FOUND = 404,
  INTERNAL_SERVER_ERROR = 500,
}

interface AppSuccessResponse<T> {
  code: ResponseCode.SUCCESS;
  description: string;
  timestamp: string;
  data: T;
}

export type AppResponse<T> =
  | AppSuccessResponse<T>
  | {
      code:
        | ResponseCode.FAIL
        | ResponseCode.UNAUTHORIZED
        | ResponseCode.NOT_FOUND
        | ResponseCode.INTERNAL_SERVER_ERROR;
      description: string;
      timestamp: string;
      data: null;
    };

export { Axios as axios };
export default Axios("");

export {};
