package sw.eng.test;

import java.util.ArrayList;
import java.util.Vector;

public class MeetingScheduler {
	private ArrayList<Meeting> meetings = new ArrayList<Meeting>();
	private Vector<Meeting> schdule = new Vector<Meeting>();
	
	public MeetingScheduler() {
		super();
		meetings.add(new Meeting(1, 3, 5));
		meetings.add(new Meeting(2, 1, 4));
		meetings.add(new Meeting(3, 2, 13));
		meetings.add(new Meeting(4, 5, 9));
		meetings.add(new Meeting(5, 5, 7));
		meetings.add(new Meeting(6, 0, 6));
		meetings.add(new Meeting(7, 8, 11));
		meetings.add(new Meeting(8, 8, 12));
		meetings.add(new Meeting(9, 12, 14));		
	}

	public static void main(String[] args) {
		MeetingScheduler  ms = new MeetingScheduler();
		ms.doSomething();
		
		for(Meeting m : ms.schdule){
			System.out.println(m.toString());
		}
	}
	
	public void doSomething(){
		meetings.sort(null);
		
		for(Meeting m: this.meetings){
			if(schdule.size()==0){
				schdule.add(m);
			}else if((m.getStartTime()-schdule.lastElement().getEndTime())>=0){
				schdule.add(m);
			}
		}
	}
}
