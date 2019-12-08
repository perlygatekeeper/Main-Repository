
public class Cell 
{

	int Answer = 0;
	
	boolean[] Possible_Answers = new boolean[9];
	
	int Row, Column, Box;
	
	
	
	
	public Cell() 
	{
		// TODO Auto-generated constructor stub
		
		
		
	}
	
	
	public void Possible_Start(int Starting_Value)
	{
		
		if(Starting_Value == 0)
		{
			for(int i = 0; i < 9; i++)
					Possible_Answers[i] = true;
		}
		else
		{
			for(int i = 0; i < 9; i++)
			{
				if(Starting_Value-1==i)
					Possible_Answers[i] = true;
				else
					Possible_Answers[i] = false;
			}
		}
		
	}

}
