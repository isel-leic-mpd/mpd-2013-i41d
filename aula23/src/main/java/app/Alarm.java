package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Alarm {

	SortedSet<Long> events = new TreeSet<Long>();
	ArrayList<AlarmListener> listeners = new ArrayList<AlarmListener>();

	public void addEvent(long time){
		events.add(time);
	}
	
	public void addListener(AlarmListener a){
		listeners.add(a);
	}

	public void removeListener(AlarmListener a){
		listeners.remove(a);
	}

	public void notifyListeners(){
		for (AlarmListener l : (List<AlarmListener>) listeners.clone()) {
			l.alert();
		}
	}
	
	public void start(){
		
		Iterator<Long> iter = events.iterator();
		Long instant = iter.hasNext() ? iter.next() : null;
		while(instant != null){
			if(System.currentTimeMillis() >= instant){
				notifyListeners();
				iter.remove();
				instant = iter.hasNext() ? iter.next() : null;
			}
		}	
	}
	
}
