import {useLocation} from "react-router-dom";
import React from "react";

export const PageNotFound = () => {
    let location = useLocation();

    return (
        <div>
            <h3>
                No existe la ruta <code>{location.pathname}</code>
            </h3>
        </div>
    );
}