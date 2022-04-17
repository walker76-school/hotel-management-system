import {SET_CURRENT_RESERVATION} from 'redux/reservation/Reservation.types';

export const setCurrentReservation = (currentReservation) => {
    return {
        type: SET_CURRENT_RESERVATION,
        payload: currentReservation
    };
};

export const clearCurrentReservation = () => {
    return {
        type: SET_CURRENT_RESERVATION,
        payload: null
    };
};