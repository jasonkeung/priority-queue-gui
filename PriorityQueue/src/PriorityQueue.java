import java.util.NoSuchElementException;

@SuppressWarnings({"unchecked", "rawtypes"})
public class PriorityQueue<E>
{
	private ArrayList<E> myArr;
	
	public PriorityQueue()
	{
		myArr = new ArrayList();
		myArr.add(null);
	}
	
	public void add(E item)
	{
		myArr.add(item);
		int tempIndex = myArr.size() - 1;
		if (myArr.size() != 2)
		{
			while (tempIndex != 1 && ((Comparable) myArr.get(tempIndex)).compareTo((Comparable) myArr.get(tempIndex / 2)) <= 0)
			{
				myArr.set(tempIndex, myArr.set(tempIndex / 2, item)); // swapping objects at index tempIndex and tempIndex / 2
				tempIndex /= 2;
			}
		}
	}

	public E remove()
	{
		if(myArr.size() == 1)
			throw new NoSuchElementException("The priority queue is empty.");
		int tempIndex = 1; // index where the item added from the bottom of the queue to the top currently is
		
		E removedItem =  myArr.get(1);
		myArr.set(1, myArr.get(myArr.size() - 1));
		myArr.remove(myArr.size() - 1);
		boolean cont = true;
		
		
		while(!isEmpty() && (isLeftNotNull(tempIndex) || isRightNotNull(tempIndex)) && cont) // loop runs until both left and right are null, there will be iterations where no switches are made if item is smaller than both left and right
		{
			if(isRightNotNull(tempIndex)) // if both are not null
			{
				int min = 0; // min represents left or right being the smaller value. 0 is left, 1 is right
				if(((Comparable)myArr.get(tempIndex * 2 + 1)).compareTo((Comparable)myArr.get(tempIndex * 2)) < 0)
					min = 1;
					
				//here, min is the smaller of the left/right values.
				
				if(((Comparable)myArr.get(tempIndex * 2 + min)).compareTo((Comparable)myArr.get(tempIndex)) < 0) // if the min of left and right is smaller than the item, then switch them.
				{
					myArr.set(tempIndex, myArr.set(tempIndex * 2 + min, myArr.get(tempIndex)));
					tempIndex = (tempIndex * 2) + min;
				}
				else
					cont = false;
				
			}else  // if only left is not null.
			{
				if(((Comparable)myArr.get(tempIndex * 2)).compareTo((Comparable)myArr.get(tempIndex)) < 0) // if left is smaller than item, then switch
				{	
					myArr.set(tempIndex, myArr.set(tempIndex * 2, myArr.get(tempIndex))); //switching left and item
					tempIndex *= 2; // updating tempIndex to follow the item
				}
				else
					cont = false;
			}/*
			else if(!isLeftNotNull(tempIndex) && isRightNotNull(tempIndex))
			{
				if(((Comparable)myArr.get(tempIndex * 2 + 1)).compareTo((Comparable)myArr.get(tempIndex)) < 0) // if right is smaller than item, then switch
				{
					myArr.set(tempIndex, myArr.set(tempIndex * 2 + 1, myArr.get(tempIndex)));// switching right and item
					tempIndex *= 2; // updating tempIndex to follow the item
					tempIndex += 1; // updating tempIndex to follow the item
				}
				else
					cont = false;
				System.out.println("SOMETHING IS WRONG");
			} */
				
		} // at end of loop, all necessary switches are made, item is in correct position.
		
		
		return (E) removedItem;
	}

	public boolean isEmpty()
	{
		return myArr.size() == 1;
	}

	public E peek()
	{
		return myArr.get(1);
	}

	
	
	public int size()
	{
		return myArr.size();
	}
	
	
	
	private boolean isLeftNotNull(int index)
	{
		try
		{
			if (myArr.get(index * 2) == null)
				return false;
		} catch (IndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}

	private boolean isRightNotNull(int index)
	{
		try
		{
			if (myArr.get(index * 2 + 1) == null)
				return false;
		} catch (IndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
	
	public ArrayList getArrayList()
	{
		return myArr;
	}
	
	/*{
		if(((Comparable)myArr.get(tempIndex * 2 + 1)).compareTo((Comparable)myArr.get(tempIndex)) < 0) // if right is smaller than item, then switch
		{
			myArr.set(tempIndex, myArr.set(tempIndex * 2 + 1, myArr.get(tempIndex)));// switching right and item
			tempIndex *= 2; // updating tempIndex to follow the item
			tempIndex += 1; // updating tempIndex to follow the item
		}
	}*/
	
}
