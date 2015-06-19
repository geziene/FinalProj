package dtu.shared;

import com.google.gwt.user.client.Window;

public class HeltalExceptions {
	
	public boolean HeltalStringTest(String TestHeltalString) {
		
		if (TestHeltalString.matches("[0-9]+")){
			return true;
			
		};
		Window.alert("ID/Status/made_by maa kun indholde tal");
		return false;
		
	}

	public boolean HeltalTest(int TestHeltal) {
		
		if (0 < TestHeltal && TestHeltal < 100000000 ){
			return true;
			
		};
		Window.alert("ID skal vaere mellem 1-99999999");
		return false;
		
	}
	
	public boolean MadebyTest(int MadebyHeltal) {
		
		if (0 < MadebyHeltal && MadebyHeltal < 100000000 ){
			return true;
			
		};
		Window.alert("made_by skal vaere mellem 1-99999999");
		return false;
		
	}
	
	public boolean GroupTest(int MadebyHeltal) {
		
		if (0 < MadebyHeltal && MadebyHeltal < 6 ){
			return true;
			
		};
		Window.alert("Gruppe skal vaere mellem 1-5");
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
