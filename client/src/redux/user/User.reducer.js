import {SET_CURRENT_USER} from 'redux/user/User.types';

const initialState = {
  currentUser: null
}

export default function userReducer(state = initialState, action) {
    switch (action.type) {
      case SET_CURRENT_USER:
        return { 
          ...state,
          currentUser: action.payload 
        }
      default:
        return state
    }
}