import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "signin", {
        username,
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register( email, password, firstName, lastName) {
    return axios.post(API_URL + "signup", {
      email,
      password,
      firstName,
      lastName
    });
  }

  getCurrentUser() {
    console.log(localStorage.getItem('user'));
    return JSON.parse(localStorage.getItem('user'));;
  }
}

export default new AuthService();