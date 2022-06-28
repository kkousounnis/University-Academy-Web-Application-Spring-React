import React, { Component } from "react";

import UserService from "../services/user.service";
import coodleMenu from "./../assets/Coodle_MenuV2.png";
import { Link } from "react-router-dom";

export default class Home extends Component {
  constructor(props) {
    super(props);
    
    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    UserService.getPublicContent().then(
      response => {
        this.setState({
          content: response.data
        });
      },
      error => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });
      }
    );
  }

  render() {
    return (
      <div class="container row p-5" >
        <div class="col-6">
          <div class="row">
            <h1>
              <b>{this.state.content} </b>
            </h1>
          </div>
          <div class="row">
            <h6>Get that "ready for anything" feeling start with the masters degree
              that will change your life.</h6>
          </div>

          <div class="row">
            <div class="col mt-5">
            <Link to={"/register"} class="btn btn-primary btn-lg mt-5 mybutton">
              Join for free
                </Link>
            </div>
          </div>
        </div>
        <div class="col-sm text-center">
          <img src={coodleMenu} />
        </div>

      </div>
    );
  }
}
