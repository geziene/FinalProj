package dtu.shared;

import com.google.gwt.user.client.Window;

public class DoubleExceptions {
	
	
	public boolean DoubleStringTest(String TestDoubleString) {
		if (!TestDoubleString.contains("-"))
		try {
	        Double.parseDouble(TestDoubleString);
	        return true;
	    } catch (NumberFormatException e) {
	    	Window.alert("Double maa kun indholde positive tal og punktum");
	        return false;
	    }
		else{
			Window.alert("Double maa kun indholde positive tal og punktum");
	        return false;
		}
	}
	
	public double DoubleDecimalTest(String maengdeString) {
		double maengde;
		double scale = Math.pow(10, 4); 
		maengde = Math.round(Double.parseDouble(maengdeString) * scale) / scale;
		return maengde;
	}
}