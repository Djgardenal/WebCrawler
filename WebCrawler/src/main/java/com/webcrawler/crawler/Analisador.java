/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcrawler.crawler;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author elias
 */
public class Analisador {
    public static void buscaLinks(File file){
        
        try {
            Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            NodeList links = documento.getElementsByTagName("<a>");
            System.out.println(links.item(0).getTextContent());
                    
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Analisador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
