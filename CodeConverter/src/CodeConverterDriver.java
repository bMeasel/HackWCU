
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
		
	}
	
	public static String CPPToJava(String orig) 
	{
		String[] res = orig.split(";");
		String after;
		for(int i = 0; i < res.length; i++) 
		{
			after = res[i];
			res[i] = CPPJavaLineBreakdown(after) + ";";
		}
	}
	
	public static String CPPJavaLineBreakdown(String orig) 
	{
		
	}
}
