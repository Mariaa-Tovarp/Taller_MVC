package model;

import java.util.ArrayList;

public class Evento {
    private String nombre;
    private String tipo;
    private String fechaHora;
    private String lugar;
    private int maxParticipantes;
    private ArrayList<String> categorias;
    private boolean cancelado;
    private ArrayList<String> resultados;

    public Evento(String nombre, String tipo, String fechaHora, String lugar, int maxParticipantes, ArrayList<String> categorias) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
        this.lugar = lugar;
        this.maxParticipantes = maxParticipantes;
        this.categorias = categorias;
        this.cancelado = false;
        this.resultados = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getFechaHora() { return fechaHora; }
    public String getLugar() { return lugar; }
    public int getMaxParticipantes() { return maxParticipantes; }
    public ArrayList<String> getCategorias() { return categorias; }
    public boolean isCancelado() { return cancelado; }
    public ArrayList<String> getResultados() { return resultados; }

    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
    public void setLugar(String lugar) { this.lugar = lugar; }
    public void setMaxParticipantes(int maxParticipantes) { this.maxParticipantes = maxParticipantes; }
    public void setCategorias(ArrayList<String> categorias) { this.categorias = categorias; }

    public void cancelarEvento() { this.cancelado = true; }

    public void agregarResultado(String resultado) {
        resultados.add(resultado);
    }

    @Override
    public String toString() {
        return nombre + " - " + fechaHora;
    }
}
