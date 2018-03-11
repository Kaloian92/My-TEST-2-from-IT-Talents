package skladICo;

import demo.Demo;

public enum Stoka {
	Stoka1(3.29), Stoka2(5.99), Stoka3(6.30), Stoka4(4.60), Stoka5(5.36), Stoka6(3.60), Stoka7(8.96), Stoka8(
			5.55), Stoka9(6.69);

	private static final double PROCENT_PECHALBA_PRI_PRODAJBA = 0.50;
	private double cenaDostavka;
	private double cenaProdajba;

	private Stoka(double cenaDostavka) {
		this.cenaDostavka = cenaDostavka;
		this.cenaProdajba = this.cenaDostavka + (this.cenaDostavka * PROCENT_PECHALBA_PRI_PRODAJBA);
	}

	public static Stoka getRandomStoka() {
		return Stoka.values()[(Demo.randomNumber(Stoka.values().length))];
	}

	public double getCenaDostavka() {
		return this.cenaDostavka;
	}

	public double getCenaProdajba() {
		return this.cenaProdajba;
	}

}
