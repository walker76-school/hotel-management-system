import {request} from 'util/APIUtils';
import {ACCESS_TOKEN, API_BASE_URL} from "constants";

export function createHotelScaffold(createHotelScaffoldRequest) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/hotel/construct",
        method: 'POST',
        body: JSON.stringify(createHotelScaffoldRequest)
    });
}