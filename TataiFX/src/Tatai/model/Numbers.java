package Tatai.model;

/**
 * Enum to model all the Maori numbers
 *
 */
public enum Numbers {

	One("tahi"), Two("rua"), Three("toru"), Four("whaa"), Five("rima"), Six("ono"), Seven("whitu"), Eight("waru"),
	Nine("iwa"), Ten("tekau");

	private final String number;

	private Numbers(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public int getIntegerValue() {
		switch (number) {
		case "tahi":
			return 1;
		case "rua":
			return 2;
		case "toru":
			return 3;
		case "whaa":
			return 4;
		case "rima":
			return 5;
		case "ono":
			return 6;
		case "whitu":
			return 7;
		case "waru":
			return 8;
		case "iwa":
			return 9;
		case "tekau":
			return 10;
		default:
			return 0;
		}
	}
}