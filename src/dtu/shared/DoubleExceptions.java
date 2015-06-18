package dtu.shared;

import com.google.gwt.user.client.Window;

public class DoubleExceptions {
	
	public boolean DoublenonNettoTest(double maengdeString) {

		if (0.05 <= maengdeString && maengdeString <= 20.00 ){
			return true;
			
		};
		Window.alert("Kvantum(netto) skal vaere mellem 0.05-20.00");
		return false;
	}
		
	public boolean DoubletoleranceTest(double maengdeString) {

		if (0.1 <= maengdeString && maengdeString <= 10.0 ){
			return true;
			
		};
		Window.alert("tolerance skal vaere mellem 0.1-10.0");
		return false;
	}
	
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