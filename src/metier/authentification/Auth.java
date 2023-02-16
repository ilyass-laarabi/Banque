package metier.authentification;

import metier.FormException;
import metier.InteractiveConsole;
import metier.admins.ServiceAdmin;
import metier.admins.ServiceIHMAdmin;
import metier.clients.ServiceClient;
import metier.clients.ServiceIHMClient;
import predentation.model.*;

public class Auth implements IAuth , InteractiveConsole {

    private Banque banque;
    LoginValidator validator=new LoginValidator(banque);
    Client client;

    Compte compteClient;

    public Compte getCompteClient() {
        return compteClient;
    }

    public void setCompteClient(Compte compteClient) {
        this.compteClient = compteClient;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Auth(Banque b){
        this.banque = b;
    }


    ServiceAdmin serviceAdmin = new ServiceAdmin(this);
    ServiceIHM serviceIHM = new ServiceIHMAdmin(serviceAdmin);

    ServiceClient serviceClient = new ServiceClient(this);

    ServiceIHM serviceIHM2 = new ServiceIHMClient(serviceClient);
    @Override
    public void seConnecter(){

        Admin admin = Admin.getInstance();

        System.out.println("*****************************************************");
        System.out.println("******************FORMULAIRE DE LOGIN****************");
        System.out.println("*****************************************************");
        System.out.println("                        LOGIN");
        String log = clavier.next();
        System.out.println("                        PASSWORD");
        String mdp = clavier.next();
        Utilisateur utilisateur=new Utilisateur(log,mdp);



        validator.validerLogin(log);




        if(admin.getLogin().equals(log) && admin.getMotDePasse().equals(mdp))
        {

            serviceIHM.menuGlobal();

        }
        else if(banque.getClientsDeBanque().stream().
                filter(client -> client.getLogin().equals(log)).findFirst().orElse(null)!=null
                && banque.getClientsDeBanque().stream().
                filter(client -> client.getMotDePasse().equals(mdp)).findFirst().orElse(null)!=null) {

            this.client =banque.getClientsDeBanque().stream().filter
                    (client -> client.getLogin().equals(log)).findFirst().orElse(null);


            System.out.println("$$$$$$$$$$$$$$$$$$ Client Connecter $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("================================================");
            System.out.println("*=*=*=*=*=*=*=*=**Welcome Mr "+client.getNom()+"*=*=*=*=*=*=*=*");


            serviceIHM2.menuGlobal();

        }else {
            System.err.println("mot de pass ou login incorrect");
            seConnecter();
        }
    }

    @Override
    public void SeDeconnecter() {
        System.out.println("Voulez Vraiment déconnecter");
        System.out.println("1 ======> oui");
        System.out.println("2 ======> non");
        int choix = clavier.nextInt();
        if(choix == 1)
        {
            seConnecter();
        }else {
            serviceIHM.menuGlobal();
        }


    }
}
