import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import CityDetail from './pages/CityDetail';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<HomePage />} />
        <Route path="/city-detail/:city" element={<CityDetail />} />
      </Routes>
    </Router>
  );
};

export default App;