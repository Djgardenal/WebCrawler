package com.webcrawler.webcrawler;

import com.webcrawler.crawler.Analisador;
import com.webcrawler.crawler.Buscador;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {

    @FXML
    private TextField url;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("Inicio");
         baixar(url.getText(), 2);
        System.out.println("fim");

    }

    public void baixar(String link, int qtd) {

        String pagina = Buscador.baixarPaginaSocket(link);
        File file = null;
        try {
            file = Buscador.gravaArquivo(pagina);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> links = Analisador.buscaLinks(file);

        qtd -= -1;
        final int x = qtd;
        for (final String link1 : links) {
            if (qtd > 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        baixar(link1, x);
                    }
                }).start();

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
