import axios from 'axios'
import {REACT_APP_LOGIN_REST_API_URL} from "../Common/Constants";


// const LOGIN_REST_API_URL = 'http://localhost:8080';


class AuthenticationService {


    login(user){
        return axios.post(REACT_APP_LOGIN_REST_API_URL + '/login' ,user);
    }

    register(user){
        return axios.post(REACT_APP_LOGIN_REST_API_URL + '/users' , user);
    }


}

export default new AuthenticationService();
