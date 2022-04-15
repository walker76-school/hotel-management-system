import React, { Component } from 'react';
import { signup, checkUsernameAvailability } from '../../util/APIUtils';
import './Signup.css';
import { Link } from 'react-router-dom';
import { 
    USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH,
    PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH
} from '../../constants';

import { Form, Input, Button, notification } from 'antd';
const FormItem = Form.Item;

class Signup extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: {
                value: ''
            },
            password: {
                value: ''
            },
            firstname: {
                value: ''
            },
            lastname: {
                value: ''
            },
            age: {
                value: ''
            },
            email: {
                value: ''
            },
            phonenumber: {
                value: ''
            },
            phase: 1
        };

        this.handleNext = this.handleNext.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.validateUsernameAvailability = this.validateUsernameAvailability.bind(this);
        this.isFormInvalid = this.isFormInvalid.bind(this);
    }

    handleInputChange(event, validationFun) {
        const target = event.target;
        const inputName = target.name;        
        const inputValue = target.value;

        this.setState({
            [inputName] : {
                value: inputValue,
                ...validationFun(inputValue)
            }
        });
    }

    handleSubmit() {

        const signupRequest = {
            username: this.state.username.value,
            password: this.state.password.value,
            firstName: this.state.firstname.value,
            lastName: this.state.lastname.value,
            age: this.state.age.value,
            email: this.state.email.value,
            phoneNumber: this.state.phonenumber.value,
        };
        
        signup(signupRequest)
            .then(response => {
                notification.success({
                    message: 'Hotel Management System',
                    description: "Thank you! You're successfully registered. Please Login to continue!",
                });
                this.props.history.push("/login");
            }).catch(error => {
            notification.error({
                message: 'Hotel Management System',
                description: error.message || 'Sorry! Something went wrong. Please try again!'
            });
        });
    }

    isFormInvalid() {
        return !(
            this.state.username.validateStatus === 'success' &&
            this.state.password.validateStatus === 'success' &&
            this.state.firstname.validateStatus === 'success' &&
            this.state.lastname.validateStatus === 'success' &&
            this.state.age.validateStatus === 'success' &&
            this.state.email.validateStatus === 'success' &&
            this.state.phonenumber.validateStatus === 'success'
        );
    };

    handleNext() {
        this.setState({
            phase: this.state.phase + 1
        });
    }

    render() {
        
        // First name, last name, email, age, phonenumber
        const formPhaseOne = (
            <Form className="signup-form">
                <FormItem label="First Name"
                    hasFeedback
                    validateStatus={this.state.firstname.validateStatus}
                    help={this.state.firstname.errorMsg}>
                    <Input
                        size="large"
                        name="firstname"
                        autoComplete="off"
                        placeholder="First Name"
                        value={this.state.firstname.value}
                        onChange={(event) => this.handleInputChange(event, this.validatePassthrough)} />
                </FormItem>
                <FormItem
                    label="Last Name"
                    validateStatus={this.state.lastname.validateStatus}
                    help={this.state.lastname.errorMsg}>
                    <Input
                        size="large"
                        name="lastname"
                        autoComplete="off"
                        placeholder="Last Name"
                        value={this.state.lastname.value}
                        onChange={(event) => this.handleInputChange(event, this.validatePassthrough)} />
                </FormItem>
                <FormItem
                    label="Age"
                    validateStatus={this.state.age.validateStatus}
                    help={this.state.age.errorMsg}>
                    <Input
                        size="large"
                        name="age"
                        autoComplete="off"
                        placeholder="Age"
                        value={this.state.age.value}
                        onChange={(event) => this.handleInputChange(event, this.validatePassthrough)} />
                </FormItem>
                <FormItem
                    label="Email"
                    validateStatus={this.state.email.validateStatus}
                    help={this.state.email.errorMsg}>
                    <Input
                        size="large"
                        name="email"
                        autoComplete="off"
                        placeholder="Email"
                        value={this.state.email.value}
                        onChange={(event) => this.handleInputChange(event, this.validatePassthrough)} />
                </FormItem>
                <FormItem
                    label="Phone Number"
                    validateStatus={this.state.phonenumber.validateStatus}
                    help={this.state.phonenumber.errorMsg}>
                    <Input
                        size="large"
                        name="phonenumber"
                        autoComplete="off"
                        placeholder="Phone Number"
                        value={this.state.phonenumber.value}
                        onChange={(event) => this.handleInputChange(event, this.validatePassthrough)} />
                </FormItem>
                <FormItem>
                    <Button type="primary"
                        htmlType="submit"
                        size="large"
                        className="signup-form-button"
                        disabled={this.isFormInvalid()}
                        onClick={this.handleNext}>Next
                    </Button>
                    Already registed? <Link to="/login">Login now!</Link>
                </FormItem>
            </Form>
        );

        const formPhaseTwo = (
            <Form className="signup-form">
                <FormItem label="Username"
                    hasFeedback
                    validateStatus={this.state.username.validateStatus}
                    help={this.state.username.errorMsg}>
                    <Input
                        size="large"
                        name="username"
                        autoComplete="off"
                        placeholder="A unique username"
                        value={this.state.username.value}
                        onChange={(event) => this.handleInputChange(event, this.validateUsername)} />
                </FormItem>
                <FormItem
                    label="Password"
                    validateStatus={this.state.password.validateStatus}
                    help={this.state.password.errorMsg}>
                    <Input
                        size="large"
                        name="password"
                        type="password"
                        autoComplete="off"
                        placeholder="A password between 6 to 20 characters"
                        value={this.state.password.value}
                        onChange={(event) => this.handleInputChange(event, this.validatePassword)} />
                </FormItem>
                <FormItem>
                    <Button type="primary"
                        htmlType="submit"
                        size="large"
                        className="signup-form-button"
                        disabled={this.isFormInvalid()}
                        onClick={this.handleSubmit}>Submit
                    </Button>
                    Already registed? <Link to="/login">Login now!</Link>
                </FormItem>
            </Form>
        );

        let currentForm = null;
        switch(this.state.phase) {
            case 1: 
                currentForm = formPhaseOne; 
                break;
            case 2: 
                currentForm = formPhaseTwo; 
                break;
            default:
                console.log('Trying to render phase ' + this.state.phase + ' for signup form.');
                break;
        }

        return (
            <div>
                <div className="signup-container">
                    <h1 className="page-title">Sign Up</h1>
                    <div className="signup-content">
                        {currentForm}
                    </div>
                </div>
            </div>
        );
    }

    // Validation Functions

    validateUsername = (username) => {
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
                validateStatus: null,
                errorMsg: null
            }
        }
    };

    validateUsernameAvailability() {
        // First check for client side errors in username
        const usernameValue = this.state.username.value;
        const usernameValidation = this.validateUsername(usernameValue);

        if(usernameValidation.validateStatus === 'error') {
            this.setState({
                username: {
                    value: usernameValue,
                    ...usernameValidation
                }
            });
            return;
        }

        this.setState({
            username: {
                value: usernameValue,
                validateStatus: 'validating',
                errorMsg: null
            }
        });

        checkUsernameAvailability(usernameValue)
        .then(response => {
            if(response.available) {
                this.setState({
                    username: {
                        value: usernameValue,
                        validateStatus: 'success',
                        errorMsg: null
                    }
                });
            } else {
                this.setState({
                    username: {
                        value: usernameValue,
                        validateStatus: 'error',
                        errorMsg: 'This username is already taken'
                    }
                });
            }
        }).catch(error => {
            // Marking validateStatus as success, Form will be recchecked at server
            this.setState({
                username: {
                    value: usernameValue,
                    validateStatus: 'success',
                    errorMsg: null
                }
            });
        });
    }

    validatePassword = (password) => {
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

    validatePassthrough = (field) => {
        return {
            validateStatus: 'success',
            errorMsg: null,
        };
    }

}

export default Signup;