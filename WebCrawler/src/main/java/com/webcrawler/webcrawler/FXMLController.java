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
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    @FXML
    private TextField url;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        String pagina =Buscador.baixarPaginaSocket(url.getText());
        File file = Buscador.gravaArquivo(pagina);
        Analisador.buscaLinks(file);
        System.out.println("fim");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
