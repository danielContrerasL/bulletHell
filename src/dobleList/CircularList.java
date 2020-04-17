package dobleList;


/**
 * 
 * @author Daniel Felipe Contreras Lopez
 * @author daniel.contreras@uptc.edu.co
 * @author cldfrkr@hotmail.com
 * @param <T>
 */
public class CircularList<T> {
	private Node<T> head;
	private Node<T> queue;
	
	/**
	 * Agrega un nodo ya existente al final de la lista
	 * 
	 * @param node
	 *            Nodo Tipo T
	 */
	public void add(Node<T> node) {
		if (this.head != null) {
			this.queue.setNext(node);
			this.queue = this.queue.getNext();
			this.queue.setNext(this.head);
		} else {
			this.queue = this.head = node;
			this.head.setNext(this.queue);
		}
	}

	/**
	 * Agrega un nuevo nodo al final de la lista
	 * 
	 * @param info
	 *            Tipo T
	 */
	public void add(T info) {
		if (this.head != null) {
			this.queue.setNext(new Node<T>(info));
			this.queue = this.queue.getNext();
			this.queue.setNext(this.head);
		} else {
			this.queue = this.head = new Node<T>(info);
			this.head.setNext(this.queue);
		}
	}

	/**
	 * Busca por nodo y regresa Verdadero si exite
	 * 
	 * @param node
	 *            Nodo Tipo T
	 * @return Boolean
	 */
	public boolean search(Node<T> node) {
		Node<T> currentNode = this.head;
		while (currentNode != null) {
			if (currentNode.equals(node)) {
				return true;
			}
			currentNode = currentNode.getNext();
		}
		return false;
	}

	/**
	 * Busca por la informacio y regresa un Nodo si existe
	 * 
	 * @param data
	 *            Tipo T
	 * @return Nodo T
	 * @throws Exception
	 *             element not fount
	 */
	public Node<T> search(T data) throws Exception {
		Node<T> currentNode = this.head;
		while (currentNode != null) {
			if (currentNode.getInfo().equals(data)) {
				return currentNode;
			}
			currentNode = currentNode.getNext();
		}
		throw new Exception("element not fount");
	}

	/**
	 * Borra un Nodo tipo Nodo T
	 * 
	 * @param node
	 *            Nodo T
	 */
	public synchronized void delete(Node<T> node) {
		if (node != this.head) {
			Node<T> currentNode = this.head;
			while (currentNode != queue) {
				if (currentNode.getNext() == node) {
					currentNode.setNext(node.getNext());
					if (node == this.queue){
						queue = currentNode;
					}
					node = null;
					return;
				}
				currentNode = currentNode.getNext();
			}
		} else {
			this.head = this.head.getNext();
		}
	}

	/**
	 * Borra un nodo basado en la informacion
	 * 
	 * @param info
	 *            Tipo T
	 */
	public void delete(T info) {
		if (this.head.getInfo() != info) {
			Node<T> currentNode = this.head;
			while (currentNode.getNext() != null) {
				if (currentNode.getNext().getInfo() == info) {
					currentNode.setNext(currentNode.getNext().getNext());
					return;
				}
				currentNode = currentNode.getNext();
			}
		} else {
			this.head = this.head.getNext();
		}
	}

	/**
	 * Inserta un nodo entre dos nodos
	 * 
	 * @param node
	 *            Nodo Tipo T en la lista
	 * @param newNode
	 *            Nodo Tipo T para insertar
	 */
	public void insert(Node<T> node, Node<T> newNode) {
		if (this.search(node)) {
			newNode.setNext(node.getNext());
			node.setNext(newNode);
		}
	}

	/**
	 * Vacia toda la lista, verdadero para borrar
	 * 
	 * @param clear
	 *            True borra
	 */
	public void clear(boolean clear) {
		if (clear) {
			this.head = null;
		}
	}

	public synchronized Node<T> getHead() {
		return this.head;
	}
	
	public synchronized Node<T> getQueue() {
		return queue;
	}
}
