import {SET_CURRENT_HOTEL_ID} from './Hotel.types';

export default function hotelReducer(state = { 
    currentHotelId: -1
}, action) {
    switch (action.type) {
      case SET_CURRENT_HOTEL_ID:
        return { currentHotelId: action.currentHotelId }
      default:
        return state
    }
}