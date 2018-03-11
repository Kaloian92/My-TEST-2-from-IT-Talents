package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import skladICo.BurzDostavchik;
import skladICo.Distributor;
import skladICo.Dostavchik;
import skladICo.KachestvenDostavchik;
import skladICo.NeopitenRabotnik;
import skladICo.Rabotnik;
import skladICo.Sklad;
import skladICo.Stoka;

public class Demo {
	public static final int randomNumber(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}

	public static final int randomNumber(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min) + min;
	}

	public static final boolean isStringValid(String string) {
		return ((string != null) && (string.trim().length() > 0));
	}

	public static String giveRandomName() {
		String[] purviImena = {
				"Kaloian", "Miroslav", "Bogomil", "Georgi", "Ivan", "Martina", "Nikolai", "Aleksandur", "Simeon",
				"Mariq", "Iordan", "Vasil", "Bianka", "Spas" };
		String[] vtoriImena = {
				"Pavlov", "Markov", "Vladimirov", "Georgiev", "Ivanov", "Petrova", "Nikolov", "Aleksandrov", "Simeonov",
				"Zlatarski", "Iordanova", "Vasilev", "Karaivanov", "Spasov" };
		return purviImena[(int) (Math.random() * purviImena.length)] + " "
				+ vtoriImena[(int) (Math.random() * vtoriImena.length)];
	}

	private static Map<Stoka, Integer> suzdavaneNaRandomSpisukStoki(int a, int b) {
		Map<Stoka, Integer> stoki = new HashMap<Stoka, Integer>();
		for (int i = 0; i < 10; i++) {
			stoki.put(Stoka.getRandomStoka(), randomNumber(a, b));
		}
		return stoki;
	}

	public static void main(String[] args) {

		// suzdavame skladovete
		ArrayList<Sklad> skladove = new ArrayList<Sklad>();
		for (int i = 0; i < 3; i++) {
			String name = giveRandomName();
			Sklad s = new Sklad("Sklada na " + name, "Addressa na " + name);
			skladove.add(s);
		}
		System.out.println('\n');

		ArrayList<Dostavchik> dostavchici = new ArrayList<Dostavchik>();
		ArrayList<Rabotnik> rabotnici = new ArrayList<Rabotnik>();
		ArrayList<Distributor> distributori = new ArrayList<Distributor>();
		for (Sklad sklad : skladove) {
			// suzdavame dostavchuci
			for (int i = 0; i < 5; i++) {
				int kakuvShteE = randomNumber(2);
				Dostavchik dos = null;
				if (kakuvShteE == 1) {
					dos = new KachestvenDostavchik(giveRandomName());
				} else {
					dos = new BurzDostavchik(giveRandomName());
				}
				dostavchici.add(dos);
				sklad.addDostavchik(dos);
			}
			System.out.println('\n');

			// suzdavame rabotnicite
			for (int i = 0; i < 4; i++) {
				int kakuvShteE = randomNumber(2);
				Rabotnik rab = null;
				if (kakuvShteE == 1) {
					rab = new Rabotnik(giveRandomName(), randomNumber(1200, 1900));
				} else {
					rab = new NeopitenRabotnik(giveRandomName(), randomNumber(700, 1200));
				}
				rabotnici.add(rab);
				sklad.addRabotnik(rab);
			}
			System.out.println('\n');

			// suzdavame distributori i im zadavame magazin
			for (int i = 0; i < 5; i++) {
				Distributor dis = new Distributor(giveRandomName());
				distributori.add(dis);
				sklad.addDistributor(dis);
			}
			System.out.println('\n' + "" + sklad + " poluchi svoite slujiteli" + '\n');

			// zarejdane na skladovete
			Map<Stoka, Integer> stoki = new HashMap<Stoka, Integer>();
			for (Stoka stoka : Stoka.values()) {
				stoki.put(stoka, randomNumber(50, 100));
			}
			sklad.zarediSklada(stoki);
			System.out.println("Sklada e zareden" + '\n' + '\n');
		}

		// zadavame magazin na vseki distributor
		for (Distributor distributor : distributori) {
			distributor.setMagazin(Distributor.Magazin.getRandomMagazin());
			System.out.println(distributor.getName() + " stana distributor za " + distributor.getMagazin());
		}
		System.out.println('\n');

		// dostavkite do magazinite
		for (Sklad sklad : skladove) {
			Map<Stoka, Integer> stoki = suzdavaneNaRandomSpisukStoki(10, 15);
			sklad.dosaviStoka(stoki);
		}
		System.out.println('\n');

		// statistikite na skladovete
		for (Sklad sklad : skladove) {
			System.out.println("Statistikite na " + sklad + " sa:");
			sklad.naiProdavanaStoka();
			System.out.println();
			sklad.naiSlabiRabotnici();
			System.out.println();
			sklad.deficitniStoki();
			System.out.println();
			sklad.statistikaProdajbi();
			System.out.println();
			sklad.statistikaPokupki();
			System.out.println();
			sklad.parichenBalans();
			System.out.println('\n');
		}

	}

}
