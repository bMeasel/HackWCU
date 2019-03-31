
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
			}
			else if(orig.indexOf(".println") >= 0)
			{
				orig = orig.replace("System.out.println(", "cout << ");
				orig = orig.replace(")", " << endl");
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
		int num = orig.indexOf('>');
		char ch = 'a';
		char b = ' ';
		String total = "";
		
		for(int i = 0; i < res.length; i++) 
		{
			if(num >=0)
			{
				ch = orig.charAt(num+1);
				b = orig.charAt(num);
			}
			if((num >= 0) && (ch == b) && (i == 0))
			{
				after = res[i];
				res[i] = "import java.util.Scanner;" + CPPJavaLineBreakdown(after) + ";";
			}
			else 
			{
				after = res[i];
				if(i < res.length-1)
				{
					res[i] = CPPJavaLineBreakdown(after) + ";";
				}
				else
				{
					res[i] = CPPJavaLineBreakdown(after);
				}
			}
			total = total.concat(res[i]);
		}
		
		return total;
	}
	
	
	public static String CPPJavaLineBreakdown(String orig) 
	{
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
}