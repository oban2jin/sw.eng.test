package sw.eng.test;

public class Meeting implements Comparable<Meeting> {

	private int startTime;
	private int endTime;
	private int meetingNo;
	
	public Meeting( int meetingNo,int startTime, int endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.meetingNo = meetingNo;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer(Integer.toString(meetingNo)+"/"+Integer.toString(startTime)+"/"+Integer.toString(endTime));
		return sb.toString();
	}

	@Override
	public int compareTo(Meeting arg0) {
		
		int rslt = this.getEndTime()-arg0.getEndTime();
		
		if(rslt ==0){
			rslt = this.getStartTime() - arg0.getStartTime();
		}
		return rslt;
	}

	
}
