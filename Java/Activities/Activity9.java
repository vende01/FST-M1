package activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity9 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("Hari", "Venu", "Sam", "Kiran");
		System.out.println(list);

		List<String> list1=new ArrayList<String>();
		list1.add("ABC");
		list1.add("DEF");
		list1.add("GHI");
		list1.remove(0);
		System.out.println(list1);
		System.out.println(list1.indexOf("DEF"));
		System.out.println(list1.indexOf("GHI"));
		
		System.out.println(list1.contains("GHI"));
		System.out.println(list1.size());
		
		
	
	}

}
