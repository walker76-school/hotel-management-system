import React from "react";
import { useNavigate } from "react-router-dom";

export default function Home(props) {

    let navigate = useNavigate();

    console.log(props.user);
    let roles = props.user != null ? props.user.roleName : ['ROLE_USER'];

    if(roles.includes("ROLE_ADMIN")) {
        console.log("navigating to admin panel");
        navigate("/admin/viewreservations");
        return (<div></div>);
    }

    return (
        <div>
            Customer Home
        </div>
    );
}
