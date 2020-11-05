import java.io.File;

public class ReadResource {

	public static void main(String[] args) {
		
		
		final File folder = new File("./src/main");
		for (File fileEntry : folder.listFiles()) {
			System.out.println(fileEntry.getPath());
		}
		

	}

}
