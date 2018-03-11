package skladICo;

import java.util.Comparator;

public class RabotniciPoRabotaComparator implements Comparator<Rabotnik> {

	@Override
	public int compare(Rabotnik r1, Rabotnik r2) {
		if ((r1.broiPrieti() + r1.broiOtpisani()) - (r2.broiPrieti() + r2.broiOtpisani()) == 0) {
			return r1.getName().compareTo(r2.getName());
		}
		return (r1.broiPrieti() + r1.broiOtpisani()) - (r2.broiPrieti() + r2.broiOtpisani());
	}

}
