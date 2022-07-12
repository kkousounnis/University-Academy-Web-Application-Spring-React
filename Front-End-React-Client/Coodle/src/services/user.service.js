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

  getTrainerListBoard() {
    return axios.get(API_URL + 'trainers-list', { headers: authHeader() });
  }

  getTrainerById(trainerId) {
    return axios.get(API_URL + '/' + trainerId);
  }

  updateTrainer(trainer, trainerId) {
    return axios.put(API_URL + '/' + trainerId, trainer);
  }

  deleteEmployee(trainerId) {
    return axios.delete(API_URL + '/' + trainerId);
  }


}

export default new UserService();
