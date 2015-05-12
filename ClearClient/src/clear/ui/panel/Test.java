package clear.ui.panel;

import java.util.ArrayList;

public class Test {
	ArrayList<String> a = new ArrayList<String>() ;
	ArrayList<String> b = new ArrayList<String>() ;
	int[] indexes ;
	public Test(){
		a.add("1");
		a.add("2");
		a.add("3");
		a.add("4");
		a.add("5");
		a.add("6");
		b.add("2");
		b.add("4");
		b.add("6");
		indexes = new int[b.size()];
	}
	
	public void test(){
		for(int i = 0 ; i< a.size() ; i++){
			for( int  j = 0 ; j<b.size() ; j++){
				if(b.get(j).equals(a.get(i))){
					indexes[j] = i ;
				}
			}
			
		}
		for(int i : indexes){
			System.out.println(i);
		}
	}
	
	public static void main(String[] args){
		new Test().test();
	}

}
