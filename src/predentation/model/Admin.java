package predentation.model;

public class Admin extends Utilisateur{

    private static  Admin ADMIN = new Admin();

    private Admin(){

        login       = "ilyass";
        motDePasse  = "laarabi";
        role        = "Admin";
        nom         =  "ILYASS";
        prenom      = "LAARABI";

    }


    public static Admin getInstance(){
        return ADMIN;
    }
}
