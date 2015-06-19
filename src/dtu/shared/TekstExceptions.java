package dtu.shared;

import com.google.gwt.user.client.Window;

public class TekstExceptions {

	public boolean TekstTest(String TestTekst, int Tekstlengthmin, int Tekstlengthmax) {
		
		if (Tekstlengthmin <= TestTekst.length() && TestTekst.length() <= Tekstlengthmax){
			return true;
		};
		Window.alert(TestTekst + " ikke accepteret, " + "Tekst felternerne skal vaere mellem " + Tekstlengthmin + " - " + Tekstlengthmax);
		return false;
		
	}

}
