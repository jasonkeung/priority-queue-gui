@SuppressWarnings("unchecked")
public class ArrayList<E>
{
	private E[] myArr;
	private int mySize;

	public ArrayList()
	{
		myArr = (E[]) new Object[10];
		mySize = 0;
	}

	public ArrayList(int i)
	{
		myArr = (E[]) new Object[i];
		mySize = 0;
	}

	public boolean add(E e)
	{
		/*if (mySize >= myArr.length)
		{
			E[] newArr = (E[]) new Object[myArr.length * 2];
			for (int i = 0; i < myArr.length; i++)
				newArr[i] = myArr[i];
			myArr = newArr;
		}*/
		add(mySize, e);
		return true;
	}

	public void add(int index, E o)// *********************
	{
		if (index > mySize || index < 0)
			throw new ArrayIndexOutOfBoundsException("Invalid Index: " + index + " for Size: " + mySize);
		else
		{
			if (index >= myArr.length)
			{
				E[] newArr = (E[]) new Object[myArr.length * 2];
				for (int i = 0; i < myArr.length; i++)
					newArr[i] = myArr[i];
				myArr = newArr;
			}
			
			if (mySize == 0)
			{
				myArr[0] = o;
				mySize++;
			} 
			else
			{
				for (int i = mySize; i >= index + 1; i--)
				{
					myArr[i] = myArr[i - 1];
				}
				myArr[index] = o;
				mySize++;
			}

		}
	}

	public E get(int index)
	{
		if (index >= 0 && index < mySize)
			return (E) myArr[index];
		else
			throw new ArrayIndexOutOfBoundsException("Invalid Index: " + index + " for Size: " + mySize);

	}

	public E set(int index, E o)
	{
		if (index < mySize && index >= 0)
		{
			E temp = myArr[index];
			myArr[index] = o;
			return temp;
		} else
		{
			throw new ArrayIndexOutOfBoundsException("Invalid Index: " + index + " for Size: " + mySize);
		}
	}

	public E remove(int index)
	{
		if (index < mySize && index >= 0)
		{
			E temp = (E) myArr[index];

			for (int i = index; i <= mySize - 1; i++)
			{
				myArr[i] = myArr[i + 1];
			}
			myArr[mySize] = null;
			mySize--;
			return temp;
		}
		else
			throw new ArrayIndexOutOfBoundsException("Invalid Index: " + index + " for Size: " + mySize);
	}

	public void removeAll()
	{
		for (int i = 0; i < mySize; i++)
		{
			myArr[i] = null;
		}

		mySize = 0;
	}

	public int size()
	{
		return mySize;
	}

	public boolean isEmpty()
	{
		return mySize == 0;
	}

}
