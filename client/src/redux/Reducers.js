import { combineReducers } from 'redux';

import hotelReducer from 'redux/hotel/Hotel.reducer';
import userReducer from 'redux/user/User.reducer';
import reservationReducer from 'redux/reservation/Reservation.reducer';

const rootReducer = combineReducers({
    hotel: hotelReducer,
    user: userReducer,
    reservation: reservationReducer
});

export default rootReducer;