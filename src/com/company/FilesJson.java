package src.com.company;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesJson <E> {
    private List<E> lista;


    public void arrayToFile(String nombre){
        File f=new File(nombre);
        int i=0;
        if(f.canWrite()==false)
            f.setWritable(true);
        while (i<lista.size()){
            pasarArrayToFile(f,lista.get(i));
            i++;
        }
    }
    private void pasarArrayToFile(File f,E ob){
        ObjectMapper mapper=new ObjectMapper();
        try {
            mapper.writeValue(f,ob);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<E> getLista(String name) {
        File f = new File(name);
        List aux=new ArrayList<E>();
        if(f.canRead()==false)
            f.setReadable(true);
        ObjectMapper mapper=new ObjectMapper();
        try {
            lista=mapper.readValue(f,aux.getClass());//No estoy seguro si va a funcionar
        } catch (JsonParseException e) {             //Si lo hace golazo, sino F
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;/*sino la funcion es
                       lista=Array.asarray(mapper.readValue(f,Clase[].class))
                       */
    }
}
/*
    La idea es que con esta clase generica se pueda evitar repetir codigo de escritura y lectura de los Json

*/