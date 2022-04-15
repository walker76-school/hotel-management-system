import React, { Component } from 'react';
import {
    Link,
    withRouter
} from 'react-router-dom';
import './AppHeader.css';
import { Layout, Menu, Dropdown, Icon } from 'antd';
import {ACCESS_TOKEN} from "../constants";
import {getCurrentUser} from "../util/APIUtils";
const Header = Layout.Header;
    
class AppHeader extends Component {
    constructor(props) {
        super(props);   
        this.handleMenuClick = this.handleMenuClick.bind(this);
    }

    handleMenuClick({ key }) {
      if(key === "logout") {
        this.props.onLogout();
      }
    }

    render() {
      let menuItems;
      if(this.props.currentUser) {
        menuItems = [
          <Menu.Item key="/">
            <Link to="/">
              <Icon style={{ color: '#3773B0'}} type="home" className="nav-icon" />
            </Link>
          </Menu.Item>,
          <Menu.Item key="/profile" className="profile-menu">
              <ProfileDropdownMenu 
                currentUser={this.props.currentUser} 
                handleMenuClick={this.handleMenuClick}/>
          </Menu.Item>
        ]; 
      } else {
        //Handle if current user is not saved
        if(localStorage.getItem(ACCESS_TOKEN)) {
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
        }
        menuItems = [
          <Menu.Item key="/login">
            <Link to="/login">Log In</Link>
          </Menu.Item>,
          <Menu.Item key="/signup">
            <Link to="/signup">Sign Up</Link>
          </Menu.Item>
        ];
      }

      return (
          <Header className="app-header">
          <div className="container">
            <div className="app-title" >
                <a href="/">
                    <h4>Hotel Management System</h4>
                </a>
            </div>
            <Menu
              className="app-menu"
              mode="horizontal"
              selectedKeys={[this.props.location.pathname]}
              style={{ lineHeight: '64px' }} >
                {menuItems}
            </Menu>
          </div>
        </Header>
      );
    }
}

function ProfileDropdownMenu(props) {
  const dropdownMenu = (
    //Dropdown menu creation
    <Menu onClick={props.handleMenuClick} className="profile-dropdown-menu">
      <Menu.Item key="user-info" className="dropdown-item" disabled>
        <div className="username-info">
          @{props.currentUser.username}
        </div>
      </Menu.Item>
      <Menu.Divider />
      <Menu.Item key="logout" className="dropdown-item">
        Logout
      </Menu.Item>
    </Menu>
  );

  return (
    <Dropdown 
      overlay={dropdownMenu} 
      trigger={['click']}
      getPopupContainer = { () => document.getElementsByClassName('profile-menu')[0]}>
      <a className="ant-dropdown-link">
         <Icon type="user" className="nav-icon" style={{marginRight: 0, color: '#3773B0'}} /> <Icon type="down" />
      </a>
    </Dropdown>
  );
}

export default withRouter(AppHeader);