package br.edu.unirg.appmodelodb;

import br.edu.unirg.appmodelodb.dao.ConexaoDB;
import java.sql.Connection;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/agendaView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("AgendaFX");
        stage.setScene(scene);
        stage.show();
        
        //Teste de conexao
//        ConexaoDB x = new ConexaoDB();
//        Connection con = x.connect();
//        System.out.println("CONECTOU");
//        con.close();
//        Platform.exit();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
