package modelo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Consulta {
    private int idConsulta;
    private static int contador = 0;
    private String estadoActual;
    private String titulo;
    private String descripcion;
    private Date fechaCreacion;
    private ArrayList<String> estados = new ArrayList<String>(List.of("Pendiente", "En Proceso", "Finalizada"));
    
    
    
    public Consulta(String titulo, String descripcion) {
        this.idConsulta = ++contador;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = new Date();
        this.estadoActual = estados.get(0);
    }

    public Consulta(int idConsulta, String titulo, String descripcion, Date fechaCreacion) {
        this.idConsulta = idConsulta;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estadoActual = estados.get(0);
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Consulta.contador = contador;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }
}
