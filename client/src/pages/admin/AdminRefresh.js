import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import LoadingIndicator from "../../common/LoadingIndicator";
import { refresh } from "./API";

export default function AdminRefresh() {

    const navigate = useNavigate();

    useEffect(() => {
        refresh()
        .then(result => {
            console.log(result);
            // notification.info({
            //     message: 'Hotel Management System',
            //     description: "Refreshed data."
            // });
            navigate('/');
        })
        .catch(error => {
            // notification.error({
            //     message: 'Hotel Management System',
            //     description: "Couldn't refresh data."
            // });
            navigate('/');
        })
    }, [navigate]);

    return (
        <LoadingIndicator />
    );
    
}