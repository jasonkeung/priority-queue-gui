
public class Driver
{
	public static void main(String[] args)
	{
		PriorityQueue pq = new PriorityQueue();
		ViewWindow vw = new ViewWindow(pq);
		vw.drawQueue();
	}
}
