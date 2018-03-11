package skladICo;

import java.util.Map;
import java.util.Map.Entry;

public abstract class Dostavchik extends Obshto {
	// tuk money e pechalbata mu
	private static final double PROCENT_ZA_KOMISIONNATA = 0.15;

	public Dostavchik(String name) {
		super(name, 0);
		System.out.println(this);
	}

	// Methods
	@Override
	public String toString() {
		return String.format("Dostavchik type - %s ,	Name - %s izkaral e %.2f leva", this.getClass().getSimpleName(),
				this.getName(), this.getMoney());
	}

	double buyStoki(Map<Stoka, Integer> stoki) {
		System.out.println(this.getName() + " kupi stokata za sklada");
		double cenaNaStokata = 0;
		stoki = this.propusniStoki(stoki);
		for (Entry<Stoka, Integer> entry : stoki.entrySet()) {
			cenaNaStokata += (entry.getKey().getCenaDostavka() * entry.getValue());
		}
		double komisionna = smetniSiKomisionnata(cenaNaStokata);
		cenaNaStokata = promeniCenata(cenaNaStokata);
		this.setMoney(this.getMoney() + komisionna);
		return cenaNaStokata;
	}

	abstract Map<Stoka, Integer> propusniStoki(Map<Stoka, Integer> stoki);

	double promeniCenata(double cenaNaStokata) {
		cenaNaStokata = cenaNaStokata - (cenaNaStokata * PROCENT_ZA_KOMISIONNATA);
		return cenaNaStokata;
	}

	double smetniSiKomisionnata(double cenaNaStokata) {
		double komisionna = PROCENT_ZA_KOMISIONNATA * cenaNaStokata;
		return komisionna;
	}
}
