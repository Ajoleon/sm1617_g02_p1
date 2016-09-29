package es.ujaen.git.practica1;

/**
 * Created by Antonio on 29/09/2016.
 * Nota: Poner las variables en ingles .Para java doc pongo dos asteriscos.
 */
 /**Clase para recoger los datos*/
public class Autentication {

    public static final int SERVICE_PORT=6000;
	/**Al declararse protected no se pueden usar fuera de la clase, hay que crear un constructor para introducir datos.
	*/
    protected String mUser="user"; //inicializarlo, orotected es que solo puedo usarlo en clases que heredan
    protected String mPass ="";
    protected String mIP = "127.0.0.1";
    protected int mPort = SERVICE_PORT;
	
	/**Constructor autenticación
	*/
    public Autentication(String User, String Pass, int Port, String IP) {
        this.mUser = User;
        this.mPass = Pass;
        this.mPort = Port;
        this.mIP = IP;
    }

    public String getUser() {
        return mUser;
    }

    public void setmUser(String User) {
        this.mUser = User;
    }
}
// Autentication auth = new Autentication("pepe", "123456", "127.0.0.1", Autentication.SERVICE_PORT); forma de llamar al constructor