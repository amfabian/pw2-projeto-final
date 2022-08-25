package dev.ifrs.model;

public class Mensagem {
    private Long id;
    private String texto;
    private String userId;
    public Mensagem(Long id, String texto, String userId) {
        this.id = id;
        this.texto = texto;
        this.userId = userId;
    }
    public Mensagem() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public String getUser_id() {
        return userId;
    }
    public void setUser_id(String userId) {
        this.userId = userId;
    }
    
}
