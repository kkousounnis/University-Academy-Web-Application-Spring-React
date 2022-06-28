import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import AuthService from "../services/auth.service";
import * as qs from "qs";


const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
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

export default class ResetPassword extends Component {
  constructor(props) {
    super(props);
    this.handleResetPassword = this.handleResetPassword.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);


    this.state = {
      password: "",
      loading: false,
      successful: false,
      message: ""
    };
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

  handleResetPassword(e) {
    e.preventDefault();

    //this is important it forces all the checks for the fields
    this.form.validateAll();
    const query = new URLSearchParams(this.props.location.search);
    const token = query.get('token');

    this.setState({
      message: "",
      successful: false,
      loading: true
    });

    if ((this.checkBtn.context._errors.length === 0) && (token != null)) {

      AuthService.resetPassword(
        token, this.state.password
      ).then(
        response => {
          this.setState({
            loading: false,
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
    }else {
      this.setState({
        loading: false
      });
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
            onSubmit={this.handleResetPassword}
            ref={c => {
              this.form = c;
            }}
          >
            {!this.state.message && (
              <><div className="form-group">
                <label htmlFor="password">Password</label>
                <Input
                  type="password"
                  className="form-control"
                  name="password"
                  id="password"
                  value={this.state.password}
                  onChange={this.onChangePassword}
                  validations={[required, vpassword]} />
              </div><div className="form-group">
                  <label htmlFor="password">Confirm Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="confirmPassword"
                    id="confirmpassword"
                    value={this.state.confirmPassword}
                    onChange={this.onChangeConfirmPassword}
                    validations={[required, vconfirmpassword]} />
                </div><div className="form-group mt-4">
                  <button
                    className="btn btn-primary btn-block mybutton"
                    disabled={this.state.loading}
                  >
                    {this.state.loading && (
                      <span className="spinner-border spinner-border-sm"></span>
                    )}
                    <span>Reset</span>
                  </button>
                </div></>
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