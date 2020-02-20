package challenge.code;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Classe que pega uma solicitação HTTP em JSON
public class ReadJSON
{
	
	ClientList list = ClientList.GetclientListInst();
	
	public void ReadFileJSON() 
	{
		 HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://storage.googleapis.com/juntossomosmais-code-challenge/input-backend.json")).build();

	     
		 JSONParser parserJSON = new JSONParser();
		 
		 try 
		 {
			 HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); 
			 JSONObject jsonObject = (JSONObject) parserJSON.parse(response.body());
			//Adiciona Objeto JSON no Array de Clientes
			list.AddJSONtoArray(jsonObject);
			
		} catch (IOException | ParseException | InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	}
}
