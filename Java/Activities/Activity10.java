package activities;

import java.util.HashSet;
import java.util.Set;

public class Activity10 {

	public static void main(String[] args) {
	

		Set<String> set1=new HashSet<String>();
		set1.add("ABC");
		set1.add("DEF");
		set1.add("GHI");
		set1.add("ABC");
		set1.add("123");
		set1.remove(0);
		System.out.println(set1);
		if(set1.remove("Z")) {
        	System.out.println("Z removed from the Set");
        } else {
        	System.out.println("Z is not present in the Set");
        }
		if(set1.remove("123")) {
        	System.out.println("123 removed from the Set");
        } else {
        	System.out.println("123 is not present in the Set");
        }
		
		System.out.println(set1.contains("GHI"));
		System.out.println(set1.size());
		
		
	
	}

}
