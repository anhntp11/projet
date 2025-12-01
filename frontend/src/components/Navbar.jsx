import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  const [open, setOpen] = useState(false);

  return (
    <>
      <nav className="navbar">
        <div className="navbar-logo">
          <Link to="/">BU Orléans</Link>
        </div>

        <div className="hamburger" onClick={() => setOpen(!open)}>
          <span></span>
          <span></span>
          <span></span>
        </div>
      </nav>

      {/* MENU LATERAL */}
      <div className={`side-menu ${open ? "open" : ""}`}>
        <button className="close-btn" onClick={() => setOpen(false)}>×</button>

        <ul>
          <li><Link to="/" onClick={() => setOpen(false)}>Accueil</Link></li>
          <li><Link to="/catalogue" onClick={() => setOpen(false)}>Catalogue</Link></li>
          <li><Link to="/emprunts" onClick={() => setOpen(false)}>Mes emprunts</Link></li>
          <li><Link to="/retours" onClick={() => setOpen(false)}>Retours</Link></li>
          <li><Link to="/connexion" onClick={() => setOpen(false)}>Connexion</Link></li>
        </ul>
      </div>
    </>
  );
}

export default Navbar;
