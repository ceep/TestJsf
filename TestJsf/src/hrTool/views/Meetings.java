package hrTool.views;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ViewScoped
@ManagedBean(name="meetingsView")
public class Meetings implements Serializable {


	private ScheduleModel lazyEventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	
	Map <Integer, DefaultScheduleEvent> meetings;

	public Map getMeetings() {
		return meetings;
	}

	public void setMeetings(Map meetings) {
		this.meetings = meetings;
	}

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}



	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	@PostConstruct
	public void init() {
		
		meetings = new HashMap<Integer, DefaultScheduleEvent>();
		
		
		lazyEventModel = new LazyScheduleModel() {

			@Override
			public void loadEvents(Date start, Date end) {
				// need to get the list of meetings in order to populate the meetings hashtable
				// meetingId + DefaultScheduleEvent
				Date random = getMeetingBetween(start, end);
				meetings.put(1, new DefaultScheduleEvent("Lazy Event 1", random, increment(random)));
				random = getMeetingBetween(start, end);
				meetings.put(2, new DefaultScheduleEvent("Lazy Event 2", random, increment(random)));
				random = getMeetingBetween(start, end);
				meetings.put(3, new DefaultScheduleEvent("Lazy Event 3", random, increment(random)));
				random = getMeetingBetween(start, end);
				meetings.put(4, new DefaultScheduleEvent("Lazy Event 4", random, increment(random)));
				random = getMeetingBetween(start, end);
				meetings.put(5, new DefaultScheduleEvent("Lazy Event 5", random, increment(random)));

				Iterator<DefaultScheduleEvent> it = meetings.values().iterator();
				
				while (it.hasNext()) {
					 DefaultScheduleEvent val = it.next();
					  addEvent(val);
				}
				   
				
				

			}   
		};
	}

	public Date increment (Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, date.getHours()+5);
		return cal.getTime();
	}


	public Date getMeetingBetween(Date start, Date end) {
		Calendar date = Calendar.getInstance();
		date.setTime(start);
		date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month
		date.add(Calendar.HOUR, ((int) (Math.random()*24)) + 1);
		return date.getTime();
	}


	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();

	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}


	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
		System.out.println("Event resized with new start Date:" + event.getScheduleEvent().getStartDate().toGMTString() + 
				", end date:" + event.getScheduleEvent().getEndDate().toGMTString() +
				" and event id: " + event.getScheduleEvent().getId());
		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		event.getScheduleEvent().getId();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
		System.out.println("Event resized with new start Date:" + event.getScheduleEvent().getStartDate().toGMTString() + 
				", end date:" + event.getScheduleEvent().getEndDate().toGMTString() +
				" and event id: " + event.getScheduleEvent().getId());
		
		Iterator<DefaultScheduleEvent> it = meetings.values().iterator();
		
		DefaultScheduleEvent val = null;
		
		while (it.hasNext()) {
			 val = it.next();
			 if(event.getScheduleEvent().getId().equals(val.getId()))
				 break;
		}
		
		if(val!=null){
			for (Entry<Integer, DefaultScheduleEvent> entry : meetings.entrySet()) {
	            if (entry.getValue().equals(val)) {
	            	System.out.println("The meetingId is: " + entry.getKey());
	            }
	        }
		}
		
		
		
		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}


}
