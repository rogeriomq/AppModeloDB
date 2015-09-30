package br.edu.unirg.appmodelodb.controllers;

import br.edu.unirg.appmodelodb.models.Pessoa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author man1gold
 */
public class ListCellViewController implements Initializable {
    @FXML
    private ImageView imageView;
    @FXML
    private Text textNome;
    @FXML
    private Text textApelido;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    public void loadPessoa(Pessoa p) {
       textNome.setText(p.getNome());
       textApelido.setText(p.getApelido());
        //textNome.textProperty().bind(p.nomeProperty());
        //textApelido.textProperty().bind(p.apelidoProperty());
    }
    
}
