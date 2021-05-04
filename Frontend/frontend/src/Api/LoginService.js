import axios from 'axios'
import {REACT_APP_LOGIN_REST_API_URL} from "../Common/Constants";


// const LOGIN_REST_API_URL = 'http://localhost:8080';


class LoginService {


    login(user){
        return axios.post(REACT_APP_LOGIN_REST_API_URL + '/login' ,user);
    }

    logOut(user){
        return axios.post(REACT_APP_LOGIN_REST_API_URL + '/logOut' ,user);
    }

    signIn(newUser){
        return axios.post(REACT_APP_LOGIN_REST_API_URL + '/signIn' , newUser);
    }


}

export default new LoginService();
