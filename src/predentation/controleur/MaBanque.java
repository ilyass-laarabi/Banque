package predentation.controleur;


import metier.authentification.Auth;
import predentation.model.Banque;
import predentation.model.Client;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class MaBanque {
    public static void main(String[] args) {


        Banque maBanque
                = new Banque(   "BP",
                "Hassan Rabat",
                "212535224433",
                "bp@banquePop.ma");


        Scanner clavier = new Scanner(System.in);
        Auth a = new Auth(maBanque);

        a.seConnecter();

clavier.close();

//     ClientDao vv=new ClientDao();
//        List<Client> clientList= vv.findAll();
    }
}
