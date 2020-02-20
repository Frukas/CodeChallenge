package challenge.code;

//Classe para montar o template para o formato OUTPUT
public class ClientsJSON
{

	String type;
	String gender ;
	Name name = new Name() ;
	Location location = new Location();
	String email;
	String birthday;
	String registered;
	String telephoneNumber;
	String mobileNumbers;
	Picture picture = new Picture();
	String nationality;	
}

class Name {
	String title;
	String first;
	String last;
	
}
class Location {
	String region;
	String street ;
	String city;
	String State;
	String postcode;
	Coordinates coordinates = new Coordinates();
	Timezone timezone = new Timezone();
	
}
class Coordinates{
	String latitude;
	String longitude;	
	
}
class Timezone{
	String offset;
	String description;
}
class Picture{
	String large;
	String medium;
	String thumbnail;
}
