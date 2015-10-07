/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.appmodelodb.controllers;

import br.edu.unirg.appmodelodb.models.Contato;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author man1gold
 */
//Com o controlador extendendo o AnchorPane ou qualquer outro NODE raiz,
//Deve-se lembrar de também definir la no FXMl, na guia Controller, marcar a opção: Use fx:root construct.
//Essa opção diz ao FXML que o nó raiz dele eh o próprio componente raiz.
public class ContatoEditViewController implements Initializable {

    @FXML
    private ChoiceBox<Integer> choiceBoxTipo;
    @FXML
    private TextField textFieldContato;
    
    private EditViewController editViewController; //meu "ponteiro" para acessar as funcoes da tela anterior
    
    private Contato contato;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBoxTipo.getItems().addAll(Contato.TIPO_CELULAR, Contato.TIPO_TRABALHO, Contato.TIPO_RESIDENCIAL, Contato.TIPO_OUTROS, Contato.TIPO_EMAIL);
        choiceBoxTipo.setConverter(new StringConverter<Integer>() {

            @Override
            public String toString(Integer object) {
                String toString = "";
                switch (object) {
                    case Contato.TIPO_CELULAR:
                        toString = "Celular";
                        break;
                    case Contato.TIPO_TRABALHO:
                        toString = "Trabalho";
                        break;
                    case Contato.TIPO_RESIDENCIAL:
                        toString = "Residencial";
                        break;
                    case Contato.TIPO_OUTROS:
                        toString = "Outros";
                        break;
                    case Contato.TIPO_EMAIL:
                        toString = "Email";
                        break;
                }
                return toString;
            }

            @Override
            public Integer fromString(String string) {
                Integer tipo = -1;
                switch (string) {
                    case "Celular":
                        tipo = Contato.TIPO_CELULAR;
                        break;
                    case "Trabalho":
                        tipo = Contato.TIPO_TRABALHO;
                        break;
                    case "Residencial":
                        tipo = Contato.TIPO_RESIDENCIAL;
                        break;
                    case "Outros":
                        tipo = Contato.TIPO_OUTROS;
                        break;
                    case "Email":
                        tipo = Contato.TIPO_EMAIL;
                        break;
                }
                return tipo;
            }
        });
        choiceBoxTipo.setValue(Contato.TIPO_CELULAR);//sempre vem celular por defaul.
    }

    public void setEditViewController(EditViewController controller) {
        this.editViewController = controller;
    }
    
    public void carregaContato(Contato c) {
        contato = c;
        c.setTipo(c.getTipo());
        c.setDescr(c.getDescr());
    }

    @FXML
    private void confirmarContato(ActionEvent event) {
        if(contato == null) {
            contato = new Contato();
        }
        contato.setDescr(textFieldContato.getText());
        contato.setTipo(choiceBoxTipo.getValue());
        if(contato.getDescr().isEmpty())
            return; // na verdade deveria ser um aviso para informar que nao pode inserir contato sem descricao.
        
        editViewController.addContatoInTable(contato);
        ((Stage)choiceBoxTipo.getScene().getWindow()).close();
        //ou
        //choiceBoxTipo.getScene().getWindow().hide() //pelo menos cai na mesma execucao da funcao close do Stage.
    }
}