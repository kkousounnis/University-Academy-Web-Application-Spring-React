import { React } from 'react';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import { Home } from './components/Home';
import { AddUser } from './components/AddUser';
import { EditUser } from './components/EditUser';

function App() {
  return (
    <div className="App">
      <Router>
        <h1>Nav</h1>
      <Routes>
        <Route path='/' element={<Home/>} />
        <Route path='/add' element={<AddUser/>} />
        <Route path='/edit/:id' element={<EditUser/>} />
      </Routes>

      </Router>
    </div>
  );
}

export default App;
