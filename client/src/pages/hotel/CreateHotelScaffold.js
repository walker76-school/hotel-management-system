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
import { createHotelScaffold } from 'pages/hotel/API';
import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';
import Stack from '@mui/material/Stack';
import { connect } from 'react-redux';
import { setCurrentHotelId } from 'redux/hotel/Hotel.actions';

const theme = createTheme();

function CreateHotelScaffold(props) {

    const navigate = useNavigate();

    let [alert, setAlert] = useState(null);
    let [name, setName] = useState({
        value: ''
    });
    let [addressLineOne, setAddressLineOne] = useState({
        value: ''
    });
    let [addressLineTwo, setAddressLineTwo] = useState({
        value: ''
    });
    let [city, setCity] = useState({
        value: ''
    });
    let [state, setState] = useState({
        value: ''
    });
    let [zip, setZip] = useState({
        value: ''
    });
    let [numFloors, setNumFloors] = useState({
        value: ''
    });
    let [numRooms, setNumRooms] = useState({
        value: ''
    });
    let [phase, setPhase] = useState(1);

    const setters = {
        name: setName,
        addressLineOne: setAddressLineOne,
        addressLineTwo: setAddressLineTwo,
        city: setCity,
        state: setState,
        zip: setZip,
        numFloors: setNumFloors,
        numRooms: setNumRooms
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
        console.log(name);
        console.log(addressLineOne);
        console.log(addressLineTwo);
        console.log(city);
        console.log(state);
        console.log(zip);
        console.log(numFloors);
        console.log(numRooms);
    }

    const isFormInvalid = () => {
        return !(
            name.validateStatus === 'success' &&
            addressLineOne.validateStatus === 'success' &&
            (addressLineTwo.validateStatus === 'success' || addressLineTwo.value === '') &&
            city.validateStatus === 'success' &&
            state.validateStatus === 'success' &&
            zip.validateStatus === 'success' &&
            numFloors.validateStatus === 'success' &&
            numRooms.validateStatus === 'success'
        );
    };

    const handleNext = () => {
        setPhase(phase + 1);
    }

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

        const createHotelScaffoldRequest = {
            name: name.value,
            addressLineOne: addressLineOne.value,
            addressLineTwo: addressLineTwo.value,
            city: city.value,
            state:state.value,
            zip: zip.value,
            numFloors: numFloors.value,
            numRooms: numRooms.value
        };

        console.log('createHotelScaffoldRequest', createHotelScaffoldRequest);

        createHotelScaffold(createHotelScaffoldRequest)
            .then(response => {
                setAlert(
                    <Alert severity="success">
                        <AlertTitle>Success</AlertTitle>
                        Hotel successfully created!
                    </Alert>
                );

                // If we're not currently linked to a hotel
                if(props.currentHotelId === -1) {
                    props.setCurrentHotelIdFn(response.id);
                    navigate("/admin/link");
                    
                } else {
                    navigate("/admin/viewreservations");
                }
            }).catch(error => {
                setAlert(
                    <Alert severity="error">
                        <AlertTitle>Error</AlertTitle>
                        Sorry! Something went wrong. Please try again!
                    </Alert>
                );
            });
    };

    // First name, last name, zip, state, numFloors
    const formPhaseOne = (
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
                margin="normal"
                required
                fullWidth
                name="name"
                label="Name"
                type="name"
                id="name"
                autoComplete="name"
                value={name.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="addressLineOne"
                label="Address Line One"
                type="addressLineOne"
                id="addressLineOne"
                autoComplete="addressLineOne"
                value={addressLineOne.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="addressLineTwo"
                label="Address Line Two"
                type="addressLineTwo"
                id="addressLineTwo"
                autoComplete="addressLineTwo"
                value={addressLineTwo.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="city"
                label="City"
                type="city"
                id="city"
                autoComplete="city"
                value={city.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="state"
                label="State"
                type="state"
                id="state"
                autoComplete="state"
                value={state.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="zip"
                label="Zip"
                type="zip"
                id="zip"
                autoComplete="zip"
                value={zip.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <Button
                onClick={handleNext}
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
            >
                Next
            </Button>
        </Box>
    );

    const formPhaseTwo = (
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
                margin="normal"
                required
                fullWidth
                id="numFloors"
                label="Number of Floors"
                name="numFloors"
                autoComplete="numFloors"
                value={numFloors.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                id="numRooms"
                label="Number of Rooms per Floor"
                name="numRooms"
                autoComplete="numRooms"
                value={numRooms.value}
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
    );

    let currentForm = null;
    switch(phase) {
        case 1: 
            currentForm = formPhaseOne; 
            break;
        case 2: 
            currentForm = formPhaseTwo; 
            break;
        default:
            console.log('Trying to render phase ' + phase + ' for signup form.');
            break;
    }
  
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
              Create Hotel
            </Typography>
            {currentForm}
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
    return {
        setCurrentHotelIdFn: (currentHotelId) => dispatch(setCurrentHotelId(currentHotelId))
    }
  }

export default connect(mapStateToProps, mapDispatchToProps)(CreateHotelScaffold);