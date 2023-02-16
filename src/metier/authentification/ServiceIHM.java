package metier.authentification;

import metier.admins.ServiceAdmin;

public class ServiceIHM implements IServiceIHM{
    protected ServiceIHM(){ }
    @Override
    public int menuGlobal() {
        return 0;
    }
}
