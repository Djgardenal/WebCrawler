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
 * @author Elias Appio Mezalira
 * @author Lucas Hoffman
 */
public class Buscador {

    /**
     * Baixa pagina utilizando um soket
     * 
     * @param urlString url da pagina a ser baixada
     * @return string com página baixada
     */
    
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

    /**
     * Baixa pagina utilizando um HttpUrlConnection
     * 
     * @param urlString url da pagina a ser baixada
     * @return string com página baixada
     */
    
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

    /**
     * Converte input stream com HTML de uma página para string
     * 
     * @param inputStream 
     * @return String com conteudo html da pá
     */
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

    
    /**
     * Cria diretório baseado na data e grava conteúdo do paramentro em um 
     * arquivo .html 
     * 
     * @param conteudo String com página html
     * @return File contendo arquivo .html gravado
     * @throws IOException Caso aconteça algum erro ao grabar o arquivo
     */
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
