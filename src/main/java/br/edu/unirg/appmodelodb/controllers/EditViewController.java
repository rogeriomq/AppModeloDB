/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.appmodelodb.controllers;

import br.edu.unirg.appmodelodb.dao.ContatoDAO;
import br.edu.unirg.appmodelodb.models.Contato;
import br.edu.unirg.appmodelodb.models.Pessoa;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

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
    private TableView<Contato> contatosTableView;
    @FXML
    private TableColumn<Contato, Integer> acoesTc;
    @FXML
    private TableColumn<Contato, Integer> tipoTc;
    @FXML
    private TableColumn<Contato, String> contatoTc;
    
    @FXML
    private Button btNovoContato;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoTc.setCellValueFactory(new PropertyValueFactory("tipo"));
        contatoTc.setCellValueFactory(new PropertyValueFactory("descr"));
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

    public void loadPessoa(Pessoa p) {
        nomeField.setText(p.getNome());
        apelidoFiled.setText(p.getApelido());
        if(p.getDataNasc() != null) {
            System.out.println("Data de nascimento: " + p.getDataNasc().toString());
            Instant instant = Instant.ofEpochMilli(p.getDataNasc().getTime());
            dataNascPicker.setValue( instant.atZone(ZoneId.systemDefault()).toLocalDate());
        }
        enderecoField.setText(p.getEndereco());
        ContatoDAO contatoDAO = new ContatoDAO();
        contatosTableView.getItems().clear();
        contatosTableView.setItems(contatoDAO.getAllContactsByPessoaId(p.getid()));
    }
}
