import React, {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";
import { cancelReservationById, getManagerInfo, getReservationsByHotelId } from "./API";
import {setCurrentHotelId} from 'redux/hotel/Hotel.actions';
import { connect } from "react-redux";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import * as moment from 'moment';
import { setCurrentReservation } from "redux/reservation/Reservation.actions";

const theme = createTheme();

function ManagerViewReservations(props) {

    let navigate = useNavigate();

    const [reservations, setReservations] = useState([]);

    useEffect(() => {

        if(props.currentUser === null) {
            console.log('navigating to login');
            // navigate('/login');
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
    }, [navigate, props.currentUser]);

    useEffect(() => {
        fetchReservations();
    }, [props.currentHotelId])

    console.log("ManagerViewReservations");

    const fetchReservations = () => {
        if(props.currentHotelId !== -1 && props.currentHotelId !== undefined) {
            console.log("Fetching reservations with " + props.currentHotelId);
            getReservationsByHotelId(props.currentHotelId)
                .then((response) => {
                    console.log(response);
                    setReservations(response);
                })
        }
    }
    
    const onCancelClicked = (reservationId) => {
        console.log('cancel', reservationId);
        cancelReservationById(reservationId)
        .then(() => {
            fetchReservations();
        })
        .catch((error) => {
            console.log(error);
            fetchReservations();
        })
    }
    
    const onModifyClicked = (reservation) => {
        console.log('modify', reservation);
        props.setCurrentReservationFn(reservation);
        navigate('/admin/reservation/modify');
    }

    return (
        <div>

            <ThemeProvider theme={theme}>
                <Container component="main">
                    <CssBaseline />
                    <Grid container spacing={2} sx={{ width: '100%' }}>
                        {reservations.map((reservation, ndx) => {
                            return (
                                <Grid item xs={6}>
                                    <Paper sx={{padding: '15px'}}>
                                        <Typography>Reservation ID: {reservation.id}</Typography>
                                        <Typography>Hotel: {reservation.hotelName}</Typography>
                                        <Typography>Room Number: {reservation.roomId}</Typography>
                                        <Typography>Start Date: {moment(reservation.startDate).format('MMMM DD, YYYY')}</Typography>
                                        <Typography>End Date: {moment(reservation.endDate).format('MMMM DD, YYYY')}</Typography>
                                        <Button onClick={() => onCancelClicked(reservation.id)} sx={{marginTop: '15px'}} variant="outlined">Cancel</Button>
                                        <Button onClick={() => onModifyClicked(reservation)} sx={{marginTop: '15px', marginLeft: '15px'}} variant="contained">Modify</Button>
                                    </Paper>
                                </Grid>
                            );
                        })}
                    </Grid>
                </Container>
            </ThemeProvider>
            
            <Grid container sx={{paddingTop: '35px', paddingLeft: '15px'}}>
                <Grid item>
                  <Button href="/admin/reservation/create" variant="contained">
                    Create Reservation
                  </Button>
                </Grid>
              </Grid>
            
        </div>
    );
    
}

const mapStateToProps = (state) => ({
    currentUser: state.user.currentUser,
    currentHotelId: state.hotel.currentHotelId 
})

const mapDispatchToProps = (dispatch) => {
    return {
        setCurrentHotelIdFn: (currentHotelId) => dispatch(setCurrentHotelId(currentHotelId)),
        setCurrentReservationFn: (currentReservation) => dispatch(setCurrentReservation(currentReservation))
    }
  }

export default connect(mapStateToProps, mapDispatchToProps)(ManagerViewReservations);
