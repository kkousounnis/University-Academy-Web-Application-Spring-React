import React, { Component } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";

export default class BoardUser extends Component {

  constructor(props) {
    super(props);
    this.state = { clients: [] };
    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    UserService.getUserBoard().then(
      response => {
        this.setState({
          content: response.data
        });
        console.log("Foo "+this.state.content);
      },
      error => {
        this.setState({
          content:
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        });

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );
  }

  render() {

    return (
      <div className="container">
        <header className="jumbotron">
          <h3>{this.state.content}</h3>
          <table class="table table-light">
            <thead>
              <tr>
                <th scope="col">Lessons</th>
                <th scope="col">Passing Grade</th>
                <th scope="col">Semester</th>
                <th scope="col">Price</th>
                <th scope="col">Status Of Payment</th>
                <th scope="col">Attended All Online Lessons</th>
                <th scope="col">Assignment Status</th>
                <th scope="col">Assignment Summary</th>
                <th scope="col">Assignment Deadline date</th>
                <th scope="col">To be implemented Pay the lesson</th>
              </tr>
            </thead>
            <tbody>

            </tbody>
          </table>
        </header>
      </div>
    );
  }
}
