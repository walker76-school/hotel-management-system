import * as React from 'react';
import {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';
import Stack from '@mui/material/Stack';
import { connect } from 'react-redux';
import { createHotelReservation } from './API';
import * as moment from 'moment';

const theme = createTheme();

function ManagerCreateReservation(props) {

    const navigate = useNavigate();

    let [alert, setAlert] = useState(null);

    let [customerUsername, setCustomerUsername] = useState({
        value: ''
    });
    let [startDate, setStartDate] = useState({
        value: ''
    });
    let [endDate, setEndDate] = useState({
        value: ''
    });
    let [roomId, setRoomId] = useState({
        value: ''
    });

    const setters = {
        customerUsername: setCustomerUsername,
        startDate: setStartDate,
        endDate: setEndDate,
        roomId: setRoomId
    }

    const handleInputChange = (event, validationFun) => {
        const target = event.target;
        const inputName = target.name;        
        const inputValue = target.value;

        const setter = setters[inputName];
        setter({
            value: inputValue,
            ...validationFun(inputValue)
        });
    }

    const debug = () => {
        console.log(customerUsername);
        console.log(startDate);
        console.log(endDate);
        console.log(roomId);
    }

    const isFormInvalid = () => {
        return !(
            customerUsername.validateStatus === 'success' &&
            startDate.validateStatus === 'success' &&
            endDate.validateStatus === 'success' &&
            roomId.validateStatus === 'success'
        );
    };

    const validatePassthrough = () => {
        return {
            validateStatus: 'success',
            errorMsg: null,
        };
    }

    const handleSubmit = () => {
        
        debug();

        if(isFormInvalid()) {
            setAlert(
                <Alert severity="error">
                    <AlertTitle>Error</AlertTitle>
                    Please try again!
                </Alert>
            );
        }

        const managerCreateReservationRequest = {
            customerUsername: customerUsername.value,
            startDate: moment(startDate.value, 'MM/DD/YYYY'),
            endDate: moment(endDate.value, 'MM/DD/YYYY'),
            roomId: roomId.value
        };

        console.log('managerCreateReservationRequest', managerCreateReservationRequest);

        // TODO
        createHotelReservation(managerCreateReservationRequest)
            .then(response => {
                console.log('.then');
                setAlert(
                    <Alert severity="success">
                        <AlertTitle>Success</AlertTitle>
                        Hotel successfully created!
                    </Alert>
                );

                navigate("/admin/viewreservations");
            }).catch(error => {
                setAlert(
                    <Alert severity="error">
                        <AlertTitle>Error</AlertTitle>
                        Sorry! Something went wrong. Please try again!
                    </Alert>
                );
            });
    };
  
    return (
      <ThemeProvider theme={theme}>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <Stack sx={{ width: '100%' }} spacing={2}>{alert}</Stack>
          <Box
            sx={{
              marginTop: 8,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Typography component="h1" variant="h5">
              Create Reservation
            </Typography>
            <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    name="customerUsername"
                    label="Customer ID"
                    type="customerUsername"
                    id="customerUsername"
                    autoComplete="customerUsername"
                    value={customerUsername.value}
                    onChange={(event) => handleInputChange(event, validatePassthrough)} 
                />
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    name="startDate"
                    label="Start Date (mm/dd/yyyy)"
                    type="startDate"
                    id="startDate"
                    autoComplete="startDate"
                    value={startDate.value}
                    onChange={(event) => handleInputChange(event, validatePassthrough)} 
                />
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    name="endDate"
                    label="End Date (mm/dd/yyyy)"
                    type="endDate"
                    id="endDate"
                    autoComplete="endDate"
                    value={endDate.value}
                    onChange={(event) => handleInputChange(event, validatePassthrough)} 
                />
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    name="roomId"
                    label="Room ID"
                    type="roomId"
                    id="roomId"
                    autoComplete="roomId"
                    value={roomId.value}
                    onChange={(event) => handleInputChange(event, validatePassthrough)} 
                />
                <Button
                    onClick={handleSubmit}
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3, mb: 2 }}
                >
                    Submit
                </Button>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    );
  }

  
const mapStateToProps = (state) => ({
    currentUser: state.user.currentUser,
    currentHotelId: state.hotel.currentHotelId 
})

const mapDispatchToProps = (dispatch) => {
    return {}
}

export default connect(mapStateToProps, mapDispatchToProps)(ManagerCreateReservation);