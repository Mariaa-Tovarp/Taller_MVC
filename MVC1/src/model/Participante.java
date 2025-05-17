package model;

public class Participante {
    private String nombre;
    private String documentoIdentidad;
    private String fechaNacimiento;
    private String informacionContacto;
    private String equipoClub;
    private boolean activo;

    public Participante(String nombre, String documentoIdentidad, String fechaNacimiento,
                        String informacionContacto, String equipoClub) {
        this.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.fechaNacimiento = fechaNacimiento;
        this.informacionContacto = informacionContacto;
        this.equipoClub = equipoClub;
        this.activo = true;  // por defecto está activo
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public void setDocumentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getInformacionContacto() { return informacionContacto; }
    public void setInformacionContacto(String informacionContacto) { this.informacionContacto = informacionContacto; }

    public String getEquipoClub() { return equipoClub; }
    public void setEquipoClub(String equipoClub) { this.equipoClub = equipoClub; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return nombre + " (" + documentoIdentidad + ")";
    }
}
