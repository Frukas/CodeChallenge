package challenge.code;


import org.json.simple.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import challenge.code.ClientsJSON;

//Classe que unifica todos os registros em um unico tipo de Classe
public class Clients 
{
	
	private String gender, name_title, name_first, name_last, location_street, location_city, location_state, location_postcode, locatio_coordinates_latitude, location_coordinates_longitude, location_timezone_offset, location_timezone_description, email, dob_date, dob_age, registered_date, registered_age, phone, cell, picture_large, picture_medium, picture_thumbnail,nationality ;

	private ClientsJSON clienteJSON = new ClientsJSON();
	
	//Função responsável em pupular os fields recebendo um array de dados
	private void PopulateClients(String[] fields)
	{
		
		this.gender = Character.toString(fields[0].charAt(0)).toUpperCase();
		this.name_title = fields[1];
		this.name_first = fields[2];
		this.name_last = fields[3];
		this.location_street = fields[4];
		this.location_city = fields[5];
		this.location_state = fields[6];
		this.location_postcode = fields[7];
		this.locatio_coordinates_latitude = fields[8];
		this.location_coordinates_longitude = fields[9];
		this.location_timezone_offset = fields[10];
		this.location_timezone_description = fields[11];
		this.email = fields[12];
		this.dob_date = fields[13];
		this.dob_age = fields[14];
		this.registered_date = fields[15];
		this.registered_age = fields[16];
		this.phone = ConvertPhoneNumber(fields[17]);
		this.cell = ConvertPhoneNumber(fields[18]);
		this.picture_large = fields[19];
		this.picture_medium = fields[20];
		this.picture_thumbnail = fields[21];	
		this.nationality = (fields.length >= 23)?fields[22]:"BR";		
	}
	
	//função simples para popular os fields
	public Clients(String[] fields) 
	{
		PopulateClients(fields);
		
	}
	
	//Classifica a regiões entre Super, Normal, Trabalhoso depenpendendo de sua longitude e latitude
	public String getClassification() 
	{
		float longitude = Float.valueOf(location_coordinates_longitude);		
		float latitude = Float.valueOf(locatio_coordinates_latitude);
				
		float Esp1minlon = -2.196998f;  
		float Esp1maxlon = -15.411580f;

		float Esp1minlat = -46.361899f; 
		float Esp1maxlat = -34.276938f;

		float Esp2minlon = -19.766959f; 
		float Esp2maxlon = -23.966413f;

		float Esp2minlat = -52.997614f; 
		float Esp2maxlat = -44.428305f;
		
		float normalminlon= -26.155681f;
		float normalmaxlon= -34.016466f;

		float normalminlat= -54.777426f;
		float normalmaxlat= -46.603598f;
		
				
		if((Esp1minlon >= longitude)&&(Esp1maxlon <= longitude)&&(Esp1minlat <= latitude)&&(Esp1maxlat >= latitude))
		{
			return "Super"; 
		}else if((Esp2minlon >= longitude)&&(Esp2maxlon <= longitude)&&(Esp2minlat <= latitude)&&(Esp2maxlat >= latitude))
		{
			return "Super";
			
		}else if((normalminlon >= longitude)&&(normalmaxlon <= longitude)&&(normalminlat <= latitude)&&(normalmaxlat >= latitude))
		{
			return "Normal";
		}else 
		{
			return "Trabalhoso";
		}
	}	
	
	
	
	//Print de teste para checar se a classe está recebendo corretamente os dados
	public void printCliente() 
	{
		System.out.println();
		System.out.println("Gender: " + this.gender);
		System.out.println("Title: " +  this.name_title);
		System.out.println("First Name: " + this.name_first);
		System.out.println("Last Name: " + this.name_last);
		System.out.println("Street: " + this.location_street);
		System.out.println("City: " + this.location_city);
		System.out.println("State: " + this.location_state );
		System.out.println("PostCode: " + this.location_postcode);
		System.out.println("Latitude: " + this.locatio_coordinates_latitude);
		System.out.println("Longitude: " + this.location_coordinates_longitude);
		System.out.println("Offset: " + this.location_timezone_offset );
		System.out.println("Timezone: " + this.location_timezone_description);
		System.out.println("Email" + this.email);
		System.out.println("Date: " + this.dob_date);
		System.out.println("Age " + this.dob_age);
		System.out.println("Registered Date: "+ this.registered_date);
		System.out.println("Registered Age: " + this.registered_age);
		System.out.println("Phone: " + this.phone);
		System.out.println("CellPhone: " + this.cell);
		System.out.println("Picture Large: " + this.picture_large);
		System.out.println("Picture Medium: " + this.picture_medium);
		System.out.println("Thumbnail: " + this.picture_thumbnail);
		System.out.println("Nacionality: " + this.nationality);
	}
	
