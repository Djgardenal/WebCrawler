/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcrawler.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elias
 */
public class Buscador {

    public static String baixarPaginaSocket(String urlString) {
        try {
            URL url = new URL(urlString);
            String host = url.getHost();
            String path = url.getPath().isEmpty() ? "/" : url.getPath();
            Socket socket = new Socket(InetAddress.getByName(url.getHost()), 80);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("GET " + path + " HTTP/1.1");
            pw.println("Host: " + host);
            pw.println("\n\r");
            pw.flush();
            String buffer = converteInputStreamToString(socket.getInputStream());
            socket.close();
            return buffer;

        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static String baixarPaginaURL(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            String buffer = converteInputStreamToString(connection.getInputStream());

            connection.disconnect();

            return buffer;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    private static String converteInputStreamToString(InputStream inputStream) {
        try {
            if (inputStream == null) {
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String linha;
            StringBuffer buffer = new StringBuffer();
            while ((linha = reader.readLine()) != null) {
                buffer.append(linha + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            return buffer.toString();
        } catch (IOException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static File gravaArquivo(String conteudo) throws IOException {
        String linha;
        Date dt = new Date();
        String caminho = ""+dt.getYear()
                + "/" + dt.getMonth()
                + "/" + dt.getDay()
                + "/" ;
        new File(caminho ).mkdirs();
        File file = new File(caminho+"/"+dt.getTime() + ".html");
        file.createNewFile();       
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(conteudo);
        fileWriter.flush();
        fileWriter.close();
        return file;
    }
}
