import React from "react";
import axios from "axios";
import {REACT_APP_REST_API_URL} from "../Common/Constants";

export const apiAxiosInstance = axios.create({
    baseURL: REACT_APP_REST_API_URL,
    timeout: 1000,
    headers: { 'Content-Type': 'application/json'}
});
