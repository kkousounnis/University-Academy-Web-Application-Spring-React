import React, { Component } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";

export default class boardAdminTrainerList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      trainers: []
    };
  }

  componentDidMount() {
    UserService.getTrainerListBoard().then(
      response => {
        this.setState({
          trainersContent: response.data
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
    let trainer = {
      id: "",
      "email": "",
      "password": "",
      "fistName": "",
      "lastName": ""
    }

    const listOfTrainers = [];
    var valuesArray = this.state.trainersContent;
    console.log("List OF Trainers:" + JSON.stringify(this.state.trainersContent));
    console.log("List OF Trainers:" + valuesArray);
    for (var key in valuesArray) {
      if (valuesArray.hasOwnProperty(key)) {
        console.log(valuesArray[key]);
        trainer = valuesArray[key];
        listOfTrainers.push(trainer);
      }
    }
    return (
      <div className="container">
        <header className="jumbotron">

          <table class="table table-light">
            <thead>
              <tr>
                <th scope="col">Email</th>
                <th scope="col">Password</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
              </tr>
            </thead>
            <tbody>
              {
                listOfTrainers.map(
                  trainer =>
                    <tr key={trainer.id}>
                      <td> {trainer.email} </td>
                      <td> {trainer.password} </td>
                      <td> {trainer.fistName} </td>
                      <td> {trainer.lastName} </td>
                    </tr>
                )
              }
            </tbody>
          </table>
        </header>
      </div>
    );
  }
}
