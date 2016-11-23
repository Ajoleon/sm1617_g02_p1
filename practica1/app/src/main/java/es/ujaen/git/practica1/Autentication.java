package es.ujaen.git.practica1;

 /**Clase para recoger los datos
  *
  */
public class Autentication {

    public static final int SERVICE_PORT=6000;
	//Al declararse protected no se pueden usar fuera de la clase, hay que crear un constructor para introducir datos
     //Inicializarlo, protected es que solo puedo usarlo en clases que heredan
    protected String mUser="user";
    protected String mPass ="";
    protected String mIP = "127.0.0.1";
    protected int mPort = SERVICE_PORT;
	
	/**Constructor autenticación
	*/
    public Autentication(String User, String Pass, String IP, int Port) {
        mUser = User;
        mPass = Pass;
        mPort = Port;
        mIP = IP;
    }

     //Para conseguir el usuario de la clase
     public String getmUser() {
         return mUser;
     }
     //Para establecer el usuario de la clase
     public void setmUser(String User) {
         mUser = User;
     }

     public String getmPass() {
         return mPass;
     }

     public void setmPass(String Pass) {
         mPass = Pass;
     }

     public String getmIP() {
         return mIP;
     }

     public void setmIP(String IP) {
         mIP = IP;
     }

     public int getmPort() {
         return mPort;
     }

     public void setmPort(int Port) {
         mPort = Port;
     }
 }
// Autentication auth = new Autentication("pepe", "123456", "127.0.0.1", Autentication.SERVICE_PORT); //forma de llamar al constructor