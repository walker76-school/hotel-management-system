import * as React from 'react';
import {useState} from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { linkToHotel } from 'pages/admin/API';
import { ACCESS_TOKEN } from 'constants';

import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';
import Stack from '@mui/material/Stack';
import { useNavigate } from 'react-router-dom';
import { connect } from 'react-redux';

const theme = createTheme();

function LinkToHotel(props) {

    const navigate = useNavigate();
    let [alert, setAlert] = useState(null);
    let [managerId, setManagerId] = useState(props.currentUser !== null ? props.currentUser.id : "");
    let [hotelId, setHotelId] = useState(props.currentHotelId !== -1 ? props.currentHotelId : "");

    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const linkToHotelRequest = {
            managerId: parseInt(data.get('managerId')),
            hotelId: parseInt(data.get('hotelId'))
        }
        console.log('linkToHotelRequest', linkToHotelRequest);

        linkToHotel(linkToHotelRequest)
            .then(response => {
                navigate('/admin/reservation/view')
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
              Link Manager Account to Hotel
            </Typography>
            <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
              <TextField
                margin="normal"
                required
                fullWidth
                id="managerId"
                label="Manager ID"
                name="managerId"
                autoComplete="managerId"
                onChange={(event) => setManagerId(event.target.value)}
                value={managerId}
              />
              <TextField
                margin="normal"
                required
                fullWidth
                id="hotelId"
                label="Hotel ID"
                name="hotelId"
                autoComplete="hotelId"
                value={hotelId}
                onChange={(event) => setHotelId(event.target.value)}
                autoFocus
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Link
              </Button>
              <Grid container>
                <Grid item>
                  <Link href="/admin/hotel/create" variant="body2">
                    {"No Hotel Available? Create One"}
                  </Link>
                </Grid>
              </Grid>
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

export default connect(mapStateToProps)(LinkToHotel);
