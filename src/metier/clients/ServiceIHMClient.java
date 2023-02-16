package metier.clients;

import metier.InteractiveConsole;
import metier.authentification.ServiceIHM;
import predentation.model.Compte;

public class ServiceIHMClient extends ServiceIHM implements IServiceIHMClient, InteractiveConsole {

    ServiceClient serviceClient;

    public ServiceIHMClient(ServiceClient serviceClient){
        this.serviceClient=serviceClient;
    }


    @Override
    public int menuGlobal() {
        boolean menu = true;

        Compte compte = serviceClient.choisirCompte();

        while (menu){
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("$=$=$=$=$=$=$=$=$Welcome to the Menu "+compte.getNumeroCompte()+"$=$==$=$=$=$=$=$=$=$=$=$=$");
            System.out.println("1 ==> faire un versement ");
            System.out.println("2 ==> faire un Retrait ");
            System.out.println("3 ==> faire un Virement ");
            System.out.println("4 ==> modifier profil ");
            System.out.println("5 ==> afficher Solde ");
            System.out.println("6 ==> choisir Compte ");
            System.out.println("0 ==> Déconnecter");
            int choix = clavier.nextInt();
            switch (choix)
            {
                case 1 :
                    serviceClient.versement();
                    break;
                case 2 :
                    menuRetrait();
                    break;

                case 3 :
                    serviceClient.virement();
                    break;

                case 4 :
                    menuModification();

                case 5 :
                    System.out.println(compte.toString());
                    break;

                case 6 :
                    compte = serviceClient.choisirCompte();
                    break;

                case 0 :
                    menu = false;
                    serviceClient.auth.SeDeconnecter();
            }
        }
        return 0;
    }

    @Override
    public int menuModification() {

        System.out.println("================Menu Modification===================");
        System.out.println("$=$=$$=$=$=$=$=$Votre profil=$=$=$=$=$=$=$==$=$=$");
        System.out.println(serviceClient.auth.getClient().toString());
        System.out.println("Que voulez-vous modifier ? ");
        System.out.println("1 - nom");
        System.out.println("2 - prenom ");
        System.out.println("3 - email ");
        System.out.println("4 - mot de passe ");
        System.out.println("5 - tel ");
        System.out.println("0 - -------EXIT---------");
        System.out.println("==============================================");
        int choix = clavier.nextInt();

        if ( choix != 0 )
        {
            serviceClient.modifierProfile(choix);
        } else if (choix == 0)
        {
            menuGlobal();
        }
        return 0;
    }

    @Override
    public int menuRetrait() {

        System.out.println("============Menu Retrait==============");
        System.out.println(
                "======================================"+
                        "1 --> 100    4 --> 500"+
                        "2 --> 200    5 --> 1000"+
                        "3 --> 300    6 --> Autre Montant"+
                        " ======================================="
        );
        int choix = clavier.nextInt();
        if(choix != 6)
        {
            System.out.println(" Voulez Vous un tikets ");
            System.out.println("1 ==> Oui");
            System.out.println("2 ==> Non");
            int choixa = clavier.nextInt();

            switch (choixa)
            {
                case 1 :
                    serviceClient.retrait(choix);
                    serviceClient.afficherTicket();
                    break;
                case 2 :
                    serviceClient.retrait(choix);
                    break;
            }
        } else if (choix == 6)
        {

            serviceClient.retrait();
            serviceClient.afficherTicket();

        }else
        {
            menuGlobal();
        }
        return 0;
    }

    @Override
    public int menuInformations() {
        return 0;
    }
}
