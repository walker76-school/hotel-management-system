import React, {useEffect} from 'react';
import {
    Outlet,
    useNavigate
} from "react-router-dom";
import {ACCESS_TOKEN} from "../constants";

export default function PrivateRoute(props) {

    const navigate = useNavigate();
    useEffect(() => {
        if(localStorage.getItem(ACCESS_TOKEN) === null) {
            console.log('No access token so not allowed in private routes');
            navigate('/login');
        }
    }, [navigate]);

    return (
        <Outlet />
    );
}