package metier.authentification;



import metier.FormException;
import predentation.model.Admin;
import predentation.model.Banque;
import predentation.model.Utilisateur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginValidator {

    public LoginValidator(Banque bq) {
        this.bq = bq;
    }

    private Banque bq;
    private static final String champ_login = "login";
    private static final String champ_pass = "password";

    private String resultMsg;
    private Map<String,String> errors = new HashMap<>();


    public String getResultMsg() {
        return resultMsg;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public void setErrors(String champ , String maperror) {
        this.errors.put(champ, maperror);
    }


    private void verifierLogin(String login)throws FormException {
        if(login !=null && login.trim().length()!=0){
            if(login.length()<4){
                throw  new FormException("le champs login doit avoir plus de 4 caractere!!!");
            }
        }else {
            throw new FormException("le champs login est obligatoire!!!");
        }
    }
    /*public void validerLoginWithUser(String login , Utilisateur user) {
        try{
            verifierLogin(login);
            user.setLogin(login);
        }catch(FormException e){
            setErrors(champ_login,e.getMessage());
        }
    }*/
    public void validerLogin(String login) {
        try{
            verifierLogin(login);

        }catch(FormException e){
            //setErrors(champ_login,e.getMessage());
            System.err.println(e.getMessage());
        }
    }

    private void verifierPass(String pass)throws FormException{
        if(pass!=null && pass.trim().length()!=0){
            if(pass.length()<4){
                throw new FormException("le mot de pass doit avoir plus de 4 caractere");
            }else{

            }
        }else {
            throw new FormException("le champs password est obligatoire!!!!");

        }
    }

    public  void validerPass (String champ , Utilisateur user) {
        try {
            verifierPass(champ);
            user.setLogin(champ);

        } catch (FormException e) {
            setErrors(champ_pass,e.getMessage());
        }
    }

    public Utilisateur validerUser(String login ,String pass) {
        Utilisateur logedUser = new Utilisateur();
        //validerLogin(login,logedUser);
        validerPass(pass,logedUser);

        if(errors.isEmpty()){

        }else {
            System.out.println(errors);
        }
        //
        Admin admin = Admin.getInstance();
        if(admin.getLogin().equals(login) && admin.getMotDePasse().equals(pass)){
            logedUser = admin;
            return logedUser;
        }
        logedUser = bq.getClientsDeBanque().stream().filter(c -> c.getLogin().equals(login) && c.getMotDePasse().equals(pass)).findFirst().orElse(null);
        return logedUser;
    }


}
