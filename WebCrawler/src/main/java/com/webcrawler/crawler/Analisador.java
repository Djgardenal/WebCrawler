/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcrawler.crawler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author elias
 */
public class Analisador {

    public static List<String> buscaLinks(File file) {

        try {
            List<String> links = new ArrayList();
            Document documento = Jsoup.parse(file, "UTF-8");
            List<Element> elementos = documento.getElementsByTag("a");
            for (Element elemento : elementos) {
                String link = elemento.attr("href");
                if(link.startsWith("http://") && link.endsWith(".html")){
                    links.add(link);
                }
                System.out.println(link);
            }
            return links;
        } catch (IOException ex) {
            Logger.getLogger(Analisador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
