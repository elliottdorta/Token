
package org.fogbeam.example.opennlp;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerMain
{
	
	/**
	 * The main method.
	 * @author Elliott Dorta
	 * @version 1.1
	 * @param args the arguments
	 * @throws Exception the exception
	 * 
	 */
	public static void main( String[] args ) throws Exception
	{
		 
		/**
		 * Carga de archivo: Método que carga un archivo desde línea de comando y comprueba si hubo fallos o no.
		 * @param buffer almacena contenido del archivo
		 * @param file crea un objeto tipo file
		 * @param fr crea un objetio tipo filereader
		 * @param br crea un objeto tipo buffered
		 * @throws Exception the exception
		 * 
		 */
		
		  String buffer = null;
		  File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      
	      
	      
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (args[0]);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	            buffer = buffer + linea;
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
		// the provided model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );

	      /**
			 * Tokeniza un sting de entrada al que se la pasa un modelo de entramiento y un string para tokenizar.
			 * @param modelIn crea un objeto de tipo fileInput que sera el modelo de entramiento
			 * @param model crea un objeto TokenizerModel
			 *
			 */
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
		
			Tokenizer tokenizer = new TokenizerME(model);
			
				/* note what happens with the "three depending on which model you use */
			String[] tokens = tokenizer.tokenize
					(buffer);
			
			for( String token : tokens )
			{
				System.out.println( token );
			}
			
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}
		}
		System.out.println( "\n-----\ndone" );
	}
}
