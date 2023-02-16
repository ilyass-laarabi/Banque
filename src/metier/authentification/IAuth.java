package metier.authentification;

import metier.FormException;

public interface IAuth {
     void seConnecter() throws FormException;
    void SeDeconnecter();
}
