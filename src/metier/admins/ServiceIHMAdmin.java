package metier.admins;

import metier.InteractiveConsole;
import metier.authentification.ServiceIHM;
import predentation.model.Admin;
import predentation.model.Client;

public class ServiceIHMAdmin extends ServiceIHM implements IServiceIHMAdmin , InteractiveConsole {

    ServiceAdmin serviceAdmin;


    public ServiceIHMAdmin(ServiceAdmin serviceAdmin) {
        this.serviceAdmin = serviceAdmin;
    }

    @Override
    public int menuModification() {
        System.out.println("entrer la Cin du client que vous voulez modifier : ");
        String cin = clavier.next();
        Client c = serviceAdmin.chercherClientParCin(cin);
        System.out.println(serviceAdmin.chercherClientParCin(cin).toString());
        serviceAdmin.modifierClient(c);
        System.out.println(serviceAdmin.chercherClientParCin(cin).toString());
        System.out.println("0 ---- EXIT -----");
        int choix = clavier.nextInt();
        if(choix == 0 )
            menuGlobal();
        return 0;
    }

    @Override
    public int menuRecherche() {
        System.out.println("==============================================");
        System.out.println("1 ====> Rechercher Client");
        System.out.println("2 ====> Rechercher Compte");
        System.out.println("0 ====> Exit");
        System.out.println("==============================================");
        int choix = clavier.nextInt();
        switch (choix)
        {
            case 1 :
                System.out.println("==============================================");
                System.out.println("=*=*=*=*=*=*=*=*=*Cherche Un Client *=*=*=*=*=*=*=*=*=*");
                System.out.println("1 ===> TROUVER LE CLIENT PAR SON ID");
                System.out.println("2 ===> TROUVER LE CLIENT PAR SON NOM");
                System.out.println("3 ===> TROUVER LE CLIENT PAR LA PRENOM");
                System.out.println("4 ===> TROUVER LE CLIENT PAR SON E-MAIL");
                System.out.println("5 ===> TROUVER LE CLIENT PAR LA CIN");
                System.out.println("0 ========>    Exit            :      ");
                System.out.println("==============================================");
                int choixChe = clavier.nextInt();
                switch (choixChe)
                {
                    case 1 :
                        System.out.println("Entrez l id de Client cherche : ");
                        Long clientid = clavier.nextLong();
                        System.out.println(serviceAdmin.chercherClientParId(clientid).toString());
                        break;

                    case 2 :
                        System.out.println("Entrez le Nom de Client Cherher : ");
                        String ClientNom = clavier.next();
                        System.out.println(serviceAdmin.chercherClientParNom(ClientNom).toString());
                        break;
                    case 3 :
                        System.out.println("Entrez le Prenom de Client Cherher : ");
                        String prenom = clavier.next();
                        System.out.println(serviceAdmin.chercherClientParPrenom(prenom).toString());
                        break;
                    case 4 :
                        System.out.println("Entrez le email de Client Cherher : ");
                        String clientEmail = clavier.next();
                        System.out.println(serviceAdmin.chercherClientParEmail(clientEmail).toString());
                        break;
                    case 5 :
                        System.out.println("Entrez le Cin de Client Cherher : ");
                        String Cin = clavier.next();
                        System.out.println(serviceAdmin.chercherClientParCin(Cin).toString());
                        break;
                    case 0 :
                        menuRecherche();
                        break;
                }
                break;

            case 2 :
                System.out.println("==============================================");
                System.out.println(" 1 -- TROUVER LE COMPTE PAR SON ID     : ");
                System.out.println(" 2 -- TROUVER LE CLIENT PAR SON SOLDE    : ");
                System.out.println(" 3 -- TROUVER LE COMPTE PAR LA DATE DE CREATION  : ");
                System.out.println(" 4 -- TROUVER LE COMPTE PAR SON PROPRIETERE    : ");
                System.out.println(" 0 --               Exit");
                System.out.println("==============================================");
                int choixa = clavier.nextInt();
                switch (choixa)
                {
                    case 1 :
                        System.out.println("Tapez id de compte chercher : ");
                        Long idCompte =  clavier.nextLong();
                        System.out.println(serviceAdmin.chercherCompteParId(idCompte));
                        break;

                    case 2 :
                        System.out.println("Tapez le solde chercher : ");
                        Double soldeCompte = clavier.nextDouble();
                        System.out.println(serviceAdmin.chercherCompteParSolde(soldeCompte));
                        break;
                    case 4 :
                        System.out.println("entre le Cin de le PROPRIETERE");
                        String cin = clavier.next();
                        Client client =serviceAdmin.chercherClientParCin(cin);
                        System.out.println(serviceAdmin.chercherCompteParProprietaire(client));
                        break;
                    case 0 :
                        menuRecherche();
                        break;
                }

                break;

            case 3 :
                menuGlobal();
                break;
        }
        return choix;
    }

    @Override
    public int menuInformations() {
        return 0;
    }

    @Override
    public int menuAjout() {
        System.out.println("==============================================");
        System.out.println("*=*=*=*=*=*=*=Add=*=*=*=*=**=*==*");
        System.out.println("1 ====> Ajouter Client ");
        System.out.println("2 ====> Ajouter Compte ");
        System.out.println("3 ====> Exite ");
        System.out.println("==============================================");
        int choix = clavier.nextInt();
        switch (choix){

            case 1 :
                serviceAdmin.nouveauClient();
                break;
            case 2 :
                serviceAdmin.nouveauCompteClientExistant();
                break;
            case 0 :
                menuGlobal();
                break;
        }
        return 0;
    }

