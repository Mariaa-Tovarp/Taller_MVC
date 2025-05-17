package controller;

import java.util.List;
import model.GestorInscripciones;
import model.Participante;
import model.Evento;

public class InscripcionControlador {

    private GestorInscripciones gestorInscripciones;

    public InscripcionControlador(GestorInscripciones gestorInscripciones) {
        this.gestorInscripciones = gestorInscripciones;
    }

    public boolean inscribirParticipante(Participante participante, Evento evento) {
        return gestorInscripciones.inscribir(participante, evento);
    }

    public boolean cancelarInscripcion(Participante participante, Evento evento) {
        return gestorInscripciones.cancelarInscripcion(participante, evento);
    }

    public List<Participante> obtenerParticipantesInscritos(Evento evento) {
        return gestorInscripciones.participantesInscritos(evento);
    }
}
