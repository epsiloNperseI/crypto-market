import './App.css';
import CryptoNavBar from "./Components/CryptoNavBar";
import {BrowserRouter as Router} from 'react-router-dom';
import CryptoGrid from "./Components/CryptoGrid";

function App() {
  return (
    <Router>
        <CryptoNavBar />
        <CryptoGrid />
    </Router>

  );
}

export default App;
