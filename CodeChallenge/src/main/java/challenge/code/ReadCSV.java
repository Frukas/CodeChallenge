package challenge.code;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

//Classe que pega uma solicitação HTTP em CSV
public class ReadCSV
{
	
	public void ReadFileCSV() 
	{		
		ClientList clientList = ClientList.GetclientListInst();
		  HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://storage.googleapis.com/juntossomosmais-code-challenge/input-backend.csv")).build();
		
		try
		{		
			HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
			
			clientList.AddCSVtoArray(response.body());			
			
		} catch (IOException | InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
}
