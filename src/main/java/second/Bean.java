package second;

public class Bean {

	// Instance Variables.
	private String name;
	private int time;
	private double price;

	// Constructors.
	public Bean() {

	}

	public Bean(String name) {
		this.name = name;
	}

	public Bean(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Bean(String name, double price, int takes) {
		this.name = name;
		this.price = price;
		this.time = takes;
	}

	// Getters And Setters.
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", time=" + time + ", price=" + price + "]";
	}

}
