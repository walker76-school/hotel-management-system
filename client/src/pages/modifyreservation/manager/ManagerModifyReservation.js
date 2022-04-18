import * as React from 'react';
import {useState, useEffect} from 'react';
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
import { modifyHotelReservation } from './API';
import * as moment from 'moment';

const theme = createTheme();

function ManagerModifyReservation(props) {

    const navigate = useNavigate();

    let [alert, setAlert] = useState(null);

    let [customerUsername, setCustomerUsername] = useState({
        value: ""
    });
    let [startDate, setStartDate] = useState({
        value: ""
    });
    let [endDate, setEndDate] = useState({
        value: ""
    });
    let [roomId, setRoomId] = useState({
        value: ""
    });

    let [oldRoomId, setOldRoomId] = useState(-1);

    useEffect(() => {

        console.log('useEffect');

        if(props.currentReservation != null) {
            console.log('Setting proper values');
            setCustomerUsername({
                ...customerUsername,
                value: (props.currentReservation != null ? props.currentReservation.customerUsername : ""),
                ...validatePassthrough(props.currentReservation.customerUsername)
            });
            setStartDate({
                ...startDate,
                value: (props.currentReservation != null ? moment(props.currentReservation.startDate).format('MM/DD/YYYY') : ""),
                ...validatePassthrough(moment(props.currentReservation.startDate).format('MM/DD/YYYY'))
            });
            setEndDate({
                ...endDate,
                value: (props.currentReservation != null ? moment(props.currentReservation.endDate).format('MM/DD/YYYY') : ""),
                ...validatePassthrough(moment(props.currentReservation.endDate).format('MM/DD/YYYY'))
            });
            setRoomId({
                ...roomId,
                value: (props.currentReservation != null ? props.currentReservation.roomId : ""),
                ...validatePassthrough(props.currentReservation.roomId)
            });
            setOldRoomId(props.currentReservation.roomId);
        }
    }, [props.currentReservation])

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
            id: props.currentReservation.id,
            customerUsername: customerUsername.value,
            startDate: moment(startDate.value, 'MM/DD/YYYY'),
            endDate: moment(endDate.value, 'MM/DD/YYYY'),
            roomId: roomId.value,
            oldRoomId: oldRoomId
        };

        console.log('managerCreateReservationRequest', managerCreateReservationRequest);

        // TODO
        modifyHotelReservation(managerCreateReservationRequest)
            .then(response => {
                console.log('.then');
                setAlert(
                    <Alert severity="success">
                        <AlertTitle>Success</AlertTitle>
                        Reservation Modified!
                    </Alert>
                );

                navigate("/admin/reservation/view");
            }).catch(error => {
                console.log(error);
                if(error.toString().includes("SyntaxError")) {
                    navigate("/admin/reservation/view");
                    return;
                } else {
                    setAlert(
                        <Alert severity="error">
                            <AlertTitle>Error</AlertTitle>
                            Sorry! Something went wrong. Please try again!
                        </Alert>
                    );
                }
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
              Modify Reservation
            </Typography>
            <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    name="customerUsername"
                    label="Customer Username"
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
    currentHotelId: state.hotel.currentHotelId,
    currentReservation: state.reservation.currentReservation
})

const mapDispatchToProps = (dispatch) => {
    return {}
}

export default connect(mapStateToProps, mapDispatchToProps)(ManagerModifyReservation);