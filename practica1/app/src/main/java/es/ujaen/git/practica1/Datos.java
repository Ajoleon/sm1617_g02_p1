package es.ujaen.git.practica1;

/**
 * Created by Antonio on 06/10/2016.
 */
public class Datos {
    private String usuario;
    private String clave;
    private String ip;
    private int puerto;

    public Datos(String usuario, String clave, String ip, int puerto) {
        this.usuario = usuario;
        this.clave = clave;
        this.ip = ip;
        this.puerto = puerto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
}
