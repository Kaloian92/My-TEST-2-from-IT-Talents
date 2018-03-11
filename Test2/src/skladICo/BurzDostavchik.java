package skladICo;

import java.util.Map;
import java.util.Map.Entry;

public class BurzDostavchik extends Dostavchik {
	private static final double PROCENT_SHANS_BURZIQ_DA_SBURKA = 0.1;

	public BurzDostavchik(String name) {
		super(name);
	}

	@Override
	Map<Stoka, Integer> propusniStoki(Map<Stoka, Integer> stoki) {
		for (Entry<Stoka, Integer> entry : stoki.entrySet()) {
			entry.setValue((int) (entry.getValue() - entry.getValue() * PROCENT_SHANS_BURZIQ_DA_SBURKA));
		}
		return stoki;
	}

}
