package view;

import controller.EventoController;
import model.Evento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventoView extends JFrame {
    private EventoController controller;

    public EventoView(EventoController controller) {
        this.controller = controller;
        setTitle("Gestión de Eventos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar en la pantalla
        inicializar();
        setVisible(true);
    }

    private void inicializar() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gestión de Eventos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 2, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        add(panelBotones, BorderLayout.CENTER);

        // Crear botones
        JButton btnRegistrar = new JButton("Registrar nuevo evento");
        JButton btnBuscarNombre = new JButton("Buscar por nombre");
        JButton btnBuscarTipo = new JButton("Buscar por tipo");
        JButton btnActualizar = new JButton("Actualizar evento");
        JButton btnCancelar = new JButton("Cancelar evento");
        JButton btnListar = new JButton("Listar todos");

        // Estética de botones
        List<JButton> botones = List.of(
                btnRegistrar, btnBuscarNombre, btnBuscarTipo,
                btnActualizar, btnCancelar, btnListar
        );

        for (JButton btn : botones) {
            btn.setBackground(new Color(200, 220, 255));
            panelBotones.add(btn);
        }

        // Acciones
        btnRegistrar.addActionListener(this::registrarEvento);
        btnBuscarNombre.addActionListener(this::buscarEventoPorNombre);
        btnBuscarTipo.addActionListener(this::buscarPorTipo);
        btnActualizar.addActionListener(this::actualizarEvento);
        btnCancelar.addActionListener(this::cancelarEvento);
        btnListar.addActionListener(this::listarEventos);
    }

    private void registrarEvento(ActionEvent e) {
        try {
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            if (nombre == null || nombre.isBlank()) return;

            String tipo = JOptionPane.showInputDialog(this, "Tipo:");
            if (tipo == null || tipo.isBlank()) return;

            String fechaHora = JOptionPane.showInputDialog(this, "Fecha y hora:");
            if (fechaHora == null || fechaHora.isBlank()) return;

            String lugar = JOptionPane.showInputDialog(this, "Lugar:");
            if (lugar == null || lugar.isBlank()) return;

            String maxStr = JOptionPane.showInputDialog(this, "Máximo de participantes:");
            if (maxStr == null || maxStr.isBlank()) return;
            int max = Integer.parseInt(maxStr);

            String categoriasStr = JOptionPane.showInputDialog(this, "Categorías (separadas por coma):");
            ArrayList<String> categorias = categoriasStr == null || categoriasStr.isBlank() ?
                    new ArrayList<>() :
                    new ArrayList<>(Arrays.asList(categoriasStr.split("\\s*,\\s*")));

            controller.registrarEvento(nombre, tipo, fechaHora, lugar, max, categorias);
            JOptionPane.showMessageDialog(this, "Evento registrado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número inválido para máximo de participantes.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void actualizarEvento(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del evento a actualizar:");
        if (nombre == null || nombre.isBlank()) return;

        Evento ev = controller.buscarPorNombre(nombre);
        if (ev != null) {
            try {
                String tipo = JOptionPane.showInputDialog(this, "Nuevo tipo:", ev.getTipo());
                if (tipo == null || tipo.isBlank()) return;

                String fechaHora = JOptionPane.showInputDialog(this, "Nueva fecha y hora:", ev.getFechaHora());
                if (fechaHora == null || fechaHora.isBlank()) return;

                String lugar = JOptionPane.showInputDialog(this, "Nuevo lugar:", ev.getLugar());
                if (lugar == null || lugar.isBlank()) return;

                String maxStr = JOptionPane.showInputDialog(this, "Nuevo máximo de participantes:", ev.getMaxParticipantes());
                if (maxStr == null || maxStr.isBlank()) return;
                int max = Integer.parseInt(maxStr);

                String categoriasStr = JOptionPane.showInputDialog(this, "Nuevas categorías (separadas por coma):", String.join(",", ev.getCategorias()));
                List<String> categorias = categoriasStr == null || categoriasStr.isBlank() ?
                        new ArrayList<>() :
                        Arrays.asList(categoriasStr.split("\\s*,\\s*"));

                controller.actualizarEvento(nombre, tipo, fechaHora, lugar, max, null);
                JOptionPane.showMessageDialog(this, "Evento actualizado.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número inválido para máximo de participantes.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Evento no encontrado.");
        }
    }



    private void cancelarEvento(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del evento a cancelar:");
        if (nombre == null || nombre.isBlank()) return;

        boolean cancelado = controller.cancelarEvento(nombre);
        if (cancelado) {
            JOptionPane.showMessageDialog(this, "Evento cancelado.");
        } else {
            JOptionPane.showMessageDialog(this, "Evento no encontrado.");
        }
    }

    private void listarEventos(ActionEvent e) {
        List<Evento> lista = controller.listarEventos();
        mostrarListaEventos(lista);
    }

    private void buscarEventoPorNombre(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del evento:");
        if (nombre == null || nombre.isBlank()) return;

        Evento evento = controller.buscarPorNombre(nombre);
        if (evento != null) {
            JOptionPane.showMessageDialog(this,
                    "Tipo: " + evento.getTipo() +
                            "\nFecha: " + evento.getFechaHora() +
                            "\nLugar: " + evento.getLugar() +
                            "\nMáx. participantes: " + evento.getMaxParticipantes() +
                            "\nCategorías: " + String.join(", ", evento.getCategorias()) +
                            (evento.isCancelado() ? "\n[Cancelado]" : ""));
        } else {
            JOptionPane.showMessageDialog(this, "Evento no encontrado.");
        }
    }

    private void buscarPorTipo(ActionEvent e) {
        String tipo = JOptionPane.showInputDialog(this, "Tipo de evento:");
        if (tipo == null || tipo.isBlank()) return;

        List<Evento> eventos = controller.buscarPorTipo(tipo);
        mostrarListaEventos(eventos);
    }

    private void mostrarListaEventos(List<Evento> eventos) {
        if (eventos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron eventos.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Evento ev : eventos) {
            sb.append(ev.getNombre()).append(" - ").append(ev.getFechaHora())
                    .append(ev.isCancelado() ? " [CANCELADO]" : "")
                    .append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
