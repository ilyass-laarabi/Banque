package metier.admins;

import metier.InteractiveConsole;
import metier.authentification.Auth;
import predentation.model.Client;
import predentation.model.Compte;
import predentation.model.TableauDeBord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServiceAdmin implements IServiceAdmin, InteractiveConsole {
    Auth  auth;



    public ServiceAdmin(Auth auth){this.auth = auth;}

    @Override
    public Client nouveauClient() {
        Compte compte = new Compte();

        System.out.println("==============================================");
        System.out.println("Creation d'un nouveau Client");
        System.out.println("Entrer un Login : ");
        String login = clavier.next();
        System.out.println("Entrer un mot de pass : ");
        String pass = clavier.next();
        System.out.println("Entrer le nom : ");
        String n= clavier.next();
        System.out.println("Entrer le prenom : ");
        String p= clavier.next();
        System.out.println("Entrer l'email : ");
        String mail = clavier.next();
        System.out.println("Entrer le CIN");
        String cin = clavier.next();
        System.out.println("Entrer le numero telephone");
        String tel= clavier.next(); System.out.println("Entrer le sexe ");
        String sexe= clavier.next();
        System.out.println("==============================================");

        Client client = new Client(login,pass,n,p,mail,cin,tel,sexe);
        auth.getBanque().getClientsDeBanque().add(client);
        client.getComptesClient().add(compte);
        compte.setProprietaire(client);

        return client;
    }

    @Override
    public Client nouveauCompteClientExistant() {
        Compte C = new Compte();

        System.out.println("Entrez Cin du Client : ");

        String Cin =clavier.next();

        chercherClientParCin(Cin).getComptesClient().add(C);

        C.setProprietaire(chercherClientParCin(Cin));

        return C.getProprietaire();
    }

    @Override
    public Client chercherClientParId(Long id) {
        Client cl = null;

        for (Client client : auth.getBanque().getClientsDeBanque()){

            if (client.getId().equals(id)){

                cl = client ;
            }
        }
        return cl;
    }

    @Override
    public List<Client> chercherClientParNom(String nom) {

        List <Client> clients = new ArrayList<>();

        for (Client client : auth.getBanque().getClientsDeBanque()){

            if (client.getNom().equals(nom)){

                clients.add(client) ;
            }
        }
        return clients;
    }

    @Override
    public List<Client> chercherClientParPrenom(String prenom) {
        List <Client> clients = new ArrayList<>();

        for (Client client : auth.getBanque().getClientsDeBanque()){

            if (client.getPrenom().equals(prenom)){

                clients.add(client) ;
            }
        }
        return clients;
    }

    @Override
    public Client chercherClientParCin(String cin) {
        Client cl = null;

        for (Client client : auth.getBanque().getClientsDeBanque()){

            if (client.getCin().equals(cin)){

                cl = client ;
            }
        }
        return cl;
    }

    @Override
    public Client chercherClientParEmail(String email) {
        Client cl = null;

        for (Client client : auth.getBanque().getClientsDeBanque()){

            if (client.getEmail().equals(email)){

                cl = client ;
            }
        }
        return cl;
    }

    @Override
    public Compte chercherCompteParId(Long idCompte) {

        for ( Client client : auth.getBanque().getClientsDeBanque()){
            for ( Compte compte : client.getComptesClient()){
                if( compte.getNumeroCompte().equals("b-co00"+idCompte)){

                    return compte ;
                }

            }
        }
        return null;
    }

    @Override
    public List<Compte> chercherCompteParSolde(double solde) {
        List<Compte> comptes = new ArrayList<>();
        for ( Client client : auth.getBanque().getClientsDeBanque()){
            for ( Compte compte : client.getComptesClient()){
                if( compte.getSolde().equals(solde)){

                    comptes.add(compte);
                }

            }

        }
        return comptes ;

    }

    @Override
    public List<Compte> chercherCompteParDateCreation(LocalDateTime date) {
        return null;
    }

    @Override
    public List<Compte> chercherCompteParProprietaire(Client proprietaire) {
        List<Compte> comptes = new ArrayList<>();
        for ( Client client : auth.getBanque().getClientsDeBanque()){
            for ( Compte compte : client.getComptesClient()){
                if( compte.getProprietaire().equals(proprietaire)){

                    comptes.add(compte);
                }

            }

        }
        return comptes ;
    }

    @Override
    public Client modifierClient(Client c) {
        System.out.println("==============================================");
        System.out.println("Que voulez-vous modifier ? ");
        System.out.println("1 - nom");
        System.out.println("2 - prenom ");
        System.out.println("3 - email ");
        System.out.println("4 - mot de passe ");
        System.out.println("5 - tel ");
        System.out.println("0 - -------EXIT---------");
        System.out.println("==============================================");
        int choix = clavier.nextInt();
        switch (choix ){

            case 1:
                System.out.println("entrer le nouveau nom ");
                String nom = clavier.next();
                c.setNom(nom);
                break ;
            case 2:
                System.out.println("entrer le nouveau prenom ");
                String prenom = clavier.next();
                c.setPrenom(prenom);
                break ;
            case 3:
                System.out.println("entrer le nouveau email ");
                String email = clavier.next();
                c.setEmail(email);
                break ;
            case 4:
                System.out.println("entrer le nouveau mot de passe ");
                String mdp = clavier.next();
                c.setMotDePasse(mdp);
                break ;
            case 5:
                System.out.println("entrer le nouveau numero de telephone ");
                String tel = clavier.next();
                c.setTel(tel);
                break ;
        }
        return null;
    }

    @Override
    public boolean supprimerClient(Long id) {

        auth.getBanque().getClientsDeBanque().remove(chercherClientParId(id));

        return false;
    }

    @Override
    public TableauDeBord calculerEtAfficherStatistiques() {
        return null;
    }

    @Override
    public List<Client> trierClientParNom() {
        Comparator<Client> clientComparator =   (o1, o2) -> {if(o1.getNom().compareTo(o2.getNom()) < 0) return -1;return 0;};

        Collections.sort(auth.getBanque().getClientsDeBanque(),clientComparator);

        return auth.getBanque().getClientsDeBanque();
    }

    @Override
    public List<Client> trierClientParCin() {
        Comparator<Client> clientComparator =   (o1, o2) -> {if(o1.getCin().compareTo(o2.getCin()) < 0) return -1;return 0;};

        Collections.sort(auth.getBanque().getClientsDeBanque(),clientComparator);

        return auth.getBanque().getClientsDeBanque();
    }

    @Override
    public List<Client> trierClientParEmail() {
        Comparator<Client> clientComparator =   (o1, o2) -> {if(o1.getEmail().compareTo(o2.getEmail()) < 0) return -1;return 0;};

        Collections.sort(auth.getBanque().getClientsDeBanque(),clientComparator);

        return auth.getBanque().getClientsDeBanque();
    }

    @Override
    public List<Client> trierClientParAdresse() {
        return null;
    }

    @Override
    public List<Client> trierClientParSoldeCompte() {
        return null;
    }

    @Override
    public List<Compte> trierComptesParSolde() {

        List<Compte> comptes = new ArrayList<>();

        for (Client client : auth.getBanque().getClientsDeBanque())
            for (Compte compte : client.getComptesClient())
            {
                comptes.add(compte);
            }

        Comparator<Compte> compteComparator = (o1, o2) -> {if(o1.getSolde() < o2.getSolde()) return 1 ; return 0;};

        Collections.sort(comptes,compteComparator);


        return comptes;
    }

    @Override
    public List<Compte> trierComptesParDateDeCreation() {
        return null;
    }

    @Override
    public List<Compte> trierComptesParNomProprietaire() {
        List<Compte> comptes = new ArrayList<>();

        for(Client client : auth.getBanque().getClientsDeBanque())
            for (Compte compte : client.getComptesClient()){
                comptes.add(compte);
            }
        Comparator<Compte> compteComparator = (o1, o2) -> {if(o1.getProprietaire().getNom().compareTo(o2.getProprietaire().getNom()) < 0) return 1;return 0;};

        Collections.sort(comptes,compteComparator);

        return comptes;
    }
}
