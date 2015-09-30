/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.appmodelodb.controllers;

import br.edu.unirg.appmodelodb.dao.PessoaDAO;
import br.edu.unirg.appmodelodb.models.Pessoa;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author man1gold
 */
public class AgendaViewController implements Initializable {
    @FXML
    private ListView<Pessoa> listview;
    private PessoaDAO pessoaDAO;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pessoaDAO = new PessoaDAO();
        listview.getSelectionModel().selectedItemProperty().addListener(
          (ObservableValue<? extends Pessoa> observable, Pessoa oldValue, Pessoa newValue) -> {
            System.out.println("pessoaAnterior = "+oldValue + "\n" + "pessoaAtual = " + newValue);
        });
        
        listview.setCellFactory((ListView<Pessoa> param) -> {
            return new ListCell<Pessoa>() {

                @Override
                protected void updateItem(Pessoa item, boolean empty) {
                    super.updateItem(item, empty);
                    if(item!=null){
                        try {
                            //setText(item.getNome());
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/listCellView.fxml"));
                            GridPane grid = loader.load();
                            ListCellViewController controller = loader.getController();
                            controller.loadPessoa(item);
                            setGraphic(grid);
                        } catch (IOException ex) {
                            Logger.getLogger(AgendaViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        setText(null);
                    }
                }
            };
        });
        
        loadListView();
    }    

    @FXML
    private void openViewNovo(ActionEvent event) {
        
    }
    
    private void loadListView() {
        listview.getItems().clear();
        listview.setItems(pessoaDAO.getAllOrderByName());
        System.out.println("listviewSIZE = " + listview.getItems().size());
    }

    @FXML
    private void openEditView(MouseEvent event) {
        if(event.getClickCount() == 2 && listview.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editView.fxml"));
                Parent parent = loader.load();
                EditViewController editViewController = loader.getController();
                editViewController.loadPessoa(listview.getSelectionModel().getSelectedItem());
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNIFIED);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AgendaViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
