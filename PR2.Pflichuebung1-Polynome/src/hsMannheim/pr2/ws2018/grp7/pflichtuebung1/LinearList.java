package hsMannheim.pr2.ws2018.grp7.pflichtuebung1;



public class LinearList {
	
	public ListElement first;
	
	public void addFirst(int value) {
	
		ListElement n = new ListElement();
		
		n.content = value;
		n.next = first;
		first = n;
	}
	
	public void removeFirst() {
	
		if (first != null)
		
			first = first.next;
	}
	
	public double getFirst() {
	
		double result = 0;
		
		
		if (first != null)
			
			result = first.content;
		
		
		return result;
	}
}