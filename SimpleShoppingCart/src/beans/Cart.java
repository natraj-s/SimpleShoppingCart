package beans;

import java.util.ArrayList;

public class Cart {
	
	private ArrayList<String> indexArray;
	
	public Cart(ArrayList<String> indexArray) {
		this.indexArray = indexArray;
	}

	public ArrayList<String> getIndexArray() {
		return indexArray;
	}
	
	public void setIndexArray(ArrayList<String> indexArray) {
		this.indexArray = indexArray;
	}
	
	public int find(String index) {
		return indexArray.indexOf(index);
	}
	
	public void add(String index) {
		indexArray.add(index);
	}
	
}
