package skladICo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Rabotnik extends Obshto {
	// tuk money oznachava zaplatata na rabotnika

	private Map<Stoka, Integer> prietiStoki = new HashMap<>();
	private Map<Stoka, Integer> otpisaniStoki = new HashMap<>();

	public Rabotnik(String name, double money) {
		super(name, money);
		System.out.println(this);
		for (Stoka stoka : Stoka.values()) {
			this.prietiStoki.put(stoka, 0);
			this.otpisaniStoki.put(stoka, 0);
		}
	}

	// Methods
	@Override
	public String toString() {
		return String.format("Rabotnik type - %s ,	Name - %s priel e %d stoki i e otpisal %d stoki",
				this.getClass().getSimpleName(), this.getName(), this.broiPrieti(), this.broiOtpisani());
	}

	int broiPrieti() {
		int prietiStoki = 0;
		for (Entry<Stoka, Integer> entry : this.prietiStoki.entrySet()) {
			prietiStoki += entry.getValue();
		}
		return prietiStoki;
	}

	int broiOtpisani() {
		int otpisaniStoki = 0;
		for (Entry<Stoka, Integer> entry : this.otpisaniStoki.entrySet()) {
			otpisaniStoki += entry.getValue();
		}
		return otpisaniStoki;
	}

	void addPrietaStoka(Stoka stoka, int nalichnost) {
		if (stoka != null) {
			if (this.prietiStoki.containsKey(stoka)) {
				this.prietiStoki.put(stoka, this.prietiStoki.get(stoka) + nalichnost);
			} else {
				this.prietiStoki.put(stoka, nalichnost);
			}
		}
	}

	void addOtpisnaStoka(Stoka stoka, int nalichnost) {
		if (stoka != null) {
			if (this.otpisaniStoki.containsKey(stoka)) {
				this.otpisaniStoki.put(stoka, this.otpisaniStoki.get(stoka) + nalichnost);
			} else {
				this.otpisaniStoki.put(stoka, nalichnost);
			}
		}
	}

	void podrediStokite(Map<Stoka, Integer> stoki, Sklad sklad) {
		stoki = this.propusniStoki(stoki);
		for (Stoka stoka : stoki.keySet()) {
			this.prietiStoki.put(stoka, (this.prietiStoki.get(stoka) + stoki.get(stoka)));
		}
		sklad.podrediVSklada(stoki);
	}

	void otpishiStokite(Map<Stoka, Integer> stoki, Sklad sklad) {
		for (Stoka stoka : stoki.keySet()) {
			this.otpisaniStoki.put(stoka, (this.otpisaniStoki.get(stoka) + stoki.get(stoka)));
		}
		sklad.otpishiOtSklada(stoki);
	}

	Map<Stoka, Integer> propusniStoki(Map<Stoka, Integer> stoki) {
		return stoki;
	}

}
