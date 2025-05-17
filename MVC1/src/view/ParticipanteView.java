package view;

import controller.ParticipanteController;
import model.Participante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ParticipanteView extends JFrame {
    private ParticipanteController controller;

    public ParticipanteView(ParticipanteController controller) {
        this.controller = controller;
        inicializar();
    }

    private void inicializar() {
        setTitle("Gestión de Participantes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gestión de Participantes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 2, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        JButton btnRegistrar = new JButton("Registrar nuevo participante");
        JButton btnBuscarNombre = new JButton("Buscar por nombre");
        JButton btnBuscarDoc = new JButton("Buscar por documento");
        JButton btnActualizar = new JButton("Actualizar participante");
        JButton btnDarBaja = new JButton("Dar de baja");
        JButton btnListar = new JButton("Listar todos");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnBuscarNombre);
        panelBotones.add(btnBuscarDoc);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnDarBaja);
        panelBotones.add(btnListar);

        add(panelBotones, BorderLayout.CENTER);

        btnRegistrar.addActionListener(this::registrar);
        btnBuscarNombre.addActionListener(this::buscarPorNombre);
        btnBuscarDoc.addActionListener(this::buscarPorDocumento);
        btnActualizar.addActionListener(this::actualizar);
        btnDarBaja.addActionListener(this::darDeBaja);
        btnListar.addActionListener(this::listar);

        setVisible(true);
    }

    private void registrar(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String doc = JOptionPane.showInputDialog("Documento:");
        String fecha = JOptionPane.showInputDialog("Fecha de nacimiento:");
        String contacto = JOptionPane.showInputDialog("Contacto:");
        String equipo = JOptionPane.showInputDialog("Equipo/Club (si aplica):");
        controller.registrarParticipante(nombre, doc, fecha, contacto, equipo);
        JOptionPane.showMessageDialog(this, "Participante registrado.");
    }

    private void buscarPorNombre(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del participante:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Búsqueda cancelada o sin nombre válido.");
            return;
        }

        ArrayList<Participante> encontrados = controller.buscarPorNombre(nombre.trim());
        if (encontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron participantes con ese nombre.");
        } else {
            StringBuilder sb = new StringBuilder("Participantes encontrados:\n\n");
            for (Participante p : encontrados) {
                sb.append("Nombre: ").append(p.getNombre())
                  .append("\nDocumento: ").append(p.getDocumentoIdentidad())
                  .append("\n\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }

    private void buscarPorDocumento(ActionEvent e) {
        String doc = JOptionPane.showInputDialog("Documento:");
        Participante p = controller.buscarPorDocumento(doc);
        if (p != null) {
            JOptionPane.showMessageDialog(this,
                "Nombre: " + p.getNombre() + "\nNacimiento: " + p.getFechaNacimiento() +
                "\nContacto: " + p.getInformacionContacto() + "\nEquipo: " + p.getEquipoClub() +
                (p.isActivo() ? "" : "\n[INACTIVO]")
            );
        } else {
            JOptionPane.showMessageDialog(this, "Participante no encontrado.");
        }
    }

    private void actualizar(ActionEvent e) {
        String doc = JOptionPane.showInputDialog("Documento del participante:");
        Participante p = controller.buscarPorDocumento(doc);
        if (p != null) {
            String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", p.getNombre());
            String nuevaFecha = JOptionPane.showInputDialog("Nueva fecha nacimiento:", p.getFechaNacimiento());
            String nuevoContacto = JOptionPane.showInputDialog("Nuevo contacto:", p.getInformacionContacto());
            String nuevoEquipo = JOptionPane.showInputDialog("Nuevo equipo:", p.getEquipoClub());
            controller.actualizarParticipante(doc, nuevoNombre, nuevaFecha, nuevoContacto, nuevoEquipo);
            JOptionPane.showMessageDialog(this, "Participante actualizado.");
        } else {
            JOptionPane.showMessageDialog(this, "Participante no encontrado.");
        }
    }

    private void darDeBaja(ActionEvent e) {
        String doc = JOptionPane.showInputDialog("Documento:");
        boolean exito = controller.darDeBajaParticipante(doc);
        JOptionPane.showMessageDialog(this, exito ? "Participante dado de baja." : "No encontrado.");
    }

    private void listar(ActionEvent e) {
        ArrayList<Participante> lista = controller.listarParticipantes();
        StringBuilder sb = new StringBuilder();
        for (Participante p : lista) {
            sb.append(p.getNombre()).append(" - ").append(p.getDocumentoIdentidad());
            if (!p.isActivo()) sb.append(" [INACTIVO]");
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
