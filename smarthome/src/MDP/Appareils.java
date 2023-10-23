package MDP;

public class Appareils {
    private int id;
    private String nomAppareil;
    private String typeAppareil;
    private String étatAppareil;
    private int idUtilisateur; // This represents the foreign key relationship to the Utilisateurs table

    public Appareils(int id, String nomAppareil, String typeAppareil, String étatAppareil, int idUtilisateur) {
        this.id = id;
        this.nomAppareil = nomAppareil;
        this.typeAppareil = typeAppareil;
        this.étatAppareil = étatAppareil;
        this.idUtilisateur = idUtilisateur;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAppareil() {
        return nomAppareil;
    }

    public void setNomAppareil(String nomAppareil) {
        this.nomAppareil = nomAppareil;
    }

    public String getTypeAppareil() {
        return typeAppareil;
    }

    public void setTypeAppareil(String typeAppareil) {
        this.typeAppareil = typeAppareil;
    }

    public String getÉtatAppareil() {
        return étatAppareil;
    }

    public void setÉtatAppareil(String étatAppareil) {
        this.étatAppareil = étatAppareil;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    // Override the toString() method to represent the Appareil object as a string
    @Override
    public String toString() {
        return "Appareil ID: " + id + ", Nom Appareil: " + nomAppareil + ", Type: " + typeAppareil + ", État: " + étatAppareil + ", ID Utilisateur: " + idUtilisateur;
    }
}

