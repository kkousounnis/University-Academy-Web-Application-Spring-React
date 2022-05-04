import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import { isEmail } from "validator";
import CheckButton from "react-validation/build/button";

import AuthService from "../services/auth.service";
import { Button } from "bootstrap";

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

export default class Login extends Component {
  constructor(props) {
    super(props);
    this.handleForgotPassword = this.handleForgotPassword.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);

    this.state = {
      email: "",
      loading: false,
      message: ""
    };
  }

  onChangeEmail(e) {
    this.setState({
      email: e.target.value
    });
  }

  handleForgotPassword(e) {
    e.preventDefault();

    //this is important it forces all the checks for the fields
    this.form.validateAll();

    this.setState({
      loading: true,
      message: ""
    });
    if (this.checkBtn.context._errors.length === 0) {
      AuthService.forgotPassword(
        this.state.email
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
      )
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
            onSubmit={this.handleForgotPassword}
            ref={c => {
              this.form = c;
            }}
          >
            {!this.state.message && (
              <><div className="form-group">
                <label htmlFor="username">Email</label>
                <Input
                  type="text"
                  className="form-control mt-2"
                  placeHolder="Type you email to send the link"
                  name="username"
                  value={this.state.email}
                  onChange={this.onChangeEmail}
                  validations={[required, vemail]} />
              </div><div className="form-group mt-4">
                  <button
                    className="btn btn-primary btn-block mybutton"
                    disabled={this.state.loading}
                  >
                    {this.state.loading && (
                      <span className="spinner-border spinner-border-sm"></span>
                    )}
                    <span>Send</span>
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