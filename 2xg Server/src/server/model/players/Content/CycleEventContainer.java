package server.model.players.Content;

/**
 * The wrapper for our event
 * 
 * @author Stuart <RogueX>
 * 
 */
public class CycleEventContainer {

	/**
	 * Event owner
	 */
	private Object owner;
	
	/**
	 * Is the event running or not
	 */
	private boolean isRunning;
	
	/**
	 * The amount of cycles per event execution
	 */	
	private int tick;
	
	/**
	 * The actual event
	 */
	private CycleEvent event;
	
	/**
	 * The current amount of cycles passed
	 */
	private int cyclesPassed;

	/**
	 * Sets the event containers details
	 * 
	 * @param owner
	 *            , the owner of the event
	 * @param event
	 *            , the actual event to run
	 * @param tick
	 *            , the cycles between execution of the event
	 */
	public CycleEventContainer(Object owner, CycleEvent event, int tick) {
		this.owner = owner;
		this.event = event;
		this.isRunning = true;
		this.cyclesPassed = 0;
		this.tick = tick;
	}

	/**
	 * Execute the contents of the event
	 */
	public void execute() {
		event.execute(this);
	}

	/**
	 * Stop the event from running
	 */
	public void stop() {
		isRunning = false;
		event.stop();
	}

	/**
	 * Does the event need to be ran?
	 * 
	 * @return true yes false no
	 */
	public boolean needsExecution() {
		if (++this.cyclesPassed >= tick) {
			this.cyclesPassed = 0;
			return true;
		}
		return false;
	}

	/**
	 * Returns the owner of the event
	 * 
	 * @return
	 */
	public Object getOwner() {
		return owner;
	}

	/**
	 * Is the event running?
	 * 
	 * @return true yes false no
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * Set the amount of cycles between the execution
	 * 
	 * @param tick
	 */
	public void setTick(int tick) {
		this.tick = tick;
	}

}