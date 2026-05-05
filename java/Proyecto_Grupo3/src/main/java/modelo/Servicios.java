package modelo;

import java.sql.Date;

public class Servicios {
    private int idServicio;
    private static int contadorId = 0;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String sede;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estado;

    public Servicios(int idServicio, String nombre, String descripcion, String categoria, String sede, Date fechaInicio, Date fechaFin, boolean estado) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.sede = sede;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public Servicios(String nombre, String descripcion, String categoria, String sede, Date fechaInicio, Date fechaFin, boolean estado) {
        this.idServicio = ++contadorId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.sede = sede;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Servicios.contadorId = contadorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
