package Busca;

public class Node {
	String nome;
	private int numDoc;
	private int freq;
	private Node next;
	private byte freqByte;
	private byte numDocByte;
	 
	public Node (String nome, byte numDoc, byte freq){
		this.nome = nome;
		this.numDocByte = numDoc;
		this.freqByte = freq;
		this.next = null;
	}
	public Node (int num, int freq){
		this.nome = null;
		this.numDoc = num;
		this.freq = freq;
		next = null;
	}
	public Node (String nome, int num, int freq){
		this.nome = nome;
		this.numDoc = num;
		this.freq = freq;
		next = null;
	}
	public Node (byte num, byte freq){
		this.freqByte = freq;
		this.numDocByte = num;
		next = null;
	}
	
	public String getNumDoc() {
		return this.numDoc+"";
	}

	public int getFreq() {
		return this.freq;
	}

	public Node getNext() {
		return this.next;
	}
	
	public byte getFreqByte(){
		return this.freqByte;
	}
	
	public byte getNumDocByte(){
		return this.numDocByte;
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
