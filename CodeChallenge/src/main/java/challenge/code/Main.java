package challenge.code;

import java.util.Scanner;

public class Main 
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		ReadCSV rcsv = new ReadCSV();
		ReadJSON rjson = new ReadJSON();
		ClientList clientList = ClientList.GetclientListInst();
		String[] clientListFinal; 
		String details;
		int i =0;
		byte[] pageSize;
		Scanner sc = new Scanner(System.in);
						
		rcsv.ReadFileCSV();
		rjson.ReadFileJSON();
				
		//Menu para olhar os registros
		do
		{
			System.out.println("Digite o registro que deseja ver (Entre 1-2000)");
			i = sc.nextInt() ;		
		}while(i <=0 || i > 2000);
		
		i -=1;
		
		clientListFinal = clientList.getArrayClientJSON();
		pageSize = clientListFinal[i].getBytes();
		details = clientList.DetailJSON(i, pageSize.length, clientListFinal.length);
		
		System.out.println(clientListFinal[i]);	
		System.out.println(details);		
		
	}
}
