import React, { Component } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import { Link } from 'react-router-dom';

export default class boardAdminTrainerList extends Component {
  constructor(props) {
    super(props);

    this.addTrainer = this.addTrainer.bind(this);
    this.editTrainer = this.editTrainer.bind(this);
    this.deleteTrainer = this.deleteTrainer.bind(this);

    this.state = {
      trainers: [],
      successful: false,
      message: ""
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

  editTrainer(id) {
    this.props.history.push(`/register-update-trainer/${id}`);
  }



  deleteTrainer(id) {
    UserService.deleteTrainer(id).then(response => {
      this.setState({
        trainers: this.state.trainers.filter(trainer => trainer.id !== id),
        message: response.data.message,
        successful: true,

      }
      );


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

      });

  }

  addTrainer() {
    this.props.history.push('/register-update-trainer/_add');
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
    for (var key in valuesArray) {
      if (valuesArray.hasOwnProperty(key)) {
        trainer = valuesArray[key];
        listOfTrainers.push(trainer);
      }
    }
    return (
      <div className="container">
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

        <h2>List Of Proffessors</h2>
        <button type="button" class="m-3 btn btn-primary mybutton" onClick={this.addTrainer}>
          Add new Trainer
        </button>


        <header className="jumbotron">

          <table class="table table-light">
            <thead>
              <tr>
                <th scope="col">Email</th>
                <th scope="col">Password</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Subject</th>
                <th> Actions</th>
              </tr>
            </thead>

            <tbody>
              {!this.state.successful &&

                listOfTrainers.map(
                  trainer =>
                    <tr key={trainer.id}>
                      <td> {trainer.email} </td>
                      <td> {trainer.password} </td>
                      <td> {trainer.fistName} </td>
                      <td> {trainer.lastName} </td>
                      <td> {trainer.subject} </td>
                      <td>
                        <button type="button" class="m-1 btn btn-warning" onClick={() => this.editTrainer(trainer.id)}>Edit</button>
                        <button type="button" class="btn btn-danger" onClick={() => this.deleteTrainer(trainer.id)}>Delete</button>
                      </td>
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
