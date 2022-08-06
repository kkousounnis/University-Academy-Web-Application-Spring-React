import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/choose-access/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }

  registerTrainer(email, password, firstName, lastName, subject) {
    return axios.post(API_URL + "trainer", {
      email,
      password,
      firstName,
      lastName,
      subject
    },
      { headers: authHeader() });
  }

  updateTrainer(email, password, firstName, lastName, subject, trainerId) {
    return axios.put(API_URL + "trainer/" + trainerId, {
      email,
      password,
      firstName,
      lastName,
      subject
    },
      { headers: authHeader() });
  }

  getTrainerListBoard() {
    return axios.get(API_URL + 'trainers-list', { headers: authHeader() });
  }

  getTrainerById(trainerId) {
    return axios.get(API_URL + 'trainer/' + trainerId, { headers: authHeader() });
  }

  deleteTrainer(trainerId) {
    return axios.delete(API_URL + 'trainer/' + trainerId);
  }


}

export default new UserService();
