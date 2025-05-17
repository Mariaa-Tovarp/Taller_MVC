package model;

import java.util.ArrayList;
import java.util.List;

public class GestorInscripciones {

    private List<Inscripcion> inscripciones;

    public GestorInscripciones() {
        inscripciones = new ArrayList<>();
    }

    // Clase interna para representar la inscripción
    private static class Inscripcion {
        private Participante participante;
        private Evento evento;

        public Inscripcion(Participante participante, Evento evento) {
            this.participante = participante;
            this.evento = evento;
        }

        public Participante getParticipante() {
            return participante;
        }

        public Evento getEvento() {
            return evento;
        }
    }

    // Inscribir participante a un evento
    public boolean inscribir(Participante participante, Evento evento) {
        // Verificar cupo
        int inscritos = participantesInscritos(evento).size();
        if (inscritos >= evento.getMaxParticipantes()) {
            return false; // No hay cupo
        }
        // Verificar si ya está inscrito
        for (Inscripcion ins : inscripciones) {
            if (ins.getEvento().equals(evento) && ins.getParticipante().equals(participante)) {
                return false; // Ya inscrito
            }
        }
        // Agregar inscripción
        inscripciones.add(new Inscripcion(participante, evento));
        return true;
    }

    // Cancelar inscripción
    public boolean cancelarInscripcion(Participante participante, Evento evento) {
        for (Inscripcion ins : inscripciones) {
            if (ins.getEvento().equals(evento) && ins.getParticipante().equals(participante)) {
                inscripciones.remove(ins);
                return true;
            }
        }
        return false; // No encontrada la inscripción
    }

    // Obtener participantes inscritos en un evento
    public List<Participante> participantesInscritos(Evento evento) {
        List<Participante> lista = new ArrayList<>();
        for (Inscripcion ins : inscripciones) {
            if (ins.getEvento().equals(evento)) {
                lista.add(ins.getParticipante());
            }
        }
        return lista;
    }
}
