import React from "react";
import { Link } from "react-router-dom";
import "../pages/Accueil.css";

const Accueil = () => {
  return (
    <div className="accueil-container">

      {/* ================= HERO FULLSCREEN ================= */}
      <section className="hero-fullscreen">
        <div className="hero-content">
          <h1>Bibliothèque Universitaire d’Orléans</h1>
          <p className="intro-text">
            Découvrez une nouvelle manière d’explorer la connaissance.
Consultez le catalogue, organisez vos emprunts et profitez d’une bibliothèque numérique qui vous suit partout — sur le campus comme à distance.
          </p>

          <div className="hero-buttons">
            <Link to="/connexion" className="btn-primary">Se connecter</Link>
            <Link to="/livres" className="btn-secondary">Catalogue</Link>
          </div>
        </div>

        <div className="hero-overlay"></div>
        <img
          className="hero-bg"
          src="https://www.stjeanio.ca/wp-content/uploads/2023/10/1667_V-1536x1028.jpg"
          alt="Bibliothèque Universitaire Orléans"
        />
      </section>

      {/* ================= SLIDER ================= */}
      <section className="photo-slider">
        <h2>La BU en images</h2>

        <div className="slider">
          <div className="slides">
            <img src="https://archicree.com/storage/2024/06/Lipsky-Rollet-1200-bibliotheque_orleans_b-08-1024x683.webp" alt="BU Orléans" />
            <img src="https://www.univ-orleans.fr/upload/public/2023-09/rez-de%20chauss%C3%A9e_bourges.jpg" alt="Rez-de-chaussée" />
            <img src="https://archicree.com/storage/2024/06/Lipsky-Rollet-1200-bibliotheque_orleans_b-01-1024x683.webp" alt="Salle lecture" />
            <img src="https://archicree.com/storage/2024/06/Lipsky-Rollet-1200-bibliotheque_orleans_b-05.webp" alt="Architecture BU" />
          </div>
        </div>
      </section>

      {/* ================= MAP ================= */}
      <section className="map-section">
        <h2>Localisation de la BU</h2>
        <p>Trouvez facilement la Bibliothèque Universitaire d’Orléans :</p>

        <div className="map-container">
          <iframe
            title="BU Orléans map"
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2635.325056629295!2d1.9366412764118714!3d47.84534687185585!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47e4fa51140ad79b%3A0x36c8f1dc1ba7e810!2sBiblioth%C3%A8que%20Universitaire%20d&#39;Orl%C3%A9ans!5e0!3m2!1sfr!2sfr!4v1707233668880!5m2!1sfr!2sfr"
            width="100%"
            height="350"
            loading="lazy"
          />
        </div>
      </section>

      {/* ================= FOOTER ================= */}
      <footer className="footer">
        © 2025 Bibliothèques Universitaires — Université d’Orléans
      </footer>

    </div>
  );
};

export default Accueil;
