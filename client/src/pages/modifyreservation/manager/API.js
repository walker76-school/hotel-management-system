import {request} from 'util/APIUtils';
import {API_BASE_URL, ACCESS_TOKEN} from "constants";


export function modifyHotelReservation(managerCreateReservationRequest) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/reservation/",
        method: 'PUT',
        body: JSON.stringify(managerCreateReservationRequest)
    });
}