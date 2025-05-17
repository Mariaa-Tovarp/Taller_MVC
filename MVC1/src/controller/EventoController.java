package controller;

import model.Evento;
import model.GestorEventos;

import java.util.ArrayList;

public class EventoController {
    private GestorEventos gestorEventos;

    public EventoController(GestorEventos gestorEventos) {
        this.gestorEventos = gestorEventos;
    }

    public void registrarEvento(String nombre, String tipo, String fechaHora, String lugar, int maxParticipantes, ArrayList<String> categorias) {
        Evento nuevoEvento = new Evento(nombre, tipo, fechaHora, lugar, maxParticipantes, categorias);
        gestorEventos.agregarEvento(nuevoEvento);
    }

    public Evento buscarPorNombre(String nombre) {
        return gestorEventos.buscarEventoPorNombre(nombre);
    }

    public ArrayList<Evento> buscarPorTipo(String tipo) {
        return gestorEventos.buscarEventosPorTipo(tipo);
    }

    public ArrayList<Evento> buscarPorFecha(String fecha) {
        return gestorEventos.buscarEventosPorFecha(fecha);
    }

    public boolean cancelarEvento(String nombre) {
        return gestorEventos.cancelarEvento(nombre);
    }

    public boolean actualizarEvento(String nombre, String tipo, String fechaHora, String lugar,
                                    int maxParticipantes, ArrayList<String> categorias) {
        Evento evento = gestorEventos.buscarEventoPorNombre(nombre);
        if (evento == null) {
            return false;
        }
        evento.setTipo(tipo);
        evento.setFechaHora(fechaHora);
        evento.setLugar(lugar);
        evento.setMaxParticipantes(maxParticipantes);
        evento.setCategorias(categorias);
        return true;
    }

    public boolean registrarResultado(String nombreEvento, String resultado) {
        Evento e = gestorEventos.buscarEventoPorNombre(nombreEvento);
        if (e != null && !e.isCancelado()) {
            e.agregarResultado(resultado);
            return true;
        }
        return false;
    }

    public ArrayList<Evento> listarEventos() {
        return gestorEventos.getTodosLosEventos();
    }
}
