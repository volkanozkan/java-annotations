package second;

@Food(price = 30.0)
public class Sushi {

	@Time(takes = 30)
	public void prepare() {

	}

	@Time(takes = 20)
	public void send() {

	}

	@Time(takes = 0)
	public void cook() {

	}

}
