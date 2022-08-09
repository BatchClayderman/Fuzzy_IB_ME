import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;

public class EKGen {
	public static Element[][] eKGen(int d, PARS pars, Element[] S_A) {
		Polynomial q = Utils.newRandomPolynomial(d, pars.getBeta(), pars);
		Element[] E_i = new Element[S_A.length];
		Element[] e_i = new Element[S_A.length];
		for (int i = 0; i < S_A.length; i++) {
			Element r_i = pars.getZp().newRandomElement().getImmutable();
			e_i[i] = pars.get_g().duplicate().powZn(r_i).getImmutable();
			E_i[i] = pars.getG2().duplicate().powZn(q.evaluate(S_A[i]).mul(pars.getTheta()[0].mul(pars.getTheta()[1]))).mul(Utils.H(S_A[i], pars).powZn(r_i)).getImmutable(); // Revised
		}
		return new Element[][]{E_i, e_i};
	}
}
