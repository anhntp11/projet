import { useState } from "react";
import "./Connexion.css";

function Connexion() {
  const [email, setEmail] = useState("");
  const [motDePasse, setMotDePasse] = useState("");
  const [role, setRole] = useState("etudiant");

  const handleSubmit = (e) => {
    e.preventDefault();

    const credentials = {
      email: email,
      motDePasse: motDePasse,
      role: role,
    };

    console.log("Tentative de connexion :", credentials);

    // 🔥 Connexion API (prêt pour le futur)
    // fetch("http://localhost:8080/api/auth/login", {
    //   method: "POST",
    //   headers: { "Content-Type": "application/json" },
    //   body: JSON.stringify(credentials),
    // });
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <h1>Connexion</h1>
        <p className="subtitle">Accédez à votre espace BU</p>

        <form onSubmit={handleSubmit}>
          <label>Adresse email</label>
          <input
            type="email"
            placeholder="exemple@univ.fr"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />

          <label>Mot de passe</label>
          <input
            type="password"
            placeholder="Votre mot de passe"
            value={motDePasse}
            onChange={(e) => setMotDePasse(e.target.value)}
            required
          />

          <label>Vous êtes :</label>
          <select value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="etudiant">Étudiant</option>
            <option value="admin">Administrateur</option>
          </select>

          <button type="submit" className="btn-login">Se connecter</button>
        </form>
      </div>
    </div>
  );
}

export default Connexion;
