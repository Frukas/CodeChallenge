package challenge.code;

import java.util.ArrayList;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//Classe responsável por unificar todos os registers em um único array
public class ClientList
{
	
	static ClientList lClientInst = null;
	ArrayList<Clients> clientList = new ArrayList<Clients>();
	String [] clientListJson;
	
	
	//Cria um singleton da classe ListaCLientes
	public static ClientList GetclientListInst() 
	{
		if(lClientInst == null) 
		{
			lClientInst = new ClientList();
		}
		
		
		return lClientInst;				
	}
	
	private ClientList()	{}
	
	
	private void AddClient(Clients clientes)
	{
		clientList.add(clientes);
	}	
	
	public ArrayList<Clients> RetornaLista()
	{
		return clientList;
	}
	
	//Coloca no array os dados que originados de um CSV
	public void AddCSVtoArray(String line) 
	{		
		//Gera um array dos registers
		String[] lines = line.split("\\n");
		String[] register;
		String[] finalRegistry = new String[22];
		
		for(int j = 1; j < lines.length; j++) {
			
			register = lines[j].split("\"");
					
			for(int i = 1 , h = 0; i < register.length; i++ ) 
			{
				//Para tirar a virgula dos registers
				if(i%2 != 0) 
				{
					finalRegistry[h] = register[i];
					h++;
				}
			}	
				
				AddClient(new Clients(finalRegistry));	
		}
	   
	}
	
	//Coloca no array os dados que originados de um JSON
	public void AddJSONtoArray(JSONObject jsonObject) 
	{
		JSONArray dadosClientes = (JSONArray) jsonObject.get("results");
		
		for(int i = 0;i< dadosClientes.size() ;i++)
		{
			JSONObject obj = (JSONObject)dadosClientes.get(i);				
			AddClient(new Clients(obj));;	
		}	 
	}
	
	public String[] getArrayClientJSON() 
	{
		clientListJson = new String[clientList.size()];
		
		for(int i = 0; i < clientListJson.length; i++ ) 
		{
			clientListJson[i] = clientList.get(i).getClienteInJSON();
		}		
		
		return clientListJson;		
	}
	
	//Printa os dados de paginação de um dado
	public String DetailJSON(int pageNumber, int pageSize, int totalCount) 
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(new DetailsJSON(pageNumber,pageSize,totalCount) );
		
		return json;
	}	
}