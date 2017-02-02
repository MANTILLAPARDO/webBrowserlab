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
public class ProxyWork implements SiteContentReader {
    SiteContentReader original;
    WebDataExtractor extractor;
    int[] contadores;
    ArrayList<String> contenido;
    private boolean mostrar;
    private int cont =0;
    boolean tieneMasLineas = true;
    public ProxyWork(WebDataExtractor extractor,String url)throws IOException{
        contadores=new int[3];
        contenido = new ArrayList<String>();
        this.extractor=extractor;
        this.original=extractor.extract(url);
        mostrar = this.puedeMostrar();
        if(!mostrar){
            llenarPaginaRestringida();
        }
        
        
    }


    @Override
    public String getNextLine() {
        String linea = null;
        linea = contenido.get(cont);
        cont++;
        if (cont+1 > contenido.size()){
            tieneMasLineas = false;
        }
        return linea;
    }

    @Override
    public boolean hasMoreLines() {
       return tieneMasLineas;
    }
    
    private boolean puedeMostrar(){
        String linea=null;
        while(original.hasMoreLines()){
        if(original.hasMoreLines()){
           linea=original.getNextLine();
           contenido.add(linea);
           if(linea.contains("juego")||linea.contains("Juego"))contadores[0]+=1;
           if(linea.contains("apuesta")||linea.contains("Apuesta"))contadores[1]+=1;
           if(linea.contains("pirater\\u00eda")||linea.contains("Pirater\\u00eda"))contadores[2]+=1;
        }
        }
        return !(contadores[0]>9 || contadores[1]>9 || contadores[2]>9);
    }
    
    private void llenarPaginaRestringida()throws IOException{
        String linea = null;
        original=null;
        contenido=null;
        original = extractor.extract("http://personalylaboral.com/procrastinacion/");
        contenido=new ArrayList<String>();
        boolean corte=true;
        while(original.hasMoreLines()&&corte){
            linea = original.getNextLine();
            if (linea.contains("<p><span id=\"more-16\"></span></p>")){
                corte=false;
            }
            contenido.add(linea);
        }
        contenido.add("</html>");
    }
}