
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
			res[i] = JavaCPPLineBreakdown(after) + ";";
			total = total.concat(res[i]);
		}
		return total;
	}
	
	
	public static String JavaCPPLineBreakdown(String orig)
	{
		String[] res = orig.split(" ");
		
	}
	
	
	public static String CPPToJava(String orig) 
	{
		String[] res = orig.split(";");
		String after;
		//test for C++ using system input
		int num = orig.indexOf('>');
		char ch = ' ';
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
				res[i] = CPPJavaLineBreakdown(after) + ";";
			}
			total = total.concat(res[i]);
		}
		
		return total;
	}
	
	
	public static String CPPJavaLineBreakdown(String orig) 
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
