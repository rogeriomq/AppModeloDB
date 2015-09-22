/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.appmodelodb.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author man1gold
 */
public class EditViewController implements Initializable {
    @FXML
    private ImageView avatar;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField apelidoFiled;
    @FXML
    private DatePicker dataNascPicker;
    @FXML
    private TextField enderecoField;
    @FXML
    private TableView<?> contatosTableView;
    @FXML
    private TableColumn<?, ?> acoesTc;
    @FXML
    private TableColumn<?, ?> tipoTc;
    @FXML
    private TableColumn<?, ?> contatoTc;
    @FXML
    private Button btNovoContato;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openViewContato(ActionEvent event) {
    }

    @FXML
    private void cancelarContato(ActionEvent event) {
    }

    @FXML
    private void confirmarContato(ActionEvent event) {
    }
    
}
