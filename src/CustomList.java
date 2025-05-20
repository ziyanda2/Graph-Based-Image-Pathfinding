import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomList<T> implements Iterable<T>{
	private Object[] elements;
	private int size;
	
	public CustomList() {
		elements = new Object[10];
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public void add(T element) {
		if(size == elements.length) {
			resize();
		}
		
		elements[size] = element;
		size++;
	}
	
	public T get(int index) {
		if(index >= 0 && index < size) {
			return (T) elements[index];
		}
		throw new IndexOutOfBoundsException("Index out of bounds");
	}
	
	public void remove(int index) {
		if(index >= 0 && index < size) {
			for(int i = index; i < size -1; i++) {
				elements[i] = elements[1 + 1];
			}
			elements[--size] = null;
		}else {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
	}
	
	
	public void resize() {
		int newSize = elements.length * 2;
		Object[] newElements = new Object[newSize];
		System.arraycopy(elements, 0, newElements, 0, elements.length);
		elements = newElements;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>(){
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index <size;
			}

			@Override
			public T next() {
				if(!hasNext()) throw new NoSuchElementException();
				return (T) elements[index++];
			} 
		};
	}
}
