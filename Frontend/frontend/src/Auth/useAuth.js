import {useContext} from "react";
import {authContext} from "./AuthContext";

export function useAuth() {
    return useContext(authContext);
}