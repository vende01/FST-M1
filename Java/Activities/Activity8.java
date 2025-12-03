package activities;

public class Activity8 {

	public static void main(String[] args) throws CustomException {
		exceptionTest("Hellow world");
		exceptionTest(null);
		

	}
public static void exceptionTest(String msg) throws CustomException {
	if(msg==null) {
		throw new CustomException("String is null");
	}
	else {
		System.out.println(msg);
	}
}
}
class CustomException extends Exception{
	public String msg=null;
	public CustomException(String msg) {
		super();
		this.msg = msg;
	}
@Override
public String getMessage() {
	
	return msg;
}
	
}