import React, {useEffect} from "react";
import { useNavigate } from "react-router-dom";
import { getManagerInfo } from "./API";
import {setCurrentHotelId} from 'redux/hotel/Hotel.actions';
import { connect } from "react-redux";

function ManagerViewReservations(props) {

    let navigate = useNavigate();

    useEffect(() => {

        if(props.currentUser === null) {
            return;
        }

        if(props.currentHotelId === -1) {
            getManagerInfo(props.currentUser.id)
                .then((response) => {
                    console.log(response);
                    if(response.hotelId === null) {
                        navigate('/admin/link');
                    } else {
                        props.setCurrentHotelIdFn(response.hotelId);
                    }
                });
        }
        
    }, [navigate]);

    console.log("ManagerViewReservations");

    return (
        <div>Manager View Reservations {props.currentHotelId}</div>
    );
    
}

const mapStateToProps = (state) => ({
    currentUser: state.user.currentUser,
    currentHotelId: state.hotel.currentHotelId 
})

const mapDispatchToProps = (dispatch) => {
    return {
        setCurrentHotelIdFn: (currentHotelId) => dispatch(setCurrentHotelId(currentHotelId))
    }
  }

export default connect(mapStateToProps, mapDispatchToProps)(ManagerViewReservations);
