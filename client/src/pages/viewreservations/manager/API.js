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