package clear.ui.data;

public class HomeMsg {
	String info;
	String one;
	String two;
	String three;
	public HomeMsg(String info){
		this.info = info;
		String[] temp = info.split(" ") ;
		one=temp[0];
		two =temp[1];
		three =temp[2];
	}
	public String getOne(){
		return one;
	}
	public String getTwo(){
		return two;
	}
	public String getThree(){
		return three;
	}
	public void setThree(String th){
		three = th;
	}
}
