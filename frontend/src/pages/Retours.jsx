export default function Retours() {
  return (
    <div style={styles.container}>
      <h1 style={styles.title}>Déclaration de Retour</h1>
      <p style={styles.subtitle}>Déclarez vos ouvrages retournés à la bibliothèque.</p>

      <div style={styles.box}>
        <p>La déclaration de retour sera envoyée à l’administrateur (API backend).</p>
      </div>
    </div>
  );
}

const styles = {
  container: { padding: "40px" },
  title: { fontSize: "32px", fontWeight: "bold", color: "#1b2b3c" },
  subtitle: { marginTop: "10px", marginBottom: "25px", color: "#555" },
  box: {
    background: "#fff5e6",
    padding: "20px",
    borderRadius: "8px",
    border: "1px solid #ffce8a",
  }
};
