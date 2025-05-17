import controller.EventoController;
import controller.ParticipanteController;
import controller.InscripcionControlador;
import model.*;
import view.EventoView;
import view.ParticipanteView;
import view.InscripcionView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class mvc1 {

    public static void main(String[] args) {
        // Crear gestores y controladores
        GestorEventos gestorEventos = new GestorEventos();
        EventoController eventoController = new EventoController(gestorEventos);

        GestorParticipantes gestorParticipantes = new GestorParticipantes();
        ParticipanteController participanteController = new ParticipanteController(gestorParticipantes);

        GestorInscripciones gestorInscripciones = new GestorInscripciones();
        InscripcionControlador inscripcionControlador = new InscripcionControlador(gestorInscripciones);

        // Datos de ejemplo
        inicializarDatos(gestorEventos, gestorParticipantes);

        SwingUtilities.invokeLater(() -> {
            JFrame ventanaPrincipal = new JFrame("Sistema de Gestión");
            ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPrincipal.setSize(350, 200);
            ventanaPrincipal.setLayout(new GridLayout(3, 1, 10, 10));

            JButton btnEventos = new JButton("Gestionar Eventos");
            JButton btnParticipantes = new JButton("Gestionar Participantes");
            JButton btnInscripciones = new JButton("Gestionar Inscripciones");

            ventanaPrincipal.add(btnEventos);
            ventanaPrincipal.add(btnParticipantes);
            ventanaPrincipal.add(btnInscripciones);

            btnEventos.addActionListener(e -> {
                ventanaPrincipal.setVisible(false);
                EventoView eventoView = new EventoView(eventoController);
                eventoView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                eventoView.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        ventanaPrincipal.setVisible(true);
                    }
                });
                eventoView.setVisible(true);
            });

            btnParticipantes.addActionListener(e -> {
                ventanaPrincipal.setVisible(false);
                ParticipanteView participanteView = new ParticipanteView(participanteController);
                participanteView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                participanteView.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        ventanaPrincipal.setVisible(true);
                    }
                });
                participanteView.setVisible(true);
            });

            btnInscripciones.addActionListener(e -> {
                ventanaPrincipal.setVisible(false);
                InscripcionView inscripcionView = new InscripcionView(
                        inscripcionControlador,
                        gestorEventos.getTodosLosEventos(),
                        gestorParticipantes.getParticipantes()
                );
                inscripcionView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                inscripcionView.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        ventanaPrincipal.setVisible(true);
                    }
                });
                inscripcionView.setVisible(true);
            });

            ventanaPrincipal.setLocationRelativeTo(null);
            ventanaPrincipal.setVisible(true);
        });
    }

    // Método para precargar datos de prueba
    private static void inicializarDatos(GestorEventos gestorEventos, GestorParticipantes gestorParticipantes) {
        ArrayList<String> categorias = new ArrayList<>();
        categorias.add("Senior");
        categorias.add("Junior");

        Evento evento1 = new Evento("Maratón 2025", "Deportivo", "2025-07-10 08:00", "Parque Central", 100, categorias);
        Evento evento2 = new Evento("Hackathon", "Tecnología", "2025-08-01 10:00", "Coworking Space", 50, categorias);

        gestorEventos.agregarEvento(evento1);
        gestorEventos.agregarEvento(evento2);

        Participante p1 = new Participante("Laura Torres", "12345678", "1990-01-01", "laura@mail.com", "Club A");
        Participante p2 = new Participante("Carlos Pérez", "87654321", "1988-05-20", "carlos@mail.com", "Club B");

        gestorParticipantes.agregarParticipante(p1);
        gestorParticipantes.agregarParticipante(p2);
    }
}
