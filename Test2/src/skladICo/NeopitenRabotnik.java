package skladICo;

import java.util.Map;
import java.util.Map.Entry;

import demo.Demo;

public class NeopitenRabotnik extends Rabotnik {
	private static final double PROCENT_DA_PROPUSNE_STOKA = 50;

	public NeopitenRabotnik(String name, double money) {
		super(name, money);
	}

	@Override
	Map<Stoka, Integer> propusniStoki(Map<Stoka, Integer> stoki) {
		for (Entry<Stoka, Integer> entry : stoki.entrySet()) {
			int kolichestvoStoki = entry.getValue();
			for (int i = 0; i < kolichestvoStoki; i++) {
				if (Demo.randomNumber(101) < PROCENT_DA_PROPUSNE_STOKA) { // zaradi 50te procenta shans da propusne stoka
					entry.setValue(entry.getValue() - 1);
				}
			}
		}
		return stoki;
	}

}
