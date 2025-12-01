import React, { useEffect, useState } from "react";
import "./Catalogue.css";

function Catalogue() {
  const [livres, setLivres] = useState([]);
  const [search, setSearch] = useState("");
  const [genre, setGenre] = useState("");

  // 🔥 Chargement automatique du catalogue au démarrage
  useEffect(() => {
    fetch("http://localhost:8080/api/catalogue")
      .then((res) => res.json())
      .then((data) => {
        setLivres(data.livres || []);
      })
      .catch((err) => {
        console.error("Erreur chargement catalogue :", err);
      });
  }, []);

  // 🔍 Recherche / Filtre
  const rechercher = () => {
    const query = new URLSearchParams();

    if (search.trim() !== "") query.append("titre", search);
    if (genre.trim() !== "") query.append("genre", genre);

    fetch("http://localhost:8080/api/catalogue/filtre?" + query.toString())
      .then((res) => res.json())
      .then((data) => setLivres(data))
      .catch((err) => console.error("Erreur recherche :", err));
  };

  return (
    <div className="catalogue-wrapper">
      <h1 className="catalogue-title">Catalogue des ouvrages</h1>

      {/* 🔎 Zone de recherche */}
      <div className="filters">
        <input
          type="text"
          placeholder="Rechercher un titre..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />

        <select value={genre} onChange={(e) => setGenre(e.target.value)}>
          <option value="">Tous les genres</option>
          <option value="Informatique">Informatique</option>
          <option value="Mathématiques">Mathématiques</option>
          <option value="Sociologie">Sociologie</option>
          <option value="Littérature">Littérature</option>
        </select>

        <button onClick={rechercher}>Rechercher</button>
      </div>

      {/* 🟦 Grille des livres */}
      <div className="grid">
        {livres.length === 0 ? (
          <p className="empty">Aucun livre trouvé.</p>
        ) : (
          livres.map((livre) => (
            <div key={livre.id} className="card">
              <h3 className="titre">{livre.titre}</h3>
              <p><span>Auteur :</span> {livre.auteur}</p>
              <p><span>Genre :</span> {livre.genre}</p>
              <p><span>Stock :</span> {livre.stock}</p>

              <button className="btn-emprunter">
                Demander un emprunt
              </button>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default Catalogue;
