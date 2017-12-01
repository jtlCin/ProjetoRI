package busca;

public class Node {
	String nome;
	private String numDoc;
	private int freq;
	private Node next;
	 
	public Node (String num, int freq){
		this.nome = null;
		this.numDoc = num;
		this.freq = freq;
		next = null;
	}
	public Node (String nome, String num, int freq){
		this.nome = nome;
		this.numDoc = num;
		this.freq = freq;
		next = null;
	}

	public String getNumDoc() {
		return this.numDoc;
	}

	public int getFreq() {
		return this.freq;
	}

	public Node getNext() {
		return this.next;
	}
	
	public boolean hasNext(){
		if(next!=null)return true;
		return false;
	}
	
	public void setNext(Node n){
		if(this.next==null) this.next = n;
		else this.next.setNext(n);
	}
	
	public Node getLast(){
		if(this.next==null){
			return this;
		}
		else return	this.next.getLast();

	}
}
