import React, {Component} from 'react';
import { withRouter} from 'react-router-dom';
import LoadingIndicator from "../../common/LoadingIndicator";
import {notification} from "antd";
import { refresh} from "./API";

class AdminRefresh extends Component {

    componentDidMount() {

        refresh()
        .then(result => {
            console.log(result);
            notification.info({
                message: 'Hotel Management System',
                description: "Refreshed data."
            });
            this.props.history.push('/');
        })
        .catch(error => {
            notification.error({
                message: 'Hotel Management System',
                description: "Couldn't refresh data."
            });
            this.props.history.push('/');
        })
    }

    render() {
        return (
            <LoadingIndicator />
        );
    }
}

export default withRouter(AdminRefresh);