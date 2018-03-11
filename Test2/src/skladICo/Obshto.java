package skladICo;

import demo.Demo;

public abstract class Obshto {

	private String name;
	private double money;

	public Obshto(String name, double money) {
		setName(name);
		setMoney(money);
	}

	private void setName(String name) {
		if (Demo.isStringValid(name)) {
			this.name = name;
		} else {
			System.out.println("Nevalidno ime");
		}
	}

	void setMoney(double money) {
		if (money >= 0) {
			this.money = money;
		} else {
			System.out.println("Nevaliden suma pari");
		}
	}

	public String getName() {
		return this.name;
	}

	public double getMoney() {
		return this.money;
	}

}
