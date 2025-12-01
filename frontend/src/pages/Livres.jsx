import { useEffect, useState } from "react";
import "./Livres.css";

export default function Livres() {
  const [livres, setLivres] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("http://localhost:8080/api/catalogue")
      .then((res) => res.json())
      .then((data) => {
        setLivres(data.livres);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Erreur catalogue :", err);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Chargement...</p>;

  return (
    <div className="catalogue-container">
      <h1>Catalogue des ouvrages</h1>

      <div className="livres-grid">
        {livres.map((livre) => (
          <div key={livre.id} className="livre-card">
            <h3>{livre.titre}</h3>

            <p>
              Auteur : <strong>{livre.auteur}</strong>
            </p>

            <p>
              Genre : <strong>{livre.genre}</strong>
            </p>

            <button className="btn-emprunt">
              Demander un emprunt
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
