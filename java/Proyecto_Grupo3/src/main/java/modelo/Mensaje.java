package modelo;

public class Mensaje {
    private int idMensaje;
    private static int contador = 0;
    private Cliente cliente;
    private Consultor consultor;
    private String asunto;
    private String contenido;

    public Mensaje(int idMensaje, Cliente cliente, Consultor consultor, String asunto, String contenido) {
        this.idMensaje = idMensaje;
        this.cliente = cliente;
        this.consultor = consultor;
        this.asunto = asunto;
        this.contenido = contenido;
    }

    public Mensaje(Cliente cliente, Consultor consultor, String asunto, String contenido) {
        this.idMensaje = ++contador;
        this.cliente = cliente;
        this.consultor = consultor;
        this.asunto = asunto;
        this.contenido = contenido;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Mensaje.contador = contador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Consultor getConsultor() {
        return consultor;
    }

    public void setConsultor(Consultor consultor) {
        this.consultor = consultor;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
