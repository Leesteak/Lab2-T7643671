/**
* Stack implementation made for the TestAction, TestDesign and PointCPTest classes
*
* @author Natalia Maximo <natalia.maximo@uottawa.ca>
*
*/

public class TStack<E> {
	private static class Node<E> {
		Node<E> next;
		E value;
		
		private Node(E val, Node<E> n){
			this.next = n;
			this.value = val;
		}
	}
	Node<E> head;
	int size;
	
	public TStack () {
		size = 0;
		head = new Node<E>(null, null);
	}

	public void push(E val) {
		Node<E> current = new Node<E>(val, head.next);
		head.next = current;
		size++;
	}

	public E pop() {
		if (head.next == null) return null; // no more elements
		Node<E> popped = head.next;
		head.next = popped.next;
		size--;

		return popped.value;
	}

	public E peek() {
		if (head.next == null) return null; // no more elements

		return head.next.value;
	}

	public int size() {
		return size;
	}
}
	
