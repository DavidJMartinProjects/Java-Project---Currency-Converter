import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

	private BufferedReader reader;
	private URL url;
	private Scanner myScanner = new Scanner(System.in);
	private int choice;
	private double amount;
	private String to, from;

	enum currency {
		EUR, USD;
	}

	public Double getRate(String from, String to, double amount) throws IOException {
		try {
			url = new URL("http://finance.yahoo.com/d/quotes.csv?f=l1&s=" + from + to + "=X");
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = reader.readLine();

			if (line.length() > 0) {
				return Double.parseDouble(line) * amount;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		} finally {
			reader.close();
		}
		return null;
	}

	public void menu() {
		System.out.println("Select the Currency you wish to convert from : ");
		System.out.println("1. Euro");
		System.out.println("2. USD");
		choice = myScanner.nextInt();
		if (choice == 1)
			from = currency.EUR.toString();
		else if (choice == 2) {
			from = currency.USD.toString();
		}

		System.out.println("Select the Currency you wish to convert to : ");
		System.out.println("1. Euro");
		System.out.println("2. USD");
		choice = myScanner.nextInt();
		if (choice == 1)
			to = currency.EUR.toString();
		else if (choice == 2) {
			to = currency.USD.toString();
		}

		System.out.println("Enter the amount you wish to convert : ");
		amount = myScanner.nextDouble();

		try {
			System.out.println(amount + " " + from + " to " + to + " is : " + getRate(from, to, amount));
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		CurrencyConverter converter = new CurrencyConverter();
		converter.menu();
	}
}
