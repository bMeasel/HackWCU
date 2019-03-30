
public class CodeConverterDriver {

	public static void main(String[] args) 
	{
		
	}
	public static String ConvertSelector(int indexFrom, int indexTo, String code) {
		if(indexFrom == indexTo) {
			return code;
		}
		else if(indexFrom == 0){
			return JavaToPython(code);
		}
		else{
			return PythonToJava(code);
		}
	}
	public static String JavaToPython(String orig) {
		return orig;
	}
	public static String PythonToJava(String orig) {
		return orig;
	}
}
