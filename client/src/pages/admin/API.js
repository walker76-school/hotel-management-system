import {request} from './../../util/APIUtils';
import {ACCESS_TOKEN, API_BASE_URL} from "../../constants";

export function refresh() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/admin/refresh",
        method: 'POST'
    });
}

export function linkToHotel(linkRequest) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/manager/link",
        method: 'POST',
        body: JSON.stringify(linkRequest)
    });
}