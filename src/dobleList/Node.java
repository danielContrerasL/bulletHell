package dobleList;

/**
 * 
 * @author Daniel Felipe Contreras Lopez
 * @author daniel.contreras@uptc.edu.co
 * @author cldfrkr@hotmail.com
 * @param <T>
 */
public class Node<T> {
	protected Node<T> next;
	protected T info;

	/**
	 * Crea un nodo. Con un nodo siguiente nulo
	 * 
	 * @param info
	 *            Tipo T
	 */
	public Node(T info) {
		this.info = info;
		this.next = null;
	}

	/**
	 * Crea un nodo. Con un nodo sigueinte existente
	 * 
	 * @param info
	 *            Tipo T
	 * @param next
	 *            Tipo Node T, ya existente
	 */
	public Node(T info, Node<T> next) {
		this.next = next;
		this.info = info;
	}

	/**
	 * Crea un nodo vacio
	 */
	public Node() {
		this.next = null;
		this.info = null;
	}

	/**
	 * Retorna el nodo siguiente al nodo actual
	 * 
	 * @return Node T
	 */
	public synchronized Node<T> getNext() {
		return this.next;
	}

	/**
	 * Agrega un nodo siguiente al nodo actual
	 * 
	 * @param next
	 *            Tipo Node T
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * Retorna el nodo actual
	 * 
	 * @return Tipo T
	 */
	public synchronized T getInfo() {
		return this.info;
	}

}
