package clear.ui.test;

import clear.ui.component.MyPanel;
import enupackage.PanelType;

public class TestMyPanel extends MyPanel{
	public TestMyPanel(){
		super(PanelType.Model);
	}
	public static void main(String[] args){
		TestDrive td = new TestDrive();
		td.test(new TestMyPanel());
	}
}
