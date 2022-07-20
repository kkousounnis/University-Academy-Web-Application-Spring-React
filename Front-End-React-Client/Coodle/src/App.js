import React, { Component } from "react";
import { BrowserRouter, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/auth.service";

import Login from "./components/login.component";
import ResetPassword from "./components/reset-password.component";
import ForgotPassword from "./components/forgot-password.component";
import Register from "./components/register.component";
import RegisterTrainer from "./components/register-trainer.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";
import BoardUser from "./components/board-user.component";
import BoardModerator from "./components/board-moderator.component";
import BoardAdmin from "./components/board-admin.component";

import ListTrainerComponent from "./components/board-admin-trainer-list.component";
import CreateTrainerComponent from "./components/board-admin-trainer-list.component";

import CoodleIcon from "./assets/coodle_Logo.png";

// import AuthVerify from "./common/auth-verify";
import EventBus from "./common/EventBus";
import boardAdminTrainerList from "./components/board-admin-trainer-list.component";

class App extends Component {
  constructor(props) {

    super(props);
    this.logOut = this.logOut.bind(this);
    this.state = {
      showUserBoard: false,
      showModeratorBoard: false,
      showAdminBoard: false,
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        showUserBoard: user.roles.includes("ROLE_USER"),
        showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
        showAdminBoard: user.roles.includes("ROLE_ADMIN"),
      });
      console.log(user)
    }

    EventBus.on("logout", () => {
      this.logOut();
    });
  }

  componentWillUnmount() {
    EventBus.remove("logout");
  }

  logOut() {
    AuthService.logout();
    this.setState({
      showUserBoard: false,
      showModeratorBoard: false,
      showAdminBoard: false,
      currentUser: undefined,
    });
  }

  render() {
    const { currentUser, showUserBoard, showModeratorBoard, showAdminBoard } = this.state;

    return (
      <BrowserRouter>

        <div class="navbar navbar d-flex flex-column flex-md-row align-items-center mb-1 border-bottom">
          <div class="d-flex align-items-center text-dark text-decoration-none ">
            <div className="navbar-brand container justify-content" >
              <img src={CoodleIcon} class=" float-left"/>
            </div>
          </div>
          {/* Home User Button */}
          <nav class=" d-inline-flex  ">
            {!currentUser && (<div className=" mt-3 texta-dark text-decoration-none ">
              <a className="nav-item text-dark text-decoration-none ">
                <Link to={"/home"} className="nav-link text-dark">
                  Home
                </Link>
              </a>
            </div>)}

            {/* Trainer Button */}
            <div className="mt-3 text-dark text-decoration-none">
              {showModeratorBoard && (
                <a className="nav-item text-dark text-decoration-none">
                  <Link to={"/mod"} className="nav-link text-dark">
                    Moderator Board
                  </Link>
                </a>
              )}
              {/* Administrator Button  */}
              {showAdminBoard && (
                <a className="nav-item text-dark text-decoration-none">
                  <Link to={"/admin"} className="nav-link text-dark">
                    Admin Board
                  </Link>
                </a>
              )}

              {/* Showing Users Profile token id email role */}
              {showUserBoard && (
                <a className="nav-item mt-3 text-dark text-decoration-none ">
                  <Link to={"/user"} className="nav-link text-dark">
                    User
                  </Link>
                </a>
              )}
            </div>

            {/* Administrator Trainer List Button  */}
            {showAdminBoard && (
              <div className="navbar navbar navbar-expand navbar-dark bg-white">
                <ul class="navbar-nav">
                  <a className="nav-item text-dark text-decoration-none">
                    <Link to={"/adminTrainers"} className="nav-link text-dark">
                      List Of Proffessors
                    </Link>
                  </a>
                </ul>
              </div>
            )}

            {currentUser ? (
              <div className="navbar navbar navbar-expand navbar-dark bg-white">
                <ul class="navbar-nav">
                  <li className="nav-item nav-link">
                    <Link to={"/profile"} className="text-dark nav-link">
                      {currentUser.email}
                    </Link>
                  </li>
                  <li className="nav-item nav-link">
                    <a href="/login" className="nav-link text-dark " onClick={this.logOut}>
                      LogOut
                    </a>
                  </li>
                </ul>
              </div>
            ) : (
              <div className="navbar navbar navbar-expand navbar-dark bg-white">
                <ul class="navbar-nav">
                  <li className="nav-item nav-link">
                    <Link to={"/login"} className="nav-link text-dark">
                      Login
                    </Link>
                  </li>
                  <li className="nav-item nav-link">
                    <Link to={"/register"} className="nav-link text-dark">
                      Sign Up
                    </Link>
                  </li>
                </ul>
              </div>
            )}

          </nav>
        </div>

        <div className="container ">
          <Switch>
            <Route exact path="/login" component={Login} />
            <Route exact path="/forgot-password" component={ForgotPassword} />
            <Route exact path="/reset-password" component={ResetPassword} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/profile" component={Profile} />
            {showUserBoard &&
              < Route path="/user" component={BoardUser} />}
            <Route path="/mod" component={BoardModerator} />
            <Route path="/admin" component={BoardAdmin} />
            <Route path="/adminTrainers" component={boardAdminTrainerList} />
            <Route path="/" exact component={ListTrainerComponent}></Route>
            <Route path="/trainer" component={ListTrainerComponent}></Route>
            <Route path="/register-trainer" component={RegisterTrainer}></Route>
            <Route path="/add-trainer/:id" component={CreateTrainerComponent}></Route>
            {!currentUser ?
              (<Route exact path="/" component={Home} />) :
              (<div class="container row p-5" >
                <div class="col-6">
                  <h2 class="text-primary mytext">This page you are looking is not available.</h2>
                </div>
              </div>
              )}
            {!currentUser ?
              (<Route exact path="/home" component={Home} />) :
              (<div class="container row p-5" >
                <div class="col-6">
                  <h2 class="text-primary mytext">This page you are looking is not available.</h2>
                </div>
              </div>
              )}
          </Switch>
        </div>
        <footer className="container text-center">
          <p>&copy; 2021-2022 Coodle, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
        </footer>

        { /*<AuthVerify logOut={this.logOut}/> */}

      </BrowserRouter>
    );
  }
}

export default App;
