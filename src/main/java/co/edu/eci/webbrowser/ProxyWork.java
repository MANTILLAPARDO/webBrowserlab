/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eci.webbrowser;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.scene.web.WebEngine;
import org.darkweb.utils.*;
import org.darkweb.uicomponents.WebEngineSingleton;
/**
 *
 * @author andres
 */
public class ProxyWork implements SiteContentReader {
    SiteContentReader original;
    WebDataExtractor extractor;
    int[] contadores;
    public ProxyWork(WebDataExtractor extractor,String url)throws IOException{
        this.extractor=extractor;
        this.original=extractor.extract(url);
        contadores=new int[3];
    }


    @Override
    public String getNextLine() {
        String linea=null;
        if(original.hasMoreLines()){
           linea=original.getNextLine();
           if(linea.contains("juego")||linea.contains("Juego"))contadores[0]+=1;
           if(linea.contains("apuesta")||linea.contains("Apuesta"))contadores[1]+=1;
           if(linea.contains("pirater\\u00eda")||linea.contains("Pirater\\u00eda"))contadores[2]+=1;
           if(contadores[0]>9||contadores[1]>9||contadores[2]>9){
               extractor.extract()
               linea=linea.replaceAll("bomba", "*****");
               linea=linea.replaceAll("explosivo", "*********");
               linea=linea.replaceAll("Bomba", "*****");
               linea=linea.replaceAll("Explosivo", "*********");
               linea=linea.replaceAll("violencia", "*********");
               linea=linea.replaceAll("Violencia", "*********");
           }
        }
           
        
        return linea;
    }

    @Override
    public boolean hasMoreLines() {
       return original.hasMoreLines();
    }
}