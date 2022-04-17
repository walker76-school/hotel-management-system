import React, { useEffect } from "react";
import { connect } from "react-redux";
import { useNavigate } from "react-router-dom";

function Home(props) {

    let navigate = useNavigate();

    useEffect(() => {
        console.log(props.currentUser);
        let roles = props.currentUser != null ? props.currentUser.roleName : ['ROLE_USER'];
    
        if(roles.includes("ROLE_ADMIN")) {
            console.log("navigating to admin panel");
            navigate("/admin/reservation/view");
        }
    }, [navigate]);

    return (
        <div>
            Customer Home
        </div>
    );
}

const mapStateToProps = (state) => ({
    currentUser: state.user.currentUser 
})

export default connect(mapStateToProps)(Home);
