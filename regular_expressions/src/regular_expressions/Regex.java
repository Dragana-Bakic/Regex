package regular_expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	// Regularni izrazi (regex) su obrasci za pretragu stringova, odnosno sluze za
	// manipulisanje i obradu nizova znakova.
	// Regularni izraz se može koristiti za pretragu, uređivanje i manipulaciju
	// tekstom.

	public static final String NEKI_TEKST = "Ovo je samo deo ";

	public static void main(String[] args) {

		// Neki od primera upotrebe regex-a u Javi:

		// 1. Provera da li string odgovara određenom obrascu

		String hw = "Hello, World!";
		System.out.println(hw.matches("Hello, .*")); // . - bilo koji karakter
														// * - pojavljuje se 0 ili vise puta
														// boolean tipa - true/false

		// 2. Pronalaženje svih pojava određenog obrasca u stringu

		System.out.println("");

		String tekst = "Pronalaženje svih pojava određenog obrasca u stringu. Kada se nakon pojava nalazi tacka - pojava.";
		Pattern p = Pattern.compile("pojava\\.");
		Matcher m = p.matcher(tekst);
		while (m.find()) {
			System.out.println("Pronađeno: " + m.group());
		}

		// 3. Deljenje stringa na delove na osnovu određenog obrasca:

		System.out.println("");

		String[] parts = NEKI_TEKST.split(" ");
		for (String part : parts) {
			System.out.println(part);
		}

		// 4. Zamena određenog obrasca u stringu:

		System.out.println("");

		List<String> lista = new ArrayList<>();
		List<String> sredjenalista = new ArrayList<>();
		List<String> listabezduplikata = new ArrayList<>();
		List<String> eurolista = new ArrayList<>();

		lista.add("123e");
		lista.add("2.8e");
		lista.add("79.7e");
		lista.add("2.8e");
		lista.add("5..509E");

		// U Javi, regex se implementira kroz klasu java.util.regex.Pattern koja pruža
		// metode za kompiliranje, uparivanje i manipulaciju regularnim izrazima.
		// Takođe, klasa java.util.regex.Matcher se koristi za uparivanje i manipulaciju
		// nizovima znakova na osnovu kompiliranog obrasca.

		Pattern sredi = Pattern.compile("\\.{2}"); // dve tacke zamenjujemo
		for (String s1 : lista) {
			Matcher m2 = sredi.matcher(s1);
			sredjenalista.add(m2.replaceAll(",")); // sa zarezom
		}

		Pattern duplo = Pattern.compile("\\b(\\w+)?(\\.\\w+)?(\\,\\w+)?\\b");

		for (String s2 : sredjenalista) {
			Matcher m1 = duplo.matcher(s2);
			if (m1.find()) {
				String d = m1.group();
				if (!listabezduplikata.contains(d)) {
					listabezduplikata.add(d);
				}
			}
		}

		Pattern euro = Pattern.compile("[e]", Pattern.CASE_INSENSITIVE);
		for (String s3 : listabezduplikata) {
			Matcher matcher2 = euro.matcher(s3);
			eurolista.add(matcher2.replaceAll("\u20AC"));
		}

		System.out.println("Lista: " + lista);
		System.out.println("Sredjena Lista: " + sredjenalista);
		System.out.println("Lista bez duplikata: " + listabezduplikata);
		System.out.println("Lista bez duplikata i sa zamenom valute: " + eurolista);
	}
}
