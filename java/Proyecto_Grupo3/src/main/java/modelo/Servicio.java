package modelo;

import java.sql.Date;

public class Servicio {
    private int idServicio;
    private static int contadorId = 0;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String sede;
    private String tecnologiasImplicadas;
    private String objetivos;
    private String alcance;
    private String beneficios;
    private Date fechaInicio;
    private boolean estado;

    public Servicio(int idServicio, String nombre, String descripcion, String tecnoImpli, String objetivos,
                    String alcance, String beneficios, String categoria, String sede, Date fechaInicio, Date fechaFin, boolean estado) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.sede = sede;
        this.tecnologiasImplicadas = tecnoImpli;
        this.objetivos = objetivos;
        this.alcance = alcance;
        this.beneficios = beneficios;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }

    public Servicio(String nombre, String descripcion, String categoria, String tecnoImpli, String objetivos,
                    String alcance, String beneficios, String sede, Date fechaInicio, Date fechaFin, boolean estado) {
        this.idServicio = ++contadorId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.sede = sede;
        this.tecnologiasImplicadas = tecnoImpli;
        this.objetivos = objetivos;
        this.alcance = alcance;
        this.beneficios = beneficios;
        this.fechaInicio = fechaInicio;
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
        Servicio.contadorId = contadorId;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTecnologiasImplicadas() {
        return tecnologiasImplicadas;
    }

    public void setTecnologiasImplicadas(String tecnologiasImplicadas) {
        this.tecnologiasImplicadas = tecnologiasImplicadas;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }
}
