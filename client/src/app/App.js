import React, { useState, useEffect } from 'react';
import './App.css';
import {
    Routes,
    Route,
    useNavigate,
  } from "react-router-dom";

import { getCurrentUser } from '../util/APIUtils';
import {ACCESS_TOKEN} from '../constants';
import Home from 'pages/home/Home';
import Login from '../user/login/Login';
import Signup from '../user/signup/Signup';
import AppHeader from '../common/AppHeader';
import NotFound from '../common/NotFound';
import LoadingIndicator from '../common/LoadingIndicator';
import { createTheme } from '@mui/material/styles';
import { ThemeProvider } from "@mui/material/styles";
import PrivateRoute from "../common/PrivateRoute";
import AdminRefresh from "../pages/admin/AdminRefresh";
import ManagerCreateReservation from '../pages/createreservation/manager/ManagerCreateReservation';
import ManagerViewReservations from '../pages/viewreservations/manager/ManagerViewReservations';

//Formatting
const theme = createTheme({
        palette: {
            primary: {
                main: '#3773B0'
            },
            info: {
                light: '#3773B0',
                main: '#3773B0',
                dark: '#3773B0'
            }
        }
    },
);

export default function App(props) {

    const [currentUser, setCurrentUser] = useState(null);
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        loadCurrentUser();
    }, []);

    const loadCurrentUser = () => {
        setIsLoading(true);

        getCurrentUser()
            .then(response => {
                setCurrentUser(response);
                setIsAuthenticated(true);
                setIsLoading(false);
            }).catch(error => {
                setIsLoading(false);
            });
    }

    const handleLogout = (redirectTo = "/") => {
        localStorage.removeItem(ACCESS_TOKEN);
        setCurrentUser(null);
        setIsAuthenticated(false);
        navigate(redirectTo);

        // notification[notificationType]({
        //     message: 'Hotel Management System',
        //     description: "You're successfully logged out.",
        // });
    }

    const handleLogin = (redirectTo = "/") => {
        // notification.success({
        //     message: 'Hotel Management System',
        //     description: "You're successfully logged in.",
        // });
        setIsLoading(true);

        getCurrentUser()
            .then(response => {
                setCurrentUser(response);
                setIsAuthenticated(true);
                setIsLoading(false);
            }).catch(error => {
                setIsLoading(false);
            });

        navigate(redirectTo);
    }

    if (isLoading) {
        return <LoadingIndicator/>
    }

    return (
        <ThemeProvider theme={theme}>
            <AppHeader isAuthenticated={isAuthenticated}
                        currentUser={currentUser}
                        onLogout={handleLogout} />
            <div className="app-content">
                <div className="container">
                    <Routes>
                        <Route exact path="/" element={<Home user={currentUser} isAuthenticated={isAuthenticated}  {...props} />}/>
                        <Route path="signup" element={<Signup />}/>
                        <Route path="login" element={<Login onLogin={handleLogin} {...props} />}/>
                        <Route path="error" element={<NotFound />}/>
                        <Route path="admin" element={<PrivateRoute />}>
                            <Route path="createreservation" element={<ManagerCreateReservation />}/>
                            <Route path="viewreservations" element={<ManagerViewReservations />}/>
                            <Route path="refresh" element={<AdminRefresh />}/>
                        </Route>
                    </Routes>
                </div>
            </div>
        </ThemeProvider>
    );
    
}
