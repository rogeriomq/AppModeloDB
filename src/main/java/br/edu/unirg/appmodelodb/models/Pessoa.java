package br.edu.unirg.appmodelodb.models;

import java.util.Date;
import java.util.UUID;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author man1gold
 */
public class Pessoa {

    private final ObjectProperty<UUID> id = new SimpleObjectProperty<>();
    private final StringProperty nome = new SimpleStringProperty();
    private final ObjectProperty<Date> dataNasc = new SimpleObjectProperty<>();
    private final StringProperty apelido = new SimpleStringProperty();
    private final StringProperty endereco = new SimpleStringProperty();
    private final StringProperty img = new SimpleStringProperty();

    public Pessoa() {

    }

    //id
    public ObjectProperty<UUID> idProperty() {
        return id;
    }

    public UUID getid() {
        return id.get();
    }

    public void setId(UUID value) {
        id.set(value);
    }

    //nome
    public StringProperty nomeProperty() {
        return nome;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String value) {
        nome.set(value);
    }

    //dataNasc
    public ObjectProperty<Date> dataNascProperty() {
        return dataNasc;
    }
    
    public Date getDataNasc() {
        return dataNasc.get();
    }
    
    public void setDataNasc(Date value) {
        dataNasc.set(value);
    }

    //apelido
    public StringProperty apelidoProperty() {
        return apelido;
    }
    
    public String getApelido() {
        return apelido.get();
    }
    
    public void setApelido(String value) {
        apelido.set(value);
    }

    public StringProperty enderecoProperty() {
        return endereco;
    }
    
    public String getEndereco() {
        return endereco.get();
    }
    
    public void setEndereco(String value) {
        endereco.set(value);
    }

    public StringProperty imgProperty() {
        return img;
    }
    
    public String getImg() {
        return img.get();
    }
    
    public void setImg(String value) {
        img.set(value);
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id.get() 
                + ",\nnome=" + nome.get() 
                + ", \ndataNasc=" + dataNasc.get() 
                + ", \napelido=" + apelido.get() 
                + ", \nendereco=" + endereco.get() + 
                ", \nimg=" + img.get() + '}';
    }
}
