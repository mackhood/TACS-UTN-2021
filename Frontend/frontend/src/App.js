import './App.css';
import {useEffect, useState} from "react";

import axios from 'axios';

function App() {
    const [loading, setLoading] = useState(true);
    const [response, setResponse] = useState(null);
    useEffect(() => {
        axios.get("backend:8080/")
            .then(res => {
                setResponse(res.data);
                setLoading(false);
            })
            .catch(err => console.log(err));

    }, [])
  return (
    <>
        <h3>TACS-UTN-2021</h3>
        {loading ? <>Cargando...</> : response}
    </>
  );
}

export default App;
