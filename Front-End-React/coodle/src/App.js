import { React } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Component } from 'react/cjs/react.production.min';
import { Home } from './components/Home';
class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {

    };

  }
  render() {
    return (
      <div className="App">
        <Router>
          <h1>Nav</h1>
          <Routes>
            <Route exact path={["/", "/home"]} element={<Home/>} />
          </Routes>
        </Router>
      </div>
    );
  }
}

export default App;