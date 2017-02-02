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
/*
Genera la fabrica adecuada sefgun el cliente necesario
K es para niños
W es para trabajo
en caso de no ser ninguno de estos, inicializa un navegador sin restricciones
*/
public abstract class ProxyFactory {
    static SiteContentReader instancia=null;
    /*
    Retorna la instancia del producto requerido
    */
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
