import { SET_CURRENT_USER } from 'redux/user/User.types';

export const setCurrentUser = (currentUser) => {
    return {
        type: SET_CURRENT_USER,
        payload: currentUser
    };
};

export const logout = () => {
    return {
        type: SET_CURRENT_USER,
        payload: null
    };
};