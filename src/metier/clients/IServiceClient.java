package metier.clients;

import predentation.model.Compte;

public interface IServiceClient {
    boolean versement();
    boolean retrait  ();
    boolean retrait  (int choixRetrait);
    boolean virement ();
    boolean modifierProfile(int choixModification);
    void dernieresOperations();
    double afficherSolde();
    Compte choisirCompte();

    void afficherTicket();
}
