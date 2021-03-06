
public class CodeConverterDriver {

	public static void main(String[] args) 
	{
		new GUI();
	}
	
	
	public static String ConvertSelector(int indexFrom, int indexTo, String code) 
	{
		if(indexFrom == indexTo)
		{
			return code;
		}
		else if(indexFrom == 0)
		{
			return JavaToCPP(code);
		}
		else
		{
			return CPPToJava(code);
		}
	}
	
	
	public static String JavaToCPP(String orig) 
	{

		orig = orig.replace("import java.util.Scanner;", "#include <iostream>\nusing namespace std;");
		orig = replaceMain(orig); 
		String[] res = orig.split(";");
		String after;
		String total = "";
		for(int i = 0; i < res.length; i++)
		{
				after = res[i];
			if(res[i].indexOf("Scanner in = new Scanner(System.in)") >= 0)
			{
				res[i] = JavaCPPLineBreakdown(after,orig) + ""; 
			}
			else if(i < res.length-1)
			{
				res[i] = JavaCPPLineBreakdown(after,orig) + ";";
			}
			else
			{
				res[i] = "\n";
				res[i] = res[i].concat(JavaCPPLineBreakdown(after,orig));
				
			}
			total = total.concat(res[i]);
		}

		return total;
	}
	
	
	public static String JavaCPPLineBreakdown(String after,String orig)
	{
		after = JavaOut(after);
		after = JavaIn(after);
		return after;
	}
	
	
	public static String JavaOut(String orig)
	{
		if(orig.indexOf("System.out.print") >= 0)
		{
			//test for print w/ end line
			if(orig.indexOf(".println") >= 0)
			{
				orig = orig.replace("System.out.println(", "cout <<");
				orig = orig.replace(orig.charAt(orig.length()-1),' ');
				orig = orig.trim();
				orig = orig.concat("<< endl");
			}
			//test for print w/out end line
			else if(orig.indexOf(".print") >= 0)
			{
				orig = orig.replace("System.out.print(", "cout <<");
				orig = orig.trim();
				orig = orig.replace(orig.charAt(orig.length()-1),' ');
			}			
			//test for any plus marks inside system.out
			orig = ComboOut(orig);
		}
		return orig;
	}
	
	
	public static String JavaIn(String orig)
	{
		String cin = "\ncin >>";
		orig = orig.replace("Scanner in = new Scanner(System.in)", "");
		orig = orig.trim();
		if(orig.indexOf("= in.") >= 0)
		{
			orig = orig.replace("= in.hasNext()", "");
			orig = orig.replace("= in.nextDouble()", "");
			orig = orig.replace("= in.nextInt()", "");
			orig = orig.replace("= in.nextFloat()", "");
			orig = orig.replace("= in.nextShort()", "");
			orig = orig.replace("= in.nextLong()", "");
			orig = orig.replace("= in.next().charAt(0)", "");
			orig = orig.replace("= in.nextLine()", "");
			orig = cin.concat(orig);
		}
		return orig;
	}
	
	
	public static String ComboOut(String orig)
	{
		String[] res = orig.split("\"");
		String total = "";
		for(int i = 0; i < res.length; i++)
		{
			if(i % 2 != 1)
			{
				res[i] = JavaModOut(res[i]);
			}
			
			if(i < res.length-1)
			{
				total = total.concat(res[i]);
				total = total.concat("\"");
			}
			else
			{
				total = total.concat(res[i]);
			}
		}
		
		return total;
	}
	
	
	public static String JavaModOut(String orig)
	{
		for(int i = 0; i < orig.length(); i++)
		{
			if(orig.indexOf("+") >= 0)
			{
				orig = orig.replace("+", "<<");
			}
		}
		return orig;
	}
	
	
	public static String CPPToJava(String orig) 
	{
		orig = replaceMain(orig);
		String[] res = orig.split(";");
		String after;
		//test for C++ using system input
		int addInput = (-1);
		if(orig.indexOf("cin >>") >= 0)
		{
			addInput = orig.indexOf("cin >>");
		}
		if(orig.indexOf("cin>>") >= 0)
		{
			addInput = orig.indexOf("cin>>");
		}
		String total = "";
		for(int i = 0; i < res.length; i++) 
		{
			after = res[i];
				if(res[i].indexOf("using") >= 0)
				{
					res[i] = CPPJavaLineBreakdown(after, addInput, 1, orig) + ";";
				}
				else if(res[i].indexOf("main") >= 0)
				{
					if(addInput >= 0)
					{
						res[i] = CPPJavaLineBreakdown(after, addInput, 2, orig) + ";\nScanner in = new Scanner(System.in);\n";
					}
					else
					{
						res[i] = CPPJavaLineBreakdown(after, addInput, 2, orig) + ";";
					}
				}
				else if(i < res.length-1)
				{
					res[i] = CPPJavaLineBreakdown(after, addInput, 0, orig) + ";";
				}
				else
				{
					res[i] = CPPJavaLineBreakdown(after, addInput, 0, orig);
				}
			total = total.concat(res[i]);
		}
		
		return total;
	}
	
	
	public static String CPPJavaLineBreakdown(String orig, int addInput, int beginning, String full) 
	{
		if(beginning == 1 || beginning == 2)
		{
			orig = CPPcin(orig, beginning);
		}
		orig = CPPcout(orig);
		orig = CPPscannerIn(full, orig);
		return orig;
	}
	
	
	public static String replaceMain(String orig)
	{
		if(orig.indexOf("int main()") >= 0)
		{
			orig = orig.replace("int main()","public static void main(String[] args)");
			return orig;
		}
		if(orig.indexOf("int main( ") >=0)
		{
			orig = orig.replace("int main( ", "int main()");
			return orig;
		}
		if(orig.indexOf("public static void main(String[] args)") >= 0)
		{
			orig = orig.replace("public static void main(String[] args)", "int main()");
			return orig;
		}
		if(orig.indexOf("public static void main()") >= 0)
		{
			orig = orig.replace("public static void main()", "int main()");
			return orig;
		}
		return orig;
	}
	
	
	public static String CPPcout(String orig)
	{
		if((orig.indexOf("cout <<") >= 0) || (orig.indexOf("cout<<") >= 0))
		{
			if((orig.indexOf("cout <<") >= 0))
			{
				orig = orig.replace("cout <<", "System.out.print(");
			}
			if((orig.indexOf("cout<<") >= 0))
			{
				orig = orig.replace("cout<<", "System.out.print(");
			}
			
			
			if((orig.indexOf("<< endl") >= 0) || (orig.indexOf("<<endl") >= 0))
			{
				orig = orig.replace("System.out.print(", "System.out.println(");
				if((orig.indexOf("<< endl") >= 0))
				{
					orig = orig.replace("<< endl", ")");
				}
				if((orig.indexOf("<<endl") >= 0))
				{
					orig = orig.replace("<<endl", ")");
				}
			}
			else
			{
				orig = orig.concat(")");
			}
			
			
			if(orig.indexOf("<<") >= 0)
			{
				orig = orig.replace("<<", "+");
			}
		}
		
		return orig;
	}
	
	
	public static String CPPscannerIn(String orig, String line)
	{
		if(line.indexOf("cin >>") >= 0 || line.indexOf("cin>>") >= 0)
		{
			line = line.replace("cin >>", "");
			line = line.replace("cin>>", "");
			line = line.trim();
		}
		String modifier = "";
		int index; 
		if(orig.indexOf(line) >= 9)
		{
			index = orig.indexOf(line) - 9;
		}
		else
		{
			index = 0;
		}
		modifier = orig.substring(index, orig.indexOf(line)+2);
		if(modifier.indexOf("boolean") >= 0)
		{
			line = line.concat("= in.hasNext()");
		}
		else if(modifier.indexOf("int") >= 3)
		{
			line = line.concat("= in.nextInt()");
		}
		else if(modifier.indexOf("double") >= 0)
		{
			line = line.concat("= in.nextDouble()");
		}
		else if(modifier.indexOf("String") >= 0)
		{
			line = line.concat("= in.nextLine()");
		}
		else if(modifier.indexOf("char") >= 0)
		{
			line = line.concat("= in.next().charAt(0)");
		}
		else if(modifier.indexOf("float") >= 0)
		{
			line = line.concat("= in.nextFloat()");
		}
		else if(modifier.indexOf("long") >= 0)
		{
			line = line.concat("= in.nextLong()");
		}
		else if(modifier.indexOf("short") >= 0)
		{
			line = line.concat("= in.nextShort()");
		}
		
		return line;
	}
	
	
	public static String CPPcin(String orig, int beginning)
	{
		if((orig.indexOf("#include <iostream>") >= 0) && beginning == 1)
		{
			orig = orig.replace("#include <iostream>", "import java.util.Scanner");
			if(orig.indexOf("using namespace std") >= 0)
			{
				orig = orig.replace("using namespace std", "");
				orig = orig.trim();
			}
		}
			return orig;
	}
	
	
}