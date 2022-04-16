import { combineReducers } from 'redux';

import hotelReducer from 'redux/hotel/Hotel.reducer';
import userReducer from 'redux/user/User.reducer';

const rootReducer = combineReducers({
    hotel: hotelReducer,
    user: userReducer
});

export default rootReducer;