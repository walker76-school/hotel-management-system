import React, { Component } from 'react';
import './App.css';
import {
    Route,
    withRouter,
    Switch
} from 'react-router-dom';

import { getCurrentUser } from '../util/APIUtils';
import {ACCESS_TOKEN} from '../constants';
import Home from '../pages/home/Home';
import Login from '../user/login/Login';
import Signup from '../user/signup/Signup';
import AppHeader from '../common/AppHeader';
import NotFound from '../common/NotFound';
import LoadingIndicator from '../common/LoadingIndicator';
import {Layout, notification} from 'antd';
import { createMuiTheme } from '@material-ui/core/styles';
import {ThemeProvider} from "@material-ui/styles";
import PrivateRoute from "../common/PrivateRoute";
import AdminRefresh from "../pages/admin/AdminRefresh";
const {Content} = Layout;

//Formatting
const theme = createMuiTheme({
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

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: null, //Username of logged in
            isAuthenticated: false, //Are they logged in
            isLoading: false
        };
        
        this.handleLogout = this.handleLogout.bind(this);
        this.loadCurrentUser = this.loadCurrentUser.bind(this);
        this.handleLogin = this.handleLogin.bind(this);

        notification.config({
            placement: 'topRight',
            top: 70,
            duration: 3,
        });
    }

    loadCurrentUser() {
        this.setState({
            isLoading: true
        });

        getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                    isAuthenticated: true,
                    isLoading: false
                });
            }).catch(error => {
            this.setState({
                isLoading: false
            });
        });
    }

    componentDidMount() {
        this.loadCurrentUser();
    }

    handleLogout(redirectTo = "/", notificationType = "success", description = "You're successfully logged out.") {
        localStorage.removeItem(ACCESS_TOKEN);

        this.setState({
            currentUser: null,
            isAuthenticated: false
        });

        this.props.history.push(redirectTo);

        notification[notificationType]({
            message: 'Hotel Management System',
            description: description,
        });
    }

    handleLogin(redirectTo = "/") {
        notification.success({
            message: 'Hotel Management System',
            description: "You're successfully logged in.",
        });
        this.setState({
            isLoading: true
        });

        getCurrentUser().then(response => {
            console.log(response);
            this.setState({
                currentUser: response,
                isAuthenticated: true,
                isLoading: false
            });
        }).catch(error => {
            this.setState({
                isLoading: false
            });
        });

        this.props.history.push(redirectTo);
    }

    setSchool(school) {
        this.setState({clickedSchool: school});
    }

    render() {
        if (this.state.isLoading) {
            return <LoadingIndicator/>
        }
        return (
            <ThemeProvider theme={theme}>
                <Layout className="app-container">
                <AppHeader isAuthenticated={this.state.isAuthenticated}
                           currentUser={this.state.currentUser}
                           onLogout={this.handleLogout}
                           onSubChange={this.onSubChange}/>

                <Content className="app-content">
                    <div className="container">
                        <Switch>
                            <Route exact path="/" render={(props) => <Home isAuthenticated={this.state.isAuthenticated}  {...props} />}/>
                            <Route path="/signup" component={Signup}/>
                            <Route path="/login" render={(props) => <Login onLogin={this.handleLogin} {...props} />}/>

                            <PrivateRoute exact path="/admin/refresh">
                                <AdminRefresh/>
                            </PrivateRoute>

                            <Route component={NotFound}/>
                        </Switch>
                    </div>
                </Content>
            </Layout>
            </ThemeProvider>
        );
    }
}

export default withRouter(App);
