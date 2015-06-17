package dtu.shared;

import com.google.gwt.user.client.Window;

public class HeltalExceptions {
	
	public boolean HeltalStringTest(String TestHeltalString) {
		
		if (TestHeltalString.matches("[0-9]+")){
			return true;
			
		};
		Window.alert("ID maa kun indholde tal");
		return false;
		
	}

	public boolean HeltalTest(int TestHeltal) {
		
		if (0 < TestHeltal && TestHeltal < 100000000 ){
			return true;
			
		};
		Window.alert("ID skal vaere mellem 1-99999999");
		return false;
		
	}
	
	public boolean StatusTest(int TestStatus) {
		
		if (0 <= TestStatus && TestStatus <= 2){
			return true;
			
		};
		Window.alert("Status skal vaere mellem 0-2");
		return false;
		
	}

}
