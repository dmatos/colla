package Drivers;



public interface Connection {
	
	public void configure(String text);
	public void connect();
	public String[] next();
	
}
