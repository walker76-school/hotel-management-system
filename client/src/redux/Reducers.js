import { combineReducers } from 'redux';

import hotelReducer from './hotel/Hotel.reducer';

function userReducer(state = { 
    currentUser: null 
}, action) {
    switch (action.type) {
      case 'user/setUser':
        return { currentUser: action.currentUser }
      case 'user/logout':
        return { currentUser: null }
      default:
        return state
    }
}

const rootReducer = combineReducers({
    hotel: hotelReducer,
    user: userReducer
});

export default rootReducer;