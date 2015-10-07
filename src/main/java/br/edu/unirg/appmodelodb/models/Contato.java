/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.appmodelodb.models;

import java.util.UUID;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author man1gold
 */
public class Contato {
    public final static int TIPO_RESIDENCIAL = 1;
    public final static int TIPO_CELULAR = 2;
    public final static int TIPO_TRABALHO = 3;
    public final static int TIPO_OUTROS = 4;
    public final static int TIPO_EMAIL = 5;
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty tipo = new SimpleIntegerProperty();
    private final StringProperty descr = new SimpleStringProperty();
    //Id da pessoa na tabela pessoa
    private final ObjectProperty<UUID> pessoaId = new SimpleObjectProperty();
    
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    
    public void setTipo(Integer value) {
        tipo.set(value);
    }

    public Integer getTipo() {
        return tipo.get();
    }

    public IntegerProperty tipoProperty() {
        return tipo;
    }

    public void setDescr(String value) {
        descr.set(value);
    }

    public String getDescr() {
        return descr.get();
    }

    public StringProperty descrProperty() {
        return descr;
    }
    
    public UUID getPessoaId() {
        return pessoaId.get();
    }

    public void setPessoaId(UUID value) {
        pessoaId.set(value);
    }

    public ObjectProperty pessoaIdProperty() {
        return pessoaId;
    }

    @Override
    public String toString() {
        return "Contato{" + "id=" + id.get() + ",\n tipo=" + tipo.get() + ",\n descr=" + descr.get() + ",\n pessoaId=" + pessoaId.get() + '}';
    }
}
