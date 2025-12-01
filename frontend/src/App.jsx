import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";
import Accueil from "./pages/Accueil";
import Catalogue from "./pages/Catalogue";
import Emprunts from "./pages/Emprunts";
import Retours from "./pages/Retours";
import Connexion from "./pages/Connexion";

function App() {
  return (
    <BrowserRouter>
      <Navbar />

      <Routes>
        <Route path="/" element={<Accueil />} />
        <Route path="/catalogue" element={<Catalogue />} />
        <Route path="/emprunts" element={<Emprunts />} />
        <Route path="/retours" element={<Retours />} />
        <Route path="/connexion" element={<Connexion />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
