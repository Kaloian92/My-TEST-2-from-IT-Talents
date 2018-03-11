package skladICo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import demo.Demo;

public class Sklad extends Obshto {
	private static final int START_MONEY = 10000;
	// tuk money predstavlqva oborota

	private String address;
	private Set<Dostavchik> dostavchici = new HashSet<Dostavchik>();
	private Set<Rabotnik> rabotnici = new HashSet<Rabotnik>();
	private Set<Distributor> distributori = new HashSet<Distributor>();
	private Map<Stoka, Integer> stoki = new HashMap<Stoka, Integer>();
	private Map<Stoka, Integer> broiProdajbiNaStokite = new HashMap<Stoka, Integer>();

	public Sklad(String name, String address) {
		super(name, START_MONEY);
		setAddress(address);
		System.out.println(this);
		for (Stoka stoka : Stoka.values()) {
			this.stoki.put(stoka, 0);
			this.broiProdajbiNaStokite.put(stoka, 0);
		}
	}

	// Methods

	@Override
	public String toString() {
		return String.format("Sklad: %s, namirasht se na adres: %s, s oborot v momenta: %.2f ", this.getName(),
				this.address, this.getMoney());
	}

	public void zarediSklada(Map<Stoka, Integer> stoki) {
		if (stoki != null) {
			Dostavchik dos = giveRandomDostavchik();
			double cenaZaDostavkata = dos.buyStoki(stoki);
			this.setMoney(this.getMoney() - cenaZaDostavkata);
			Rabotnik rab = giveRandomRabotnik();
			rab.podrediStokite(stoki, this);
		} else {
			System.out.println("Nevalidni stoki davash");
		}
	}

	public void dosaviStoka(Map<Stoka, Integer> stoki) {
		if (stoki != null) {
			boolean imaTolkovaStoki = true;
			for (Stoka stoka : stoki.keySet()) {
				if (stoki.get(stoka) > this.stoki.get(stoka)) {
					imaTolkovaStoki = false;
					break;
				}
			}
			if (imaTolkovaStoki) {
				Distributor dis = giveRandomDistributor();
				double pariOtDistribuciqta = dis.dostaviDoMagazina(stoki);
				this.setMoney(this.getMoney() + pariOtDistribuciqta);
				Rabotnik rab = giveRandomRabotnik();
				rab.otpishiStokite(stoki, this);
			} else {
				System.out.println("Nqma tolkova stoki v sklada");
			}
		} else {
			System.out.println("Nevalidni stoki davash");
		}
	}

	public void naiProdavanaStoka() {
		System.out.println("Pette nai - prodavani stoki sa:");
		TreeMap<Integer, Stoka> broiProdajbi = new TreeMap<>((s1, s2) -> s2 - s1 < 0 ? -1 : 1);
		for (Entry<Stoka, Integer> entry : this.broiProdajbiNaStokite.entrySet()) {
			broiProdajbi.put(entry.getValue(), entry.getKey());
		}
		int count = 0;
		for (Entry<Integer, Stoka> entry : broiProdajbi.entrySet()) {
			if (++count <= 5) {
				System.out.println("Ot " + entry.getValue() + " sa prodadeni " + entry.getKey() + " broiki");
			} else {
				break;
			}
		}
	}

	public void naiSlabiRabotnici() {
		System.out.println("Trimata nai - slabi rabotnici sa:");
		TreeSet<Rabotnik> rabotniks = new TreeSet<>(new RabotniciPoRabotaComparator());
		rabotniks.addAll(this.rabotnici);
		int count = 0;
		for (Rabotnik rabotnik : rabotniks) {
			if (++count <= 3) {
				System.out.println(rabotnik);
			} else {
				break;
			}
		}
	}

	public void deficitniStoki() {
		System.out.println("Deficitnite stoki sa: ");
		for (Entry<Stoka, Integer> entry : this.stoki.entrySet()) {
			if (entry.getValue() < 10) {
				System.out.println(entry.getKey() + " e deficitna, ot neq ima " + entry.getValue() + " broiki");
			}
		}
	}

	public void statistikaProdajbi() {
		System.out.println("Distributorite sa :");
		TreeSet<Distributor> distributors = new TreeSet<Distributor>(
				(d1, d2) -> d2.getMoney() - d1.getMoney() < 0 ? -1 : 1);
		distributors.addAll(this.distributori);
		for (Distributor distributor : distributors) {
			System.out.println(distributor);
		}
	}

	public void statistikaPokupki() {
		System.out.println("Dostavchicite sa :");
		TreeSet<Dostavchik> dostavchiks = new TreeSet<Dostavchik>(
				(d1, d2) -> d2.getMoney() - d1.getMoney() < 0 ? -1 : 1);
		dostavchiks.addAll(this.dostavchici);
		for (Dostavchik dostavchik : dostavchiks) {
			System.out.println(dostavchik);
		}
	}

	public void parichenBalans() {
		System.out.println(String.format("Sklada obshto ot pokupkite i prodajbite e izkaral %.2f leva",
				this.getMoney() - START_MONEY));
	}

	public void addDostavchik(Dostavchik dos) {
		if (dos != null) {
			this.dostavchici.add(dos);
		} else {
			System.out.println("Nevaliden dostavchik");
		}
	}

	public void addRabotnik(Rabotnik rab) {
		if (rab != null) {
			this.rabotnici.add(rab);
		} else {
			System.out.println("Nevaliden rabotnik");
		}
	}

	public void addDistributor(Distributor dis) {
		if (dis != null) {
			this.distributori.add(dis);
		} else {
			System.out.println("Nevaliden distributor");
		}
	}

	private Dostavchik giveRandomDostavchik() {
		ArrayList<Dostavchik> dostavchiks = new ArrayList<Dostavchik>(this.dostavchici);
		return dostavchiks.get(Demo.randomNumber(dostavchiks.size()));
	}

	private Rabotnik giveRandomRabotnik() {
		ArrayList<Rabotnik> rabotniks = new ArrayList<Rabotnik>(this.rabotnici);
		return rabotniks.get(Demo.randomNumber(rabotniks.size()));
	}

	private Distributor giveRandomDistributor() {
		ArrayList<Distributor> distributors = new ArrayList<Distributor>(this.distributori);
		return distributors.get(Demo.randomNumber(distributors.size()));
	}

	void podrediVSklada(Map<Stoka, Integer> stoki) {
		for (Stoka stoka : stoki.keySet()) {
			this.stoki.put(stoka, (this.stoki.get(stoka) + stoki.get(stoka)));
		}
	}

	void otpishiOtSklada(Map<Stoka, Integer> stoki) {
		for (Stoka stoka : stoki.keySet()) {
			this.broiProdajbiNaStokite.put(stoka, (this.broiProdajbiNaStokite.get(stoka) + stoki.get(stoka)));
			this.stoki.put(stoka, (this.stoki.get(stoka) - stoki.get(stoka)));
		}
	}

	// Setters
	private void setAddress(String address) {
		if (Demo.isStringValid(address)) {
			this.address = address;
		} else {
			System.out.println("Nevaliden address");
		}
	}

	// Getters
}
