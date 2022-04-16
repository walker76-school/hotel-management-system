import { SET_CURRENT_HOTEL_ID } from 'redux/hotel/Hotel.types';

export const setCurrentHotelId = (hotelId) => {
    return {
        type: SET_CURRENT_HOTEL_ID,
        payload: hotelId
    };
};