package skladICo;

import java.util.Map;

public class KachestvenDostavchik extends Dostavchik {
	private static final double PROCENT_ZARADI_KACHESTVOTO = 0.3;

	public KachestvenDostavchik(String name) {
		super(name);
	}

	@Override
	double smetniSiKomisionnata(double cenaNaStokata) {
		double komisionna = PROCENT_ZARADI_KACHESTVOTO * cenaNaStokata;
		return komisionna;
	}

	@Override
	double promeniCenata(double cenaNaStokata) {
		cenaNaStokata = cenaNaStokata - (cenaNaStokata * PROCENT_ZARADI_KACHESTVOTO);
		return cenaNaStokata;
	}

	@Override
	Map<Stoka, Integer> propusniStoki(Map<Stoka, Integer> stoki) {
		return stoki;
	}
}
