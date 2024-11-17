package SistemaDeMenus;

import Cuentas.*;  

public class UsuarioObserver implements Observer {
    private String nombreUsuario;

    public UsuarioObserver(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public void actualizar(boolean asientoDisponible) {
        if (asientoDisponible) {
            System.out.println("Notificación para " + nombreUsuario + ": Un asiento se ha desocupado.");
        } else {
            System.out.println("Notificación para " + nombreUsuario + ": Un nuevo vuelo ha sido agregado.");
        }
    }
}
