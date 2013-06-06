package app;

import util.Action;
import util.Iters;


public class Program {
	
	public static void main(String [] args) throws NoSuchMethodException, SecurityException{
		
		Action a = Proxifier.of(Action.class, System.out, "println");
		Iters.foreach(new Object[]{4, 5 , 6, 7}, a);
		
	}
	
}
