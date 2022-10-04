package system;

public class TextOutput {

	public TextOutput() {
		// TODO Auto-generated constructor stub
	}
	
	//Used for future proofing, if the console is moved to a different location.
	public static void println(String text) {
		System.out.println(text);
	}
	
	//Used for future proofing, if the console is moved to a different location.
	public static void print(String text) {
		System.out.print(text);
	}
}
