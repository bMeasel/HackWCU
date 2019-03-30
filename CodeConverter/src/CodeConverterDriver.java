
public class CodeConverterDriver {

	public static void main(String[] args) 
	{
		new GUI();
	}
	
	
	
	
	
	public static String ConvertSelector(int indexFrom, int indexTo, String code) 
	{
		if(indexFrom == indexTo) {
			return code;
		}
		else if(indexFrom == 0){
			return JavaToCPP(code);
		}
		else{
			return CPPToJava(code);
		}
	}
	
	
	public static String JavaToCPP(String orig) 
	{
		String[] res = orig.split(";");
		String after;
		for(int i = 0; i < res.length; i++)
		{
			after = res[i];
			res[i] = JavaCPPLineBreakdown(after) + ";";
		}
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
				res[i] = "import java.util.Scanner; " + CPPJavaLineBreakdown(after) + ";";
			}
			else 
			{
				after = res[i];
				res[i] = CPPJavaLineBreakdown(after) + ";";
			}
		}
	}
	
	
	public static String CPPJavaLineBreakdown(String orig) 
	{
		if((orig.indexOf("cout <<") >= 0) || (orig.indexOf("cout<<") >= 0))
		{
			if((orig.indexOf("<< endl") >= 0) || (orig.indexOf("<<endl") >= 0))
			{
				
			}
		}
	}
}
