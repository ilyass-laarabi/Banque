package dao;

import predentation.model.Utilisateur;

public interface IUtilisateurDao extends IDao<Utilisateur, Long> {

    Utilisateur findByLoginAndPass(String login, String pass);
}

