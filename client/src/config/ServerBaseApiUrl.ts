const SERVICE_BASE_API_URLS: {
    [index: string]: string;
  } = {
    localhost: "http://localhost:5000/api",
  };
  
  export const getServiceBaseURL: () => string = () => {
    const hostname = window.location.hostname;
    const baseURL = SERVICE_BASE_API_URLS[hostname];
  
    return baseURL || "http://localhost:5000/api";
  };
  
  export {};