import {SET_CURRENT_HOTEL_ID} from './Hotel.types';

const initialState = {
  currentHotelId: -1
}

export default function hotelReducer(state = initialState, action) {
    switch (action.type) {
      case SET_CURRENT_HOTEL_ID:
        return { 
          ...state,
          currentHotelId: action.payload 
        }
      default:
        return state
    }
}