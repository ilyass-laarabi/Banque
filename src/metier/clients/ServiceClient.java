package metier.clients;

import metier.InteractiveConsole;
import metier.authentification.Auth;
import predentation.model.Compte;

import java.util.Date;

public class ServiceClient implements IServiceClient, InteractiveConsole {

    Auth auth;

    Compte compte;

    public ServiceClient(Auth auth){
        this.auth = auth;
    }

    @Override
    public boolean versement() {
        System.out.println("Entrez le Montant Pour Versement : ");
        Double mnt = clavier.nextDouble();
        compte.setSolde(compte.getSolde()+mnt);
        System.out.println("Montant Verse avec Success");

        return false;
    }

    @Override
    public boolean retrait() {
        System.out.println("Entrez le Montant Retrait : ");
        Double mnt = clavier.nextDouble();
        if(compte.getSolde() >= mnt)
        {
            compte.setSolde(compte.getSolde() - mnt);
            System.out.println("Montant Retrait avec Success");
        }else
        {
            System.out.println("Votre Solde est Insuffisant !!!!!");
        }
        return false;
    }

    @Override
    public boolean retrait(int choixRetrait) {
        switch (choixRetrait)
        {
            case 1 :
                compte.setSolde(compte.getSolde() - 100);
                break;

            case 2 :
                compte.setSolde(compte.getSolde() - 200);
                break;

            case 3 :
                compte.setSolde(compte.getSolde() - 300);
                break;
            case 4 :
                compte.setSolde(compte.getSolde() - 500);
                break;

            case 5 :
                compte.setSolde(compte.getSolde() - 1000);
                break;

        }
        return false;
    }

    @Override
    public boolean virement() {
        System.out.println("Entrez le Montant que vous voulez versé : ");
        Double mnt = clavier.nextDouble();
        System.out.println("Entrez id de Compte de bénéficie : ");
        System.out.println("b-co00");
        int id = clavier.nextInt();

        if(compte.getSolde() >= mnt)
        {
            for(Compte compte1 : auth.getClient().getComptesClient())
            {
                if(compte1.getNumeroCompte().equals("b-co00"+id))
                {
                    compte1.setSolde(compte1.getSolde() + mnt);
                    compte.setSolde(compte.getSolde() - mnt);
                }
            }
        }
        return false;
    }

    @Override
    public boolean modifierProfile(int choixModification) {
        switch (choixModification)
        {
            case 1 :
                System.out.println("Entrez Le Nom modifier :");
                String nom = clavier.next();
                auth.getClient().setNom(nom);
                break;
            case 2:
                System.out.println("entrer le nouveau prenom ");
                String prenom = clavier.next();
                auth.getClient().setPrenom(prenom);
                break ;
            case 3:
                System.out.println("entrer le nouveau email ");
                String email = clavier.next();
                auth.getClient().setEmail(email);
                break ;
            case 4:
                System.out.println("entrer le nouveau mot de passe ");
                String mdp = clavier.next();
                auth.getClient().setMotDePasse(mdp);
                break ;
            case 5:
                System.out.println("entrer le nouveau numero de telephone ");
                String tel = clavier.next();
                auth.getClient().setTel(tel);
                break ;
        }

        return false;
    }

    @Override
    public void dernieresOperations() {

    }

    @Override
    public double afficherSolde() {
        return 0;
    }

    @Override
    public Compte choisirCompte() {
        System.out.println(auth.getClient().getComptesClient().toString());
        System.out.println("Tapez Id de votre compte que vous voulez : ");
        System.out.print("b-co00");
        int id = clavier.nextInt();

        for (Compte c : auth.getClient().getComptesClient())
        {
            if(c.getNumeroCompte().equals("b-co00"+id))
            {
                this.compte = c;
                return compte;
            }
        }
        return null;
    }

    @Override
    public void afficherTicket() {
        Date date = new Date();
        System.out.println("===================Votre Tiket =====================================================");
        System.out.println("Date : "+ date);
        System.out.println("============Bonjour "+auth.getClient().getNom()+" ==============");
        System.out.println("========retrait de "+ compte.getSolde() + " de compte "+compte.getNumeroCompte());
        System.out.println("======== vers le compte "+compte.getNumeroCompte()+"" );
    }
}
