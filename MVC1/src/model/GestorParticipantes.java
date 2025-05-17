package model;

import java.util.ArrayList;

public class GestorParticipantes {
    private ArrayList<Participante> participantes = new ArrayList<>();

    public void agregarParticipante(Participante participante) {
        participantes.add(participante);
    }

    public ArrayList<Participante> buscarPorNombre(String nombre) {
    ArrayList<Participante> resultado = new ArrayList<>();
    for (Participante p : participantes) {
        if (p.getNombre() != null && p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
            resultado.add(p);
        }
    }
    return resultado;
}



    public Participante buscarPorDocumento(String documento) {
        for (Participante p : participantes) {
            if (p.getDocumentoIdentidad().equalsIgnoreCase(documento) && p.isActivo()) {
                return p;
            }
        }
        return null;
    }

    public boolean actualizarParticipante(String documento, String nuevoNombre, String nuevaFechaNacimiento,
                                          String nuevoContacto, String nuevoEquipo) {
        Participante p = buscarPorDocumento(documento);
        if (p != null) {
            p.setNombre(nuevoNombre);
            p.setFechaNacimiento(nuevaFechaNacimiento);
            p.setInformacionContacto(nuevoContacto);
            p.setEquipoClub(nuevoEquipo);
            return true;
        }
        return false;
    }

    public boolean darDeBajaParticipante(String documento) {
        Participante p = buscarPorDocumento(documento);
        if (p != null) {
            p.setActivo(false);
            return true;
        }
        return false;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }
}
