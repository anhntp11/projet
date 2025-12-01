import React, { useEffect, useState } from "react";
import "./Emprunts.css";

export default function Emprunts() {
  const [emprunts, setEmprunts] = useState([]);
  const [historique, setHistorique] = useState([]);

  useEffect(() => {
    // Exemple temporaire (à remplacer par votre API)
    setEmprunts([
      {
        id: 1,
        titre: "Introduction à Java",
        auteur: "Martin Durand",
        dateDebut: "2025-01-12",
        dateFin: "2025-02-12",
      },
    ]);

    setHistorique([
      {
        id: 10,
        titre: "Analyse des données",
        auteur: "Claire Thomas",
        dateDebut: "2024-11-01",
        dateFin: "2024-12-01",
      },
    ]);
  }, []);

  return (
    <div className="emprunts-wrapper">
      <header className="emprunts-header">
        <h1>Mes emprunts</h1>
        <p className="subtitle">
          Suivez l'ensemble de vos ouvrages empruntés et consultez votre historique.
        </p>
      </header>

      <section className="block">
        <h2 className="block-title">Emprunts en cours</h2>

        {emprunts.length === 0 ? (
          <p className="empty">Aucun emprunt en cours.</p>
        ) : (
          <div className="grid">
            {emprunts.map((item) => (
              <article key={item.id} className="card">
                <div className="card-content">
                  <h3 className="titre-livre">{item.titre}</h3>
                  <p className="info"><span>Auteur :</span> {item.auteur}</p>
                  <p className="info"><span>Date de début :</span> {item.dateDebut}</p>
                  <p className="info"><span>Date de fin prévue :</span> {item.dateFin}</p>
                </div>

                <div className="card-actions">
                  <button className="btn-primary">Prolonger</button>
                  <button className="btn-secondary">Déclarer un retour</button>
                </div>
              </article>
            ))}
          </div>
        )}
      </section>

      <section className="block">
        <h2 className="block-title">Historique des emprunts</h2>

        {historique.length === 0 ? (
          <p className="empty">L'historique est vide.</p>
        ) : (
          <div className="grid">
            {historique.map((item) => (
              <article key={item.id} className="card card-history">
                <div className="card-content">
                  <h3 className="titre-livre">{item.titre}</h3>
                  <p className="info"><span>Auteur :</span> {item.auteur}</p>
                  <p className="info"><span>Début :</span> {item.dateDebut}</p>
                  <p className="info"><span>Retour :</span> {item.dateFin}</p>
                </div>
              </article>
            ))}
          </div>
        )}
      </section>
    </div>
  );
}
