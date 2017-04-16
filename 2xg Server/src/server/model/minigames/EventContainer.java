package server.model.minigames;

/**
 * Holds extra data for an event (for example the tick time etc).
 * 
 * @author Graham
 * 
 */
public class EventContainer {

	/**
	 * The actual event.
	 */
	private Event event;

	/**
	 * A flag which specifies if the event is running;
	 */
	private boolean isRunning;

	/**
	 * When this event was last run.
	 */
	private long lastRun;

	/**
	 * The tick time in milliseconds.
	 */
	private int tick;

	/**
	 * The owner.
	*/
	//private Object owner;
	
	/**
	 * The event container.
	 * 
	 * @param evt
	 * @param tick
	 */
	protected EventContainer(Event evt, int tick) {
		//this.owner = owner;
		this.tick = tick;
		this.event = evt;
		this.isRunning = true;
		this.lastRun = System.currentTimeMillis();
		// can be changed to 0 if you want events to run straight away
	}

	/**
	 * Executes the event!
	 */
	public void execute() {
		this.lastRun = System.currentTimeMillis();
		this.event.execute(this);
	}

	/**
	 * Gets the last run time.
	 * 
	 * @return
	 */
	public long getLastRun() {
		return this.lastRun;
	}

	/**
	 * Returns the tick time.
	 * 
	 * @return
	 */
	public int getTick() {
		return this.tick;
	}

	/**
	 * Gets the owner of the object.
	 * 
	 * @return
	 */
	//public Object getOwner() {
	//	return this.owner;
	//}

	
	/**
	 * Returns the is running flag.
	 * 
	 * @return
	 */
	public boolean isRunning() {
		return this.isRunning;
	}

	/**
	 * Stops this event.
	 */
	public void stop() {
		this.isRunning = false;
		//this.event.stop();
	}

}
