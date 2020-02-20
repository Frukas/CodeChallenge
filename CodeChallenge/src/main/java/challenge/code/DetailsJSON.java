package challenge.code;

//Classe para o template para Metadata
public class DetailsJSON
{
	
	public DetailsJSON(int pageNumber, int pageSize, int totalCount) 
	{
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		
	}
	int pageNumber;
    int pageSize;
    int totalCount;
    String users; //TODO Decobrir qual usuario
}
