import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import MenuItem from '@mui/material/MenuItem';
import Menu from '@mui/material/Menu';
import AccountCircle from '@mui/icons-material/AccountCircle';
import Button from '@mui/material/Button';
import {ACCESS_TOKEN} from "constants";
import {getCurrentUser} from "util/APIUtils";
import { useNavigate } from "react-router-dom";
import { connect } from 'react-redux';
import { setCurrentUser } from 'redux/user/User.actions';

function AppHeader(props) {

  const [anchorEl, setAnchorEl] = React.useState(null);
  const [currentUser, setCurrentUser] = React.useState(null);
  const [isAuthenticated, setIsAuthenticated] = React.useState(false);
  const [isLoading, setIsLoading] = React.useState(false);
  let navigate = useNavigate();

  const isMenuOpen = Boolean(anchorEl);

  const handleProfileMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    handleMenuClose();
    props.onLogout();
  }

  const onLoginClicked = () => {
    navigate('/login');
  }
  
  const onRegisterClicked = () => {
    navigate('/signup');
  }

  const menuId = 'primary-menu';
  let menuItems;
  if(props.currentUser !== null) {
    menuItems = 
      <Box sx={{ display: { xs: 'none', md: 'flex' } }}>
        <IconButton
          size="large"
          edge="end"
          aria-label="account of current user"
          aria-controls={menuId}
          aria-haspopup="true"
          onClick={handleProfileMenuOpen}
          color="inherit"
        >
          <AccountCircle />
        </IconButton>
      </Box>;
  } else {
    // //Handle if current user is not saved
    // if(localStorage.getItem(ACCESS_TOKEN)) {
    //   getCurrentUser()
    //   .then(response => {
    //     console.log(response);
    //     setCurrentUser(response);
    //     setIsLoading(false);
    //   }).catch(error => {
    //     setIsLoading(false);
    //   });
    // }

    menuItems = 
      <Box sx={{ display: { xs: 'none', md: 'flex' } }}>
        <Button color="inherit" onClick={onLoginClicked}>Login</Button>
        <Button color="inherit" onClick={onRegisterClicked}>Register</Button>
      </Box>;
  }

  const renderMenu = (
    <Menu
      anchorEl={anchorEl}
      anchorOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      id={menuId}
      keepMounted
      transformOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      open={isMenuOpen}
      onClose={handleMenuClose}
    >
      <MenuItem onClick={handleLogout}>Logout</MenuItem>
      {/* <MenuItem onClick={handleMenuClose}>My account</MenuItem> */}
    </Menu>
  );

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ display: { xs: 'none', sm: 'block' } }}
          >
            Hotel Management System
          </Typography>
          <Box sx={{ flexGrow: 1 }} />
          {menuItems}
        </Toolbar>
      </AppBar>
      {renderMenu}
    </Box>
  );
}

const mapStateToProps = (state) => ({
  currentUser: state.user.currentUser 
})

export default connect(mapStateToProps)(AppHeader);