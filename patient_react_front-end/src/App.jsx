import "./../node_modules/bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import HomePage from "./Components/HomePage";
import Login from "./Components/Login";
import CreatePatient from "./Components/CreatePatient";

import Consultation from "./Components/Consultation";
import { createContext, useEffect, useState } from "react";
import DiagnosticPage from "./Components/Consultation/DiagnosticPage";
export const JwtContext = createContext();

const App = () => {
  const [jwt, setJwt] = useState(null);
  
  return (

    <JwtContext.Provider value={[jwt, setJwt]}>
      <Router>
        <Routes>
          <Route path="" element={<HomePage />} />
          <Route path="login" element={<Login />} />
          <Route path="create-patient" element={<CreatePatient />} />
          <Route path="create-consultation" element={<Consultation />} />
        <Route path="/diagnostics/:id" element={<DiagnosticPage />} />
        </Routes>
      </Router>
    </JwtContext.Provider>
  );
};

export default App;
