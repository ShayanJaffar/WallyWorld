import java.util.LinkedList;

import com.google.gson.annotations.Expose;

public class Resume {
	@Expose
	private LinkedList<String> prevWork;
	@Expose
	private LinkedList<String> prevEdu;
	@Expose
	private LinkedList<String> skills;
	
	public String toString() {
		String s = "";
		if(prevWork != null) {
			s+= "Previous Work: \n";
			for(String pW : prevWork)
				s+=(pW+"\n");
		}
		if(prevEdu != null) {
			s+= "Previous Education: \n";
			for(String pE : prevEdu)
				s+=(pE+"\n");
		}
		if(skills != null) {
			s+= "Skills: \n";
			for(String sk : skills)
				s+=(sk+"\n");
		}
		return s;
	}
}
				