    @Override
    public int menuSuppression() {
        System.out.println("==============================================");
        System.out.println("=*=*==*=*=*Delete Menu=*=*=*=*=*=*");
        System.out.println("3 ====>  Supprimer Client");
        System.out.println("2 ====>  Supprimer Compte");
        System.out.println("==============================================");
        int choix = clavier.nextInt();
        switch (choix)
        {
            case 1 :
                System.out.println("Tapez id de Client que vous voulez Supprimer : ");
                Long clientId = clavier.nextLong();
                serviceAdmin.chercherClientParId(clientId);
                System.out.println("Etes-vous sur de vouloir le supprimer ?");
                System.out.println("-1 OUI ");
                System.out.println("-2 NON ");


                int boolCh = clavier.nextInt();
                switch (boolCh){

                    case 1 : serviceAdmin.supprimerClient(clientId);
                        break ;
                    case 2 : menuGlobal();
                        break ;
                }

        }
        return 0 ;
    }

    @Override
    public int tableauDeBord() {

        return 0;
    }

    @Override
    public int menuTrie() {
        System.out.println("==============================================");
        System.out.println("*=*=*=*=*=*=*=*=*=*Menu Trie*=*=*=*=*=*=*=*=*=*=*=*");
        System.out.println("1 ==> Trier Les Clients ");
        System.out.println("2 ==> Trier les Comptes");
        System.out.println("0 ==================Exit===============");
        System.out.println("==============================================");
        int choix = clavier.nextInt();

        switch (choix)
        {
            case 1 :
                System.out.println("==============================================");
                System.out.println("=*=*=*=*=*=*=*Menu Trie Client=*=*=*=*=*=**=*=");
                System.out.println("1 ===> Trier les Client par Nom");
                System.out.println("2 ===> Trier les Client par Cin");
                System.out.println("3 ===> Trier les Client par Email");
                System.out.println("4 ===> Trier les Client par Solde de Compte");
                System.out.println("5 ===> Trier les Client par Adresse");
                System.out.println("0 ==================Exit===============");
                System.out.println("==============================================");
                int choixClient = clavier.nextInt();
                switch (choixClient)
                {
                    case 1 :
                        System.out.println(serviceAdmin.trierClientParNom().toString());
                        break;

                    case 2 :
                        System.out.println(serviceAdmin.trierClientParCin().toString());
                        break;
                    case 3 :
                        System.out.println(serviceAdmin.trierClientParEmail().toString());
                        break;
                    case 4 :
                        System.out.println(serviceAdmin.trierClientParSoldeCompte().toString());
                        break;
                    case 5 :
                        System.out.println(serviceAdmin.trierClientParAdresse().toString());
                        break;
                    case 0 :
                        menuTrie();
                        break;
                }
            case 2 :
                System.out.println("==============================================");
                System.out.println("=*=*=*=*=*=*=*Menu Trie Compte=*=*=*=*=*=**=*=");
                System.out.println("1 ===> trier Comptes Par Solde");
                System.out.println("2 ===> trier Comptes Par Date De Creation");
                System.out.println("3 ===> trier Comptes Par Nom Propriétaire");
                System.out.println("0 ==================Exit===============");
                System.out.println("==============================================");
                int choixCompte = clavier.nextInt();
                switch (choixCompte)
                {
                    case 1 :
                        System.out.println(serviceAdmin.trierComptesParSolde().toString());
                        break;
                    case 2 :
                        System.out.println(serviceAdmin.trierComptesParDateDeCreation().toString());
                        break;
                    case 3 :
                        System.out.println(serviceAdmin.trierComptesParNomProprietaire().toString());
                        break;
                    case 0 :
                        menuTrie();
                }
            case 0 :
                menuGlobal();
                break;
        }
        return 0;
    }

    @Override
    public int menuComptabilite() {
        return 0;
    }

    @Override
    public int menuGlobal() {
        boolean menu = true;

        while (menu){

            Admin admin = Admin.getInstance();
            System.out.println("<=================Welcome Mr "+admin.getNom()+"==================>");
            System.out.println("$*$*$*$*$*$*$*$*$*$*$Bank Menu$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$");
            System.out.println("$*$*$*$*$*$*$*$*$*$*$Admin$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$");
            System.out.println(" 1  =======>    AJOUTER     <=============== ");
            System.out.println(" 2  =======>    RECHERCHER  <===============  ");
            System.out.println(" 3  =======>    MODIFIER    <=============== ");
            System.out.println(" 4  =======>    SUPPRIMER   <=============== ");
            System.out.println(" 5 ========>    Trier    <=============== ");
            System.out.println(" 0  =======>    Déconnecter <=============== ");
            int choix = clavier.nextInt();

            switch (choix){

                case 1 :
                    menuAjout();
                    break;

                case 2 :
                    menuRecherche();
                    break;

                case 3 :
                    menuModification();
                    break;

                case 4 :
                    menuSuppression();
                    break;

                case 5 :
                    menuTrie();

                case 0 :
                    menu=false;
                    serviceAdmin.auth.SeDeconnecter();
                    break;
            }
        }
        return 0;
    }
}
