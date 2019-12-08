
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/*
 * 
 * A solved Cell is a unique Possible_Answer in either a Row, Column, or Box.
 * 
 * 
 * Step 1. Update all Possible_Answer based on Starting Values
 * Step 2. Find Imaginary Numbers (Look at this later)
 * Step 3. Attempt to Find Unique Answers in rows, Columns, and Boxes.
 * Repeat
 * 
 */


public class Grid 
{
	
	public static void main(String[] args) throws IOException  {
		Grid StartUp = new Grid();
	  }
	
	Cell[] Cell_Map = new Cell[81];
	
	int Row = 0, Column = 0;
	int Cell_Index = 0;
	
	int Solve_Count = 0;
	
	boolean Solved = false;
	
	
	public Grid() 
	{
		//(Check) Stupidity Proof 
		//(Check) 17 rules.
		//No starting duplicates
		//Fits format of 9x9
		//(Check) Each pass has to solve at least 1 cell or else it stops.
		
		//New Format reader one line of 81 chars or 9x9
		
		//create imaginary logic
		
		//Get onto GitHub
		
		
		
		
		
		
		
		
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Input"));
			String Input_Row = "";
			
			Input_Row = reader.readLine();
			
			if(Input_Row == null)
			{
				System.out.println("Input Format Error");
				System.exit(0);
			}
			
			if(Input_Row.length() != 81)
			{
				for(int i = 0;i<8;i++)
				{
					if(Input_Row.length() != 9)
					{
						System.out.println("Input Format Error");
						System.exit(0);
					}
					Input_Row = reader.readLine();
					if(Input_Row == null)
					{
						System.out.println("Input Format Error");
						System.exit(0);
					}
				}
				
				
			}	
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		//Pulling the Data from the Input Text File.
		try {
			reader = new BufferedReader(new FileReader("Input"));
			String Input_Row = "";
			
			
			for(;Row < 9; Row++)
			{
				Input_Row = reader.readLine();
			
				
				for(;Column < 9; Column++)
				{
					Cell_Index = (Row*9)+Column;
					Cell_Map[Cell_Index] = new Cell();
					Cell_Map[Cell_Index].Answer = Character.getNumericValue(Input_Row.charAt(Column));
					Cell_Map[Cell_Index].Row = Row;
					Cell_Map[Cell_Index].Column = Column;
					Cell_Map[Cell_Index].Box = ((int)Math.floor(Row/3)*3)+((int)Math.floor(Column/3));
					
					Cell_Map[Cell_Index].Possible_Start(Cell_Map[Cell_Index].Answer);
				}
				Column = 0;
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Starting Point
		//Determine Possible_Answers
		for(int Grid_Index = 0; Grid_Index < 81; Grid_Index++)
		{
			if(Cell_Map[Grid_Index].Answer > 0)
			{
				Update_Possible_Answers(Cell_Map[Grid_Index]);
				Solve_Count++;
			}
			
		}
		
		if(Solve_Count < 17)
		{
			System.out.println("Not enough starting information to solve");
			System.exit(0);
		}
		
		while(Solved == false)
		{
			Solve_Count = 0;
			for(int Grid_Index = 0; Grid_Index < 81; Grid_Index++)
			{
				if(Cell_Map[Grid_Index].Answer == 0)
					Solve_Cell(Cell_Map[Grid_Index]);
			}
			Check_Solved();
			Print_Solution(Cell_Map);
			if(Solve_Count == 0)
			{
				System.out.println("No progress was made and the program was terminated.");
				System.exit(0);
			}
		}
		
		
		
		
		
		// TODO Auto-generated constructor stub
	}

	public void Update_Possible_Answers(Cell Target)
	{
		for(int Grid_Index = 0; Grid_Index < 81; Grid_Index++)
		{
			if(Cell_Map[Grid_Index].Row == Target.Row)
				Cell_Map[Grid_Index].Possible_Answers[Target.Answer-1] = false;
			if(Cell_Map[Grid_Index].Column == Target.Column)
				Cell_Map[Grid_Index].Possible_Answers[Target.Answer-1] = false;
			if(Cell_Map[Grid_Index].Box == Target.Box)
				Cell_Map[Grid_Index].Possible_Answers[Target.Answer-1] = false;
		}
	}
	
	public void Parelle_Logic()
	{
		
	}
	
	public void Perpendicular_Logic()
	{
		
	}
	
	public void Combined_Logic()
	{
		
	}
	
	public void Solve_Cell(Cell Target)
	{
		Cell[] Solve_Row = new Cell[9];
		int R_Ind = 0;
		Cell[] Solve_Column = new Cell[9];
		int C_Ind = 0;
		Cell[] Solve_Box = new Cell[9];
		int B_Ind = 0;
		
		for(int Grid_Index = 0; Grid_Index < 81; Grid_Index++)
		{
			if(Cell_Map[Grid_Index].Row == Target.Row)
			{
				Solve_Row[R_Ind] = Cell_Map[Grid_Index];
				R_Ind++;
			}
			if(Cell_Map[Grid_Index].Column == Target.Column)
			{
				Solve_Column[C_Ind] = Cell_Map[Grid_Index];
				C_Ind++;
			}
			if(Cell_Map[Grid_Index].Box == Target.Box)
			{
				Solve_Box[B_Ind] = Cell_Map[Grid_Index];
				B_Ind++;
			}
		}
		
		for(int Answer_Index = 0; Answer_Index < 9; Answer_Index++)
		{
			if(Target.Possible_Answers[Answer_Index] == true)
			{
				if(Check_Answer(Solve_Row, Answer_Index, Target) & Answer_Index < 9)
				{
					Target.Answer = Answer_Index + 1;
					Answer_Index = 9;
					Target.Possible_Start(Target.Answer);
					Update_Possible_Answers(Target);
					Solve_Count++;
				}
				if(Check_Answer(Solve_Column, Answer_Index, Target) & Answer_Index < 9)
				{
					Target.Answer = Answer_Index + 1;
					Answer_Index = 9;
					Target.Possible_Start(Target.Answer);
					Update_Possible_Answers(Target);
					Solve_Count++;
				}
				if(Check_Answer(Solve_Box, Answer_Index, Target) & Answer_Index < 9)
				{
					Target.Answer = Answer_Index + 1;
					Answer_Index = 9;
					Target.Possible_Start(Target.Answer);
					Update_Possible_Answers(Target);
					Solve_Count++;
				}
					
			}
		}
		
	}
	
	
	public boolean Check_Answer(Cell[] Area, int Answer, Cell Target)
	{
		boolean Answer_Found = true;
		if(Answer < 9)
		{
			for(int i = 0; i < 9; i++)
			{
				if(Area[i].Row == Target.Row & Area[i].Column == Target.Column & Area[i].Box == Target.Box)
				{
					
				}
				else
					if(Area[i].Possible_Answers[Answer])
					{
						Answer_Found = false;
						i = 9;
					}
			}
				
		}		
		return Answer_Found;
		
	}
	
	
	public void Check_Solved()
	{
		Solved = true;
		for(int Grid_Index = 0; Grid_Index < 81; Grid_Index++)
			if(Cell_Map[Grid_Index].Answer == 0)
			{
				Solved = false;
				Grid_Index = 81;
			}
	}
	
	public void Print_Solution(Cell[] Solved_Grid)
	{
		String Output_Row = "";
		Row = 0;
		Column = 0;
		for(;Row < 9; Row++)
		{
			for(;Column < 9; Column++)
			{
				Cell_Index = (Row*9)+Column;
				Output_Row = Output_Row + " " + Cell_Map[Cell_Index].Answer;
			}
			System.out.println(Output_Row);
			Column = 0;
			Output_Row = "";
		}
		System.out.println("____________________________");
	}
	
}
