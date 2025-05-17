package model;

import java.util.ArrayList;

public class GestorEventos {
    private ArrayList<Evento> eventos = new ArrayList<>();

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }

    public Evento buscarEventoPorNombre(String nombre) {
        for (Evento e : eventos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Evento> buscarEventosPorTipo(String tipo) {
        ArrayList<Evento> resultado = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public ArrayList<Evento> buscarEventosPorFecha(String fecha) {
        ArrayList<Evento> resultado = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getFechaHora().contains(fecha)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public boolean cancelarEvento(String nombre) {
        Evento e = buscarEventoPorNombre(nombre);
        if (e != null) {
            e.cancelarEvento();
            return true;
        }
        return false;
    }

    public ArrayList<Evento> getTodosLosEventos() {
        return eventos;
    }
}
