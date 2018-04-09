package com.webcrawler.webcrawler;

import com.webcrawler.crawler.Analisador;
import com.webcrawler.crawler.Buscador;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        String pagina =Buscador.baixarPaginaURL("http://www.fsg.br");
        File file = Buscador.gravaArquivo(pagina);
        Analisador.buscaLinks(file);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
