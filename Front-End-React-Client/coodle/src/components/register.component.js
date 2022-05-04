import React, { Component, useState } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";
import AuthService from "../services/auth.service";

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const vemail = value => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const vuserfirstName = value => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The username must be between 3 and 20 characters.
      </div>
    );
  }
};

const vuserlastName = value => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The username must be between 3 and 20 characters.
      </div>
    );
  }
};

//check if there is at least one uppercase.
const uppercaseRegExp = /(?=.*?[A-Z])/;

//check if there is at least one lowercase.
const lowercaseRegExp = /(?=.*?[a-z])/;

//check if there is at least one digit.
const digitsRegExp = /(?=.*?[0-9])/;

//check if there is at least one Special Characters.
const specialCharRegExp = /(?=.*?[#?!@$%^&*-])/;

//check if there is at least 8 characters.
const minLengthRegExp = /.{8,}/;

// using this variable in order to check with confirmation password field that they are the same
var globalPassword;

const vpassword = value => {
  globalPassword = value;
  if (value.length < 8 || value.length > 40) {//check if value is more than 8 characters and less than 40.
    return (
      <div className="alert alert-danger" role="alert">
        The password must be between 6 and 40 characters.
      </div>
    );
  } else if (!uppercaseRegExp.test(value)) {//check if there is at least one uppercase.
    return (
      <div className="alert alert-danger" role="alert">
        Password must have at least one Uppercase.
      </div>
    );
  } else if (!lowercaseRegExp.test(value)) {//check if there is at least one lowercase.
    return (
      <div className="alert alert-danger" role="alert">
        Password must have at least one Lowercase.
      </div>
    );
  } else if (!digitsRegExp.test(value)) {//check if there is at least one digit.
    return (
      <div className="alert alert-danger" role="alert">
        Password must have at least one digit.
      </div>
    );
  } else if (!specialCharRegExp.test(value)) {//check if there is at least one Special Characters.
    return (
      <div className="alert alert-danger" role="alert">
        Password must have at least one Special Characters.
      </div>
    );
  } else if (!minLengthRegExp.test(value)) {//check if there is at least 8 characters.
    return (
      <div className="alert alert-danger" role="alert">
        Password must have at least minumum 8 characters.
      </div>
    );
  }
};

const vconfirmpassword = value => {
  if (globalPassword !== value) {
    return (
      <div className="alert alert-danger" role="alert">
        Passwords does not match.
      </div>
    );
  }
};

const thisIsMyCopy = "<p>copy copy copy <strong>strong copy</strong></p>";

export default class Register extends Component {

  constructor(props) {
    super(props);
    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.onChangeConfirmPassword = this.onChangeConfirmPassword.bind(this);
    this.onChangefirstName = this.onChangefirstName.bind(this);
    this.onChangelastName = this.onChangelastName.bind(this);

    this.state = {
      email: "",
      password: "",
      confirmPassword: "",
      firstName: "",
      lastName: "",
      successful: false,
      message: ""
    };
  }

  onChangeEmail(e) {
    this.setState({
      email: e.target.value
    });
  }

  onChangePassword(e) {
    this.setState({
      password: e.target.value
    });
  }

  onChangeConfirmPassword(e) {
    this.setState({
      confirmPassword: e.target.value
    });
  }

  onChangefirstName(e) {
    this.setState({
      firstName: e.target.value
    });
  }

  onChangelastName(e) {
    this.setState({
      lastName: e.target.value
    });
  }

  handleRegister(e) {

    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      AuthService.register(
        this.state.email,
        this.state.password,
        this.state.firstName,
        this.state.lastName
      ).then(
        response => {
          this.setState({
            message: response.data.message,
            successful: true
          });
        },
        error => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          this.setState({
            successful: false,
            message: resMessage
          });
        }
      );
    }
  }

  render() {
    return (
      <div className="col-md-12">
        <div className="card card-container">
          <img
            src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
            alt="profile-img"
            className="profile-img-card"
          />

          <Form
            onSubmit={this.handleRegister}
            ref={c => {
              this.form = c;
            }}
          >
            {!this.state.successful && (
              <div>
                <div className="form-group">
                  <label htmlFor="Email">Email</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="email"
                    value={this.state.email}
                    onChange={this.onChangeEmail}
                    validations={[required, vemail]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="password">Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="password"
                    id="password"
                    value={this.state.password}
                    onChange={this.onChangePassword}
                    validations={[required, vpassword]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="password">Confirm Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="confirmPassword"
                    id="confirmpassword"
                    value={this.state.confirmPassword}
                    onChange={this.onChangeConfirmPassword}
                    validations={[required, vconfirmpassword]}
                  />

                </div>

                <div className="form-group">
                  <label htmlFor="firstName">First Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="firstName"
                    value={this.state.firstName}
                    onChange={this.onChangefirstName}
                    validations={[required, vuserfirstName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="lastName">Last Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="lastName"
                    value={this.state.lastName}
                    onChange={this.onChangelastName}
                    validations={[required, vuserlastName]}
                  />
                </div>

                <div className="form-group mt-4">
                  <button className="btn btn-primary btn-block mybutton">Sign Up</button>
                </div>
              </div>
            )}

            {this.state.message && (
              <div className="form-group">
                <div
                  className={
                    this.state.successful
                      ? "alert alert-success"
                      : "alert alert-danger"
                  }
                  role="alert"
                >
                  {this.state.message}
                </div>
              </div>
            )}
            <CheckButton
              style={{ display: "none" }}
              ref={c => {
                this.checkBtn = c;
              }}
            />
          </Form>
        </div>
      </div>
    );
  }
}
