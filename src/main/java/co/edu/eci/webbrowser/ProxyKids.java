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
public class ProxyKids implements SiteContentReader {
    SiteContentReader original;
    WebDataExtractor extractor;
    
    public ProxyKids(WebDataExtractor extractor,String url)throws IOException{
        this.extractor=extractor;
        this.original=extractor.extract(url);
    }


    @Override
    public String getNextLine() {
        String linea=null;
        if(original.hasMoreLines()){
           linea=original.getNextLine();
           if(linea.contains("omba")||linea.contains("xplosivo")||linea.contains("iolencia")){
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