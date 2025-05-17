package controller;

import model.GestorParticipantes;
import model.Participante;

import java.util.ArrayList;

public class ParticipanteController {
    private GestorParticipantes gestor;

    public ParticipanteController(GestorParticipantes gestor) {
        this.gestor = gestor;
    }

    public void registrarParticipante(String nombre, String documentoIdentidad, String fechaNacimiento,
                                      String informacionContacto, String equipoClub) {
        Participante nuevo = new Participante(nombre, documentoIdentidad, fechaNacimiento, informacionContacto, equipoClub);
        gestor.agregarParticipante(nuevo);
    }

    public ArrayList<Participante> buscarPorNombre(String nombre) {
    return gestor.buscarPorNombre(nombre);
}


    public Participante buscarPorDocumento(String documento) {
        return gestor.buscarPorDocumento(documento);
    }

    public boolean actualizarParticipante(String documento, String nuevoNombre, String nuevaFechaNacimiento,
                                          String nuevoContacto, String nuevoEquipo) {
        return gestor.actualizarParticipante(documento, nuevoNombre, nuevaFechaNacimiento, nuevoContacto, nuevoEquipo);
    }

    public boolean darDeBajaParticipante(String documento) {
        return gestor.darDeBajaParticipante(documento);
    }

    public ArrayList<Participante> listarParticipantes() {
        return gestor.getParticipantes();
    }
}
