package metier.admins;

import predentation.model.Client;
import predentation.model.Compte;
import predentation.model.TableauDeBord;

import java.time.LocalDateTime;
import java.util.List;

public interface IServiceAdmin {
    Client          nouveauClient();
    Client          nouveauCompteClientExistant();

    Client          chercherClientParId(Long id);
    List<Client> chercherClientParNom(String nom);
    List<Client>    chercherClientParPrenom(String prenom);
    Client          chercherClientParCin(String cin);
    Client          chercherClientParEmail(String email);

    Compte          chercherCompteParId(Long idCompte);
    List<Compte>    chercherCompteParSolde(double solde);
    List<Compte>    chercherCompteParDateCreation(LocalDateTime date);
    List<Compte>    chercherCompteParProprietaire(Client proprietaire);

    Client          modifierClient(Client c);
    boolean         supprimerClient(Long id);

    TableauDeBord calculerEtAfficherStatistiques();

    List<Client>    trierClientParNom();
    List<Client>    trierClientParCin();
    List<Client>    trierClientParEmail();
    List<Client>    trierClientParAdresse();
    List<Client>    trierClientParSoldeCompte();
    List<Compte>    trierComptesParSolde();
    List<Compte>    trierComptesParDateDeCreation();
    List<Compte>    trierComptesParNomProprietaire();
}
