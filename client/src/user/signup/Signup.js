import * as React from 'react';
import {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { signup, checkUsernameAvailability } from 'util/APIUtils';
import './Signup.css';
import { 
    USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH,
    PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH,
    ACCESS_TOKEN
} from 'constants';

import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';
import Stack from '@mui/material/Stack';

const theme = createTheme();

export default function SignUp(props) {

    const navigate = useNavigate();

    let [alert, setAlert] = useState(null);
    let [username, setUsername] = useState({
        value: ''
    });
    let [password, setPassword] = useState({
        value: ''
    });
    let [firstname, setFirstname] = useState({
        value: ''
    });
    let [lastname, setLastname] = useState({
        value: ''
    });
    let [age, setAge] = useState({
        value: ''
    });
    let [email, setEmail] = useState({
        value: ''
    });
    let [phonenumber, setPhonenumber] = useState({
        value: ''
    });
    let [phase, setPhase] = useState(1);

    const setters = {
        username: setUsername,
        password: setPassword,
        firstname: setFirstname,
        lastname: setLastname,
        age: setAge,
        email: setEmail,
        phonenumber: setPhonenumber
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
        console.log(username);
        console.log(password);
        console.log(firstname);
        console.log(lastname);
        console.log(age);
        console.log(email);
        console.log(phonenumber);
    }

    const isFormInvalid = () => {
        return !(
            username.validateStatus === 'success' &&
            password.validateStatus === 'success' &&
            firstname.validateStatus === 'success' &&
            lastname.validateStatus === 'success' &&
            age.validateStatus === 'success' &&
            email.validateStatus === 'success' &&
            phonenumber.validateStatus === 'success'
        );
    };

    const handleNext = () => {
        setPhase(phase + 1);
    }

    // Validation Functions
    const validateUsername = () => {
        if(username.length < USERNAME_MIN_LENGTH) {
            return {
                validateStatus: 'error',
                errorMsg: `Username is too short (Minimum ${USERNAME_MIN_LENGTH} characters needed.)`
            }
        } else if (username.length > USERNAME_MAX_LENGTH) {
            return {
                validationStatus: 'error',
                errorMsg: `Username is too long (Maximum ${USERNAME_MAX_LENGTH} characters allowed.)`
            }
        } else {
            return {
                validateStatus: 'success',
                errorMsg: null
            }
        }
    };

    const validateUsernameAvailability = () => {

        // First check for client side errors in username
        const usernameValue = username.value;
        const usernameValidation = validateUsername(usernameValue);

        if(usernameValidation.validateStatus === 'error') {
            setUsername({
                username: {
                    value: usernameValue,
                    ...usernameValidation
                }
            });
            return;
        }

        setUsername({
            value: usernameValue,
            validateStatus: 'validating',
            errorMsg: null
        });

        checkUsernameAvailability(usernameValue)
        .then(response => {
            if(response.available) {
                setUsername({
                    value: usernameValue,
                    validateStatus: 'success',
                    errorMsg: null
                });
            } else {
                
                setUsername({
                    value: usernameValue,
                    validateStatus: 'error',
                    errorMsg: 'This username is already taken'
                });
            }
        }).catch(error => {
            setUsername({
                value: usernameValue,
                validateStatus: 'success',
                errorMsg: null
            });
        });
    }

    const validatePassword = (password) => {
        if(password.length < PASSWORD_MIN_LENGTH) {
            return {
                validateStatus: 'error',
                errorMsg: `Password is too short (Minimum ${PASSWORD_MIN_LENGTH} characters needed.)`
            }
        } else if (password.length > PASSWORD_MAX_LENGTH) {
            return {
                validationStatus: 'error',
                errorMsg: `Password is too long (Maximum ${PASSWORD_MAX_LENGTH} characters allowed.)`
            }
        } else {
            return {
                validateStatus: 'success',
                errorMsg: null,
            };            
        }
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

        const signupRequest = {
            username: username.value,
            password: password.value,
            firstName: firstname.value,
            lastName: lastname.value,
            age:age.value,
            email: email.value,
            phoneNumber: phonenumber.value,
        };

        console.log('signupRequest', signupRequest);

        signup(signupRequest)
            .then(response => {
                // notification.success({
                //     message: 'Hotel Management System',
                //     description: "Thank you! You're successfully registered. Please Login to continue!",
                // });
                navigate("/login");
            }).catch(error => {
                setAlert(
                    <Alert severity="error">
                        <AlertTitle>Error</AlertTitle>
                        Sorry! Something went wrong. Please try again!
                    </Alert>
                );
            });
    };

    // First name, last name, email, age, phonenumber
    const formPhaseOne = (
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
                margin="normal"
                required
                fullWidth
                name="firstname"
                label="First Name"
                type="firstname"
                id="firstname"
                autoComplete="firstname"
                value={firstname.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="lastname"
                label="Last Name"
                type="lastname"
                id="lastname"
                autoComplete="lastname"
                value={lastname.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="age"
                label="Age"
                type="age"
                id="age"
                autoComplete="age"
                value={age.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="email"
                label="Email"
                type="email"
                id="email"
                autoComplete="email"
                value={email.value}
                onChange={(event) => handleInputChange(event, validatePassthrough)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                id="phonenumber"
                label="Phone Number"
                name="phonenumber"
                autoComplete="phonenumber"
                value={phonenumber.value}
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
            <Grid container>
                <Grid item>
                    <Link href="/login" variant="body2">
                    {"Already have an account? Sign In"}
                    </Link>
                </Grid>
            </Grid>
        </Box>
    );

    const formPhaseTwo = (
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
                margin="normal"
                required
                fullWidth
                name="username"
                label="Username"
                type="username"
                id="username"
                autoComplete="username"
                value={username.value}
                onChange={(event) => handleInputChange(event, validateUsername)} 
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="password"
                value={password.value}
                onChange={(event) => handleInputChange(event, validatePassword)} 
            />
            <Button
                onClick={handleSubmit}
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
            >
                Submit
            </Button>
            <Grid container>
                <Grid item>
                    <Link href="/login" variant="body2">
                    {"Already have an account? Sign In"}
                    </Link>
                </Grid>
            </Grid>
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
              Sign up
            </Typography>
            {currentForm}
          </Box>
        </Container>
      </ThemeProvider>
    );
  }