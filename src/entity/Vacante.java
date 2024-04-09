package entity;

public class Vacante {
    private int id;
    private Empresa id_empresa;
    private String titulo;
    private String descripcion;
    private String duracion;
    private String estado;
    private String tecnologia;


    public Vacante() {
    }
    public Vacante(int id, Empresa id_empresa, String titulo, String descripcion, String duracion, String estado) {
        this.id = id;
        this.id_empresa = id_empresa;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empresa getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Empresa id_empresa) {
        this.id_empresa = id_empresa;
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    @Override
    public String toString() {
        return "Vacante{" +
                "id=" + id +
                ", id_empresa=" + id_empresa.getId() +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion='" + duracion + '\'' +
                ", estado='" + estado + '\'' +
                ", tecnologia='" + tecnologia + '\'' +
                ", nombre empresa=" + id_empresa.getNombre() +
                '}';
    }
}
