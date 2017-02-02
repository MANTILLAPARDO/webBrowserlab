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
import java.util.*;
/**
 *
 * @author andres
 */
public abstract class ProxyFactory {
    static SiteContentReader instancia=null;
    public static SiteContentReader getInstance(char caracter,String url)throws IOException{
        if(caracter=='K')instancia=new ProxyKids(new WebDataExtractor(),url);
        else if (caracter=='W')instancia=new ProxyWork(new WebDataExtractor(),url);
        else{
            WebDataExtractor extractor=new WebDataExtractor();
            instancia=extractor.extract(url);
        }
        return instancia;
    } 
}
