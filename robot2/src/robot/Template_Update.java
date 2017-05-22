package robot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class Template_Update {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		BufferedReader file1 = new BufferedReader(new FileReader(args[0]));

		BufferedReader file2 = new BufferedReader(new FileReader(args[1]));
		String s;
		 while ((s = file1.readLine()) != null) {
		       String[] list= s.split("\t");
		       String key = 
		      }
		 file1.close();

	}


	Map<KString, Map<, V>>

}
