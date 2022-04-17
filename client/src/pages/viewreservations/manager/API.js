import {request} from 'util/APIUtils';
import {API_BASE_URL, ACCESS_TOKEN} from "constants";


export function getManagerInfo(managerId) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/manager/" + managerId,
        method: 'GET'
    });
}

export function getReservationsByHotelId(hotelId) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/reservation/byHotelId/" + hotelId,
        method: 'GET'
    });
}

export function cancelReservationById(reservationId) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/reservation/" + reservationId,
        method: 'DELETE'
    });
}