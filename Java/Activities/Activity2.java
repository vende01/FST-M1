package activities;

public class Activity2 {

    public static void main(String[] args) {
     int a[]= {10,23,23,10,230,10};
     int constval=10;
     int sum=30;
     boolean result = false;
	try {
		result = isSum30(a,constval,sum);
	} catch (Exception e) {
		e.printStackTrace();
	}
     System.out.println(result);
    }
   public static  boolean  isSum30(int []a, int constval, int sum) {
    	boolean result=false;
    	int total=0;
    	for (int i = 0; i < a.length; i++) {
			if(a[i]==constval) {
				total=total+a[i];
			}
		}
    	if(total==sum) {
    		result=true;
    	}
    	
    	return result;
    }

}