	//Converte os dados de um JSON para passar para classe cliente
	public Clients(JSONObject linhaJSON) 
	{
				
		String[] clientData = 
			{
				returnValueN1(linhaJSON,"gender"),
				returnValueN2(linhaJSON,"name","title"),
				returnValueN2(linhaJSON,"name","first"),
				returnValueN2(linhaJSON,"name","last"),
				returnValueN2(linhaJSON,"location","street"),
				returnValueN2(linhaJSON,"location","city"),
				returnValueN2(linhaJSON,"location","state"),
				returnValueN2(linhaJSON,"location","postcode"),	
				returnValueN3(linhaJSON,"location","coordinates","latitude"),
				returnValueN3(linhaJSON,"location","coordinates","longitude"),
				returnValueN3(linhaJSON,"location","timezone","offset"),
				returnValueN3(linhaJSON,"location","timezone","description"),
				returnValueN1(linhaJSON,"email"),
				returnValueN2(linhaJSON,"dob","date"),
				returnValueN2(linhaJSON,"dob","age"),
				returnValueN2(linhaJSON,"registered","date"),
				returnValueN2(linhaJSON,"registered","age"),
				returnValueN1(linhaJSON,"phone"),
				returnValueN1(linhaJSON,"cell"),
				returnValueN2(linhaJSON,"picture","large"),
				returnValueN2(linhaJSON,"picture","medium"),
				returnValueN2(linhaJSON,"picture","thumbnail")					
			};	
		PopulateClients(clientData);
	}
	//Retorna os valores em uma profundadidade de um nível
	private String returnValueN1(JSONObject Objct, String key1)
	{
		
		return (String)Objct.get(key1).toString();	
		
	}
	//Retorna os valores em uma profundadidade de dois níveis	
	private String returnValueN2(JSONObject Objct, String key1,String key2 ) 
	{
		JSONObject obj = (JSONObject)Objct.get(key1);
			
		return (String)obj.get(key2).toString();		
	}
	//Retorna os valores em uma profundadidade de três níveis
	private String returnValueN3(JSONObject Objct, String key1,String key2, String key3 ) 
	{
		JSONObject obj = (JSONObject)Objct.get(key1);
		JSONObject obj2 = (JSONObject)obj.get(key2);
		
		return (String)obj2.get(key3).toString();		
	}
	
	//Transforma os número de telefone em E.164
	private String ConvertPhoneNumber(String number) 
	{
		char[] numberArray = number.toCharArray();
		String numberConverted = "";
		
		for(int i = 0; i < numberArray.length; i++) 
		{
			if(numberArray[i] != '(' && numberArray[i] != ')' && numberArray[i] != '-'&& !Character.isWhitespace(numberArray[i]) ) 
			{
				numberConverted = numberConverted + numberArray[i];
			}
		}
		//ToDo implementar outras nacionalidades 
		numberConverted = "+55" + numberConverted;
		
		return numberConverted;		
	}
	
	//Muda para o formato padrão de OUTPUT
	public void ClientOUTPUT()
	{
		clienteJSON.type = getClassification();
		clienteJSON.gender = this.gender;
		clienteJSON.name.title = this.name_title;
		clienteJSON.name.first = this.name_first;
		clienteJSON.name.last = this.name_last;
		//clienteJSON.location.region TODO criar metodo para localizar região
		clienteJSON.location.street = this.location_street;
		clienteJSON.location.city = this.location_city;
		clienteJSON.location.State = this.location_state;
		clienteJSON.location.postcode = this.location_postcode;
		clienteJSON.location.coordinates.latitude = this.locatio_coordinates_latitude;
		clienteJSON.location.coordinates.longitude = this.location_coordinates_longitude;
		clienteJSON.location.timezone.offset = this.location_timezone_offset;
		clienteJSON.location.timezone.description = this.location_timezone_description;
		clienteJSON.email = this.email;
		clienteJSON.birthday = this.dob_date;
		clienteJSON.registered = this.dob_date;
		clienteJSON.telephoneNumber = this.phone;
		clienteJSON.mobileNumbers = this.cell;
		clienteJSON.picture.large = this.picture_large;
		clienteJSON.picture.medium = this.picture_medium;
		clienteJSON.picture.thumbnail = this.picture_thumbnail;
		clienteJSON.nationality = this.nationality;
	}
	
	//Retorna String no formato JSON do padrão OUTPUT
	public String getClienteInJSON()
	{
		ClientOUTPUT(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(clienteJSON);
		
		return json;		
	}	
}
