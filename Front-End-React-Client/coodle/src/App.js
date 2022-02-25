import React, { Component } from "react";
import { BrowserRouter, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { withRouter } from 'react-router'

import AuthService from "./services/auth.service";

import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";
import BoardUser from "./components/board-user.component";
import BoardModerator from "./components/board-moderator.component";
import BoardAdmin from "./components/board-admin.component";

import myImage from "./assets/coodle_Logo.png";

// import AuthVerify from "./common/auth-verify";
import EventBus from "./common/EventBus";

class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
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
        showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
        showAdminBoard: user.roles.includes("ROLE_ADMIN"),
      });
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
      showModeratorBoard: false,
      showAdminBoard: false,
      currentUser: undefined,
    });
  }

  render() {
    const { currentUser, showModeratorBoard, showAdminBoard } = this.state;

    return (
      <BrowserRouter>

        <div class="navbar navbar d-flex flex-column flex-md-row align-items-center mb-1 border-bottom">
          <div class="d-flex align-items-center text-dark text-decoration-none ">
            <Link to={"/"} className="navbar-brand container justify-content" >
              <img src={myImage} class=" float-left" />
            </Link>
          </div>

          <nav class=" d-inline-flex  ">
            <div className=" mt-3 text-dark text-decoration-none ">
              <a className="nav-item text-dark text-decoration-none ">
                <Link to={"/home"} className="nav-link text-dark">
                  Home
                </Link>
              </a>
            </div>
            <div className="mt-3 text-dark text-decoration-none">
              {showModeratorBoard && (
                <a className="nav-item">
                  <Link to={"/mod"} className="nav-link text-dark">
                    Moderator Board
                  </Link>
                </a>
              )}

              {showAdminBoard && (
                <a className="nav-item">
                  <Link to={"/admin"} className="nav-link text-dark">
                    Admin Board
                  </Link>
                </a>
              )}

              {currentUser && (
                <a className="nav-item mt-3 text-dark text-decoration-none ">
                  <Link to={"/user"} className="nav-link text-dark">
                    User
                  </Link>
                </a>
              )}
            </div>
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



        <div className="container mt-3">
          <Switch>
            <Route exact path="/" component={Home} />
            <Route exact path="/home" component={Home} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/profile" component={Profile} />
            <Route path="/user" component={BoardUser} />
            <Route path="/mod" component={BoardModerator} />
            <Route path="/admin" component={BoardAdmin} />
          </Switch>
        </div>

        { /*<AuthVerify logOut={this.logOut}/> */}

      </BrowserRouter>
    );
  }
}

export default App;
