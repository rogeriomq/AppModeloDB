/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.appmodelodb.controllers;

import br.edu.unirg.appmodelodb.dao.ContatoDAO;
import br.edu.unirg.appmodelodb.dao.PessoaDAO;
import br.edu.unirg.appmodelodb.models.Contato;
import br.edu.unirg.appmodelodb.models.Pessoa;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    private UUID pessoaID;
    private ContatoEditViewController contatoEditController;

    //Como estou criando uma proprierdade publica, estática e imutável(nao muda na memoria ram seu endereçamento)
    // Nao preciso fazer os métodos get e set, eles já existem por defaul.
    public static final ObjectProperty<Pessoa> pessoaProperty = new SimpleObjectProperty<>(new Pessoa());

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoTc.setCellValueFactory(new PropertyValueFactory("tipo"));
        contatoTc.setCellValueFactory(new PropertyValueFactory("descr"));
        //toda vez que mudar a pessoaProperty, vai aconceter que mudar o formulário
        pessoaProperty.addListener((ObservableValue<? extends Pessoa> observable, Pessoa oldValue, Pessoa newValue) -> {
            //sempre será lançado um nullPointerException na primeira vez que a tela é carregada
            //Por isso o código das vinculações e carregamento está dentro de um try{}catch(){}.
            try {
                System.out.println("old->" + oldValue.getid() + "\n new-> " + newValue.getid());
                nomeField.textProperty().bindBidirectional(newValue.nomeProperty());
                apelidoFiled.textProperty().bindBidirectional(newValue.apelidoProperty());
                //nao da pra fazer bind ainda com dataNasc pois o componente recebe um Localdate e a data do banco vem java.util.Date
                if (newValue.getDataNasc() != null) {
                    //System.out.println("Data de nascimento: " + newValue.getDataNasc().toString());
                    Instant instant = Instant.ofEpochMilli(newValue.getDataNasc().getTime());
                    System.out.println("dataNascPicker - SetValue");
                    dataNascPicker.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDate());
                }
                enderecoField.textProperty().bindBidirectional(newValue.enderecoProperty());
                //Nao vinculamos a lista de contatos pois deve-se selecionar no banco 
                //sempre a lista de contatos da pessoa selecioonada
                ContatoDAO contatoDAO = new ContatoDAO();
                ObservableList<Contato> contatos = contatoDAO.getAllContactsByPessoaId(newValue.getid());
                Platform.runLater(() -> {
                    contatosTableView.getItems().clear();
                    contatosTableView.setItems(contatos);
                });

            } catch (NullPointerException ex) {
            } finally {
                contatosTableView.getItems().clear();
            }

            //Como a dataDeNasc nao está com bind... temos que fazer agora com que toda vez que munde a data no formulario,
            //também mude na pessoaProperty.
            dataNascPicker.valueProperty().addListener((ObservableValue<? extends LocalDate> observable1, LocalDate oldValue1, LocalDate newValue1) -> {
                if (newValue1 == null) {
                    //pessoaProperty.get().setDataNasc(null); ou
                    pessoaProperty.get().dataNascProperty().set(null);
                    /*
                     A diferença é que como propriedade, vc pode definir/implementar o monitoramento(...changevalue....)
                     e setando a data diretamente, nao se consegue fazer isso.
                     */
                } else {
                    System.out.println("dataNascPicker chageListener");
                    Instant instant = newValue1.atStartOfDay(ZoneId.systemDefault()).toInstant();
                    pessoaProperty.get().setDataNasc(Date.from(instant));
                }
            });
        });
    }

    @FXML
    private void openViewContato(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/contatoEditView.fxml"));
            Parent root = loader.load();
            contatoEditController = loader.getController();
            contatoEditController.setEditViewController(this); //aqui eu fiz referencia a essa tela, na tela de contato.

            Stage stage = new Stage(StageStyle.UTILITY);
            stage.initOwner(contatosTableView.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelarContato(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    //Salva a pessoa e seus contatos.
    @FXML
    private void confirmarContato(ActionEvent event) {
        PessoaDAO dao = new PessoaDAO();
        //System.out.println("Pessoa = " + pessoaProperty.get()); 
        UUID pessoaId = dao.saveOrUpdate(pessoaProperty.get());
        if (pessoaId != null) {
            ContatoDAO contatoDAO = new ContatoDAO();

            ///Com for
            /*
            for (Contato c : contatosTableView.getItems()) {
                c.setDescr(c.getDescr());
                c.setPessoaId(pessoaId);
                int idRetornado = contatoDAO.saveOrUpdate(c, pessoaId);
                if (idRetornado != -1) {
                    c.setId(idRetornado);
                }
            }*/
            //Com forEach, adiconado essa funcionalidade a partir do Java8
            contatosTableView.getItems().forEach(c -> {
                c.setDescr(c.getDescr());
                c.setPessoaId(pessoaId);
                int idRetornado = contatoDAO.saveOrUpdate(c, pessoaId);
                if (idRetornado != -1) {
                    c.setId(idRetornado);
                }
            });

        }
    }

    public void loadPessoa(Pessoa p) {
        pessoaID = p.getid();
        pessoaProperty.set(p);
        System.out.println("LoadPessoa = " + pessoaProperty.get().getApelido());
    }

    public void addContatoInTable(Contato c) {
        contatosTableView.getItems().add(c);
    }
}
