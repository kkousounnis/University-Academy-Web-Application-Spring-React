import React, { Component } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";

export default class BoardAdmin extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    UserService.getAdminBoard().then(
      response => {
        this.setState({
          content: response.data
        });
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
        <header className="mt-4">
          <div class="p-5 mb-4 bg-light rounded-3">
            <div class="container-fluid py-5">
              <div class="jumbotron text-center jumbo">
                <h2 class="display-3">Welcome to the {this.state.content}</h2>
                <p class="lead">"With great power comes great responsibility!"</p>
                <p>~Uncle Ben</p>
              </div>
            </div>
          </div>
        </header>
      </div>
    );
  }
}
