public class testsave {

    public static void main(String[] args) {
        // Arrange: Create a Utilisateur object with test data
        Utilisateur utilisateur = new Utilisateur(0, "Amina", "12345", "test@example.com", "User");

        // Act: Call the save method to save the Utilisateur
        Utilisateurdaoimpl utilisateurdao = new Utilisateurdaoimpl();
        utilisateurdao.save(utilisateur);
    }
}
