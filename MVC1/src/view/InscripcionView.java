package view;

import controller.InscripcionControlador;
import model.Evento;
import model.Participante;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InscripcionView extends JFrame {

    private InscripcionControlador controlador;
    private JComboBox<Evento> comboEventos;
    private JComboBox<Participante> comboParticipantes;
    private JTextArea areaInscritos;
    private JButton btnInscribir, btnCancelar, btnListar;

    public InscripcionView(InscripcionControlador controlador,
                           List<Evento> eventos,
                           List<Participante> participantes) {
        this.controlador = controlador;

        setTitle("Gestión de Inscripciones");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel titulo = new JLabel("Gestión de Inscripciones", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Panel central con formularios y botones
        JPanel panelCentral = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        comboEventos = new JComboBox<>(eventos.toArray(new Evento[0]));
        comboParticipantes = new JComboBox<>(participantes.toArray(new Participante[0]));

        btnInscribir = new JButton("Inscribir");
        btnCancelar = new JButton("Cancelar inscripción");

        Color botonColor = new Color(200, 220, 255);
        btnInscribir.setBackground(botonColor);
        btnCancelar.setBackground(botonColor);

        panelCentral.add(new JLabel("Evento:"));
        panelCentral.add(comboEventos);
        panelCentral.add(new JLabel("Participante:"));
        panelCentral.add(comboParticipantes);
        panelCentral.add(btnInscribir);
        panelCentral.add(btnCancelar);

        add(panelCentral, BorderLayout.CENTER);

        // Panel derecho con scroll para los participantes inscritos
        areaInscritos = new JTextArea();
        areaInscritos.setEditable(false);
        areaInscritos.setLineWrap(true);
        areaInscritos.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(areaInscritos);
        scrollPane.setPreferredSize(new Dimension(250, 300)); // Ancho visible

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBorder(BorderFactory.createTitledBorder("Participantes inscritos"));
        panelDerecho.add(scrollPane, BorderLayout.CENTER);

        add(panelDerecho, BorderLayout.EAST);

        // Panel inferior con botón listar
        JPanel panelInferior = new JPanel();
        btnListar = new JButton("Listar inscritos");
        btnListar.setBackground(botonColor);
        panelInferior.add(btnListar);
        add(panelInferior, BorderLayout.SOUTH);

        // Listeners
        btnInscribir.addActionListener(e -> inscribir());
        btnCancelar.addActionListener(e -> cancelar());
        btnListar.addActionListener(e -> listar());

        setVisible(true);
    }

    private void inscribir() {
        Evento evento = (Evento) comboEventos.getSelectedItem();
        Participante participante = (Participante) comboParticipantes.getSelectedItem();

        if (evento == null || participante == null) {
            JOptionPane.showMessageDialog(this, "Seleccione evento y participante.");
            return;
        }

        boolean exito = controlador.inscribirParticipante(participante, evento);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Inscripción realizada con éxito.");
            listar();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo realizar la inscripción.");
        }
    }

    private void cancelar() {
        Evento evento = (Evento) comboEventos.getSelectedItem();
        Participante participante = (Participante) comboParticipantes.getSelectedItem();

        if (evento == null || participante == null) {
            JOptionPane.showMessageDialog(this, "Seleccione evento y participante.");
            return;
        }

        boolean exito = controlador.cancelarInscripcion(participante, evento);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Inscripción cancelada con éxito.");
            listar();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cancelar la inscripción.");
        }
    }

    private void listar() {
        Evento evento = (Evento) comboEventos.getSelectedItem();
        if (evento == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un evento.");
            return;
        }

        List<Participante> inscritos = controlador.obtenerParticipantesInscritos(evento);
        areaInscritos.setText("");
        if (inscritos.isEmpty()) {
            areaInscritos.setText("No hay participantes inscritos.");
        } else {
            for (Participante p : inscritos) {
                areaInscritos.append(p.toString() + "\n");
            }
        }
    }
}
