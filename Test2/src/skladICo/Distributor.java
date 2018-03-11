package skladICo;

import java.util.Map;
import java.util.Map.Entry;

import demo.Demo;

public class Distributor extends Obshto {

	public enum Magazin {
		METRO, LIDL, BILLA, HIT, PENNY, FANTASTICO, KAUFLAND;

		public static Magazin getRandomMagazin() {
			return Magazin.values()[(Demo.randomNumber(Magazin.values().length))];
		}
	}

	// money tuk oznachava pechalba
	private static final double PROCENT_ZA_KOMISIONNATA = 0.20;
	private Magazin magazin;

	public Distributor(String name) {
		super(name, 0);
		System.out.println(this);
	}

	// Methods
	@Override
	public String toString() {
		return String.format("Distributor type - %s,	Name - %s izkaral e %.2f leva", this.getClass().getSimpleName(),
				this.getName(), this.getMoney());
	}

	double dostaviDoMagazina(Map<Stoka, Integer> stoki) {
		System.out.println(this.getName() + " nosi stokata do " + this.magazin);
		double pariOtDistribuciqta = 0;
		for (Entry<Stoka, Integer> entry : stoki.entrySet()) {
			pariOtDistribuciqta += (entry.getKey().getCenaProdajba() * entry.getValue());
		}
		double komisionna = PROCENT_ZA_KOMISIONNATA * pariOtDistribuciqta;
		pariOtDistribuciqta = pariOtDistribuciqta - komisionna;
		this.setMoney(this.getMoney() + komisionna);
		return pariOtDistribuciqta;
	}

	public void setMagazin(Magazin magazin) {
		if (magazin != null) {
			this.magazin = magazin;
		} else {
			System.out.println("Nevaliden magazin");
		}
	}

	public Magazin getMagazin() {
		return this.magazin;
	}

}
