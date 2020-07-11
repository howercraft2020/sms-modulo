package cl.ubicua.smsmodulo.app.models.entity;

public class SMS {

    private String numero;
    private String mensaje;


    public SMS() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
