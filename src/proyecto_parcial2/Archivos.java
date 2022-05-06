/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_parcial2;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Archivos {
 
    public final String NomArchivo = "Datos.txt";
    List <Informacion> Datos = new ArrayList<>();
    
    public boolean Grabar(List<Informacion> Datos, int accion){
         FileWriter archivo;
        try{
            if(accion == 0){
                archivo = new FileWriter(NomArchivo, true);
            }else{
                archivo = new FileWriter(NomArchivo);
            }
            try(BufferedWriter bw = new BufferedWriter(archivo)){
                for(Informacion dato : Datos){
                    bw.write(ConversionGson(dato) + "\n");
                }
                bw.close();
            }
        }catch(Exception ex){
            return false;
        }
        return true;
    }
    
    private String ConversionGson(Informacion dato){
        Gson gson = new Gson();
        return gson.toJson(dato);
    }
    
    
    public boolean Leer(){
        String cadena = "";
        
        try{
            FileReader archivo = new FileReader(NomArchivo);
            BufferedReader br = new BufferedReader(archivo);
            
            while((cadena = br.readLine()) != null){
                Datos.add(ConvertirClase(cadena));
            }
            br.close();
            archivo.close();
        }catch(Exception ex){
            return false;
        }
        
        return true;
    }
    
    private Informacion ConvertirClase(String dato){
        Gson gson = new Gson();
        return gson.fromJson(dato, Informacion.class);
    }
    
    public void Agregar(Informacion dato){
        Datos.add(dato);
    }
    
    public List<Informacion> getDatos(){
        return Datos;
    
    }
    
    public boolean Verificar(){
        File archivo = new File(NomArchivo);
        if(!archivo.exists()){
            return false;
        }else{
            return true;
        }
    }
    
}
