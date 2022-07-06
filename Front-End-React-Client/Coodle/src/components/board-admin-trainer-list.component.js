import React, { Component } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";

export default class boardAdminTrainerList extends Component {
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
              {/* {
                listOfCourses.map(
                  course =>
                    <tr key={course.id}>
                      <td> {course.title} </td>
                      <td>{course.passingGrade}</td>
                      <td> {course.semester} </td>
                      <td> {course.price} </td>
                      <td> {course.isPaid} </td>
                      <td> {course.hasAttendedECourses} </td>
                      <td> {course.assignment} </td>
                      <td> {course.isPaid} </td>

                    </tr>
                )
              } */}
            </tbody>
          </table>
        </header>
      </div>
    );
  }
}
