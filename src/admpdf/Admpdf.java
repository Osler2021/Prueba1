package admpdf;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Admpdf {
            
    public static void main(String[] args) {
        PDDocument pdDocument = null;                                           //creo documento en memoria
        try {
            pdDocument = PDDocument.load(new File("C:/gmonti/Humber2.pdf"));     // Leo el archivo en ruta disco
            PDFTextStripper pdfTexto = new PDFTextStripper();                   // Creo el objeto texto 
            pdfTexto.setStartPage(1);                                           //Anular si el archivo original viene dividido por salto QR
            pdfTexto.setEndPage(1);                                             //Anular si el archivo original viene dividido por salto QR
            String contenido = pdfTexto.getText(pdDocument);                    //Extraigo el contenido
            System.out.println(contenido);
            String texto = Normalizer.normalize(contenido, Normalizer.Form.NFD);//Para eliminar acentos o codigos raros
            String textodep = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            String contenidoMin = textodep.toLowerCase();                      //Convierto todo a minuscula para buscar producto mas facil
            System.out.println("------------------------------");
            if (contenido.indexOf("101") != -1) {                               //Veo si tiene ctg, msje general
                System.out.println("Tiene ctg");
            } else if (contenido.indexOf("102") != -1) {  
                System.out.println("Tiene ctg");
            } else {    
                System.out.println("El pdf no tiene ctg");
            }   
            System.out.println("------------------------------");
            if (contenidoMin.indexOf("trigo") != -1) {                          //Busco producto, msje general
            } else if (contenidoMin.indexOf("soja") != -1) {
            } else if (contenidoMin.indexOf("maiz") != -1) {
            } else if (contenidoMin.indexOf("mani") != -1) {
            } else {
                System.out.println("El pdf no tiene trigo, ni maiz, ni mani, ni soja");
            }
        
            String[] palabras = contenidoMin.split("\\s+");                     //Creo una lista con cada palabra de factura por cada espacio en blanco 
            //System.out.println("Cantidad palabras: "+palabras.length);        //Cuento palabras
            ArrayList ctgs = new ArrayList<String>();                           //Creo una nueva lista con los ctg
                for(int i = 0; i<palabras.length; i++){                         //Busco todos los ctg
                    if(palabras[i].indexOf("101") != -1) {                      //Busco ctg 101
                    String ctg1 = palabras[i].substring(palabras[i].indexOf("101"),palabras[i].indexOf("101") + 11);
                        boolean esNroctg1;
                        if (esNroctg1 = ctg1.chars().allMatch(Character::isDigit)) { //Compruebo que sea numerico los 11 digitos
                            ctgs.add(ctg1);
                            System.out.println("CTG: " + ctg1);
                        }
                    }
                    else if(palabras[i].indexOf("102") != -1) {                 //Busco ctg 102
                    String ctg2 = palabras[i].substring(palabras[i].indexOf("102"),palabras[i].indexOf("102") + 11);
                        boolean esNroctg2;
                        if (esNroctg2 = ctg2.chars().allMatch(Character::isDigit)) { 
                            ctgs.add(ctg2);
                            System.out.println("CTG: " + ctg2);
                        }   
                    }
                    if (palabras[i].indexOf("trigo") != -1) {                   //Busco producto
                        System.out.println("Producto: trigo");
                        System.out.println("------------------------------");
                    } else if (palabras[i].indexOf("soja") != -1) {
                        System.out.println("Producto: soja");
                        System.out.println("------------------------------");
                    } else if (palabras[i].indexOf("maiz") != -1) {
                        System.out.println("Producto: maiz");
                        System.out.println("------------------------------");
                    } else if (palabras[i].indexOf("mani") != -1) {
                        System.out.println("Producto: mani");
                        System.out.println("------------------------------");
                    }  
                }   
                
                
            /**    
            if (contenido.indexOf("101") != -1) {                               //Busco CTG 101 o 102
                String ctg1 = contenido.substring(contenido.indexOf("101"), (contenido.indexOf("101") + 11));
                boolean esNroctg1;
                if (esNroctg1 = ctg1.chars().allMatch(Character::isDigit)) {    // Compruebo que sea numero
                    System.out.println("Resultado CTG N°: " + ctg1);
                }
            } else if (contenido.indexOf("102") != -1) {
                String ctg2 = contenido.substring(contenido.indexOf("102"), (contenido.indexOf("102") + 11));
                boolean esNroctg2;
                if (esNroctg2 = ctg2.chars().allMatch(Character::isDigit)) {    // Compruebo que sea numero
                    System.out.println("Resultado CTG N°: " + ctg2);
                }
            } else {
                System.out.println("El pdf no tiene ctg");
            }
            String contenidoMin = contenido.toLowerCase();                      //convierto todo a minuscula para buscar producto
            System.out.println("------------------------------");
            if (contenidoMin.indexOf("trigo") != -1) {
                System.out.println("Resultado Producto: trigo");
            } else if (contenidoMin.indexOf("soja") != -1) {
                System.out.println("Resultado Producto: soja");
            } else if (contenidoMin.indexOf("maiz") != -1) {
                System.out.println("Resultado Producto: maiz");
            } else if (contenidoMin.indexOf("mani") != -1) {
                System.out.println("Resultado Producto: mani");
            } else {
                System.out.println("El pdf no tiene trigo, ni maiz, ni mani, ni soja");
            }*/
        } catch (IOException e) {
            System.out.println("Error: archivo no existe");                     
        } finally {
            if (pdDocument != null) {
                try {
                    pdDocument.close();                                         //Cierro el documento
                } catch (IOException e) {
                    System.out.println("Error: no se pudo cerrar el pdf");      
                }
            }
        }
    }
}
