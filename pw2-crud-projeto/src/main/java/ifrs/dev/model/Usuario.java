package ifrs.dev.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Usuario extends PanacheEntity {
    
    private String login;
    private String password;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Anuncio> anuncios;


    public Usuario() {
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Anuncio> getAnuncios() {
        return anuncios;
    }
    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }
    public void addAnuncios(Anuncio anuncio){
        this.anuncios.add(anuncio);
    }
    
}
