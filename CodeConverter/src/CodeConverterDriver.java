
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
		String[] res = orig.split(";");
		String after;
		String total = "";
		for(int i = 0; i < res.length; i++)
		{
			after = res[i];
			if(after.indexOf("main(") >= 0)
			{
				after = replaceMain(after);
			}
			res[i] = JavaCPPLineBreakdown(after) + ";";
			total = total.concat(res[i]);
		}
		return total;
	}
	
	
	public static String JavaCPPLineBreakdown(String orig)
	{
		orig = JavaOut(orig);
		return orig;
	}
	
	
	public static String JavaOut(String orig)
	{
		if(orig.indexOf("System.out.print") >= 0)
		{
			//test for print w/out end line
			if(orig.indexOf(".print(") >= 0)
			{
				orig = orig.replace("System.out.print(", "cout << ");
				orig = orig.replace(")","");
			}
			//test for print w/ end line
			else if(orig.indexOf(".println") >= 0)
			{
				orig = orig.replace("System.out.println(", "cout << ");
				orig = orig.replace(")", " << endl");
			}
			//test for any plus marks inside system.out
			orig = ComboOut(orig);
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
			orig = orig.replace("+", "<<");
		}
		return orig;
	}
	
	
	public static String CPPToJava(String orig) 
	{
		orig = replaceMain(orig);
		String[] res = orig.split(";");
		String after;
		//test for C++ using system input
		int addInput;
		if(orig.indexOf("cin >>") >= 0)
		{
			addInput = orig.indexOf("cin >>");
		}
		else
		{
			addInput = orig.indexOf("cin>>");
		}
		String total = "";
		for(int i = 0; i < res.length; i++) 
		{
			after = res[i];
				if(i < res.length-1)
				{
					res[i] = CPPJavaLineBreakdown(after, addInput, 0) + ";";
				}
				else if(res[i].indexOf("using") >= 0)
				{
					res[i] = CPPJavaLineBreakdown(after, addInput, 1) + ";";
				}
				else if(res[i].indexOf("main") >= 0)
				{
					res[i] = CPPJavaLineBreakdown(after, addInput, 2) + ";";
				}
				else
				{
					res[i] = CPPJavaLineBreakdown(after, addInput, 0);
				}
			total = total.concat(res[i]);
		}
		
		return total;
	}
	
	
	public static String CPPJavaLineBreakdown(String orig, int addInput, int beginning) 
	{
		if((beginning == 1 || beginning == 2) && addInput >= 0)
		{
			orig = CPPcin(orig, beginning);
		}
		orig = CPPcout(orig);
		return orig;
	}
	
	
	public static String replaceMain(String orig)
	{
		if(orig.indexOf("int main()") >= 0)
		{
			orig = orig.replace("int main()","public static void main(String[] args)");
			return orig;
		}
		else if(orig.indexOf("public static void main(String[] args)") >= 0)
		{
			orig = orig.replace("public static void main(String[] args)", "int main()");
			return orig;
		}
		return orig;
	}
	
	
	public static String CPPcout(String orig)
	{
		String closing = ")";
		if((orig.indexOf("cout <<") >= 0) || (orig.indexOf("cout<<") >= 0))
		{
			if((orig.indexOf("cout <<") >= 0))
			{
				orig = orig.replace("cout <<", "System.out.print(");	
			}
			else
			{
				orig = orig.replace("cout<<", "System.out.print(");
			}
			
			
			if((orig.indexOf("<< endl") >= 0) || (orig.indexOf("<<endl") >= 0))
			{
				orig = orig.replace("System.out.print(", "System.out.println(");
				if((orig.indexOf("<< endl") >= 0))
				{
					orig = orig.replace("<< endl", closing);
				}
				else
				{
					orig = orig.replace("<<endl", closing);
				}
			}
			else
			{
				orig = orig.concat(closing);
			}
			
			
			if(orig.indexOf("<<") >= 0)
			{
				orig = orig.replace("<<", "+");
			}
		}
		
		return orig;
	}
	
	
	public static String CPPcin(String orig, int beginning)
	{
		if((orig.indexOf("#include <iostream>") >= 0) && beginning == 1)
		{
			orig = orig.replace("#include <iostream>", "import java.util.Scanner;");
			orig = orig.replace("using namespace std", "");
		}
		if(beginning == 2)
		{
			orig = createScanner(orig);
			return orig;
		}
		else
		{
			return orig;
		}
	}
	
	
	public static String createScanner(String orig)
	{
		int index = orig.indexOf("{", orig.indexOf("main"));
		String newStr = "";
		String addScanner = "Scanner in = new Scanner(System.in);";
		for(int i = 0; i < orig.length(); i++)
		{
			newStr += orig.charAt(i);
			if(i == index)
			{
				newStr += addScanner;
			}
		}
		return newStr;
	}
}