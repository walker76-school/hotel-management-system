import {SET_CURRENT_RESERVATION} from 'redux/reservation/Reservation.types';

const initialState = {
  currentReservation: null
}

export default function reservationReducer(state = initialState, action) {
    switch (action.type) {
      case SET_CURRENT_RESERVATION:
        return { 
          ...state,
          currentUser: action.payload 
        }
      default:
        return state
    }
}