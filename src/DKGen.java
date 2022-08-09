import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;


public class DKGen {
	public static Element[][] dKGen(int d, PARS pars, Element[] S_B, Element[] P_A) {
		Element gamma = pars.getZp().newRandomElement().getImmutable();
		Polynomial f = Utils.newRandomPolynomial(d, pars.getAlpha().duplicate(), pars);
		Polynomial h = Utils.newRandomPolynomial(d, gamma.duplicate(), pars);
		Polynomial q_prime = Utils.newRandomPolynomial(d, pars.getBeta().duplicate(), pars);
		Element G_ID = pars.getG().newRandomElement().duplicate().getImmutable();
		/*
		Element[] D_i = new Element[S_B.length];
		Element[] d_i = new Element[S_B.length];
		for (int i = 0; i < S_B.length; i++) {
			Element k_i = pars.getZp().newRandomElement().getImmutable();
			d_i[i] = pars.get_g().duplicate().powZn(k_i).getImmutable();
			D_i[i] = pars.getG2().duplicate().powZn(f.evaluate(S_B[i])).mul(G_ID.powZn(h.evaluate(S_B[i]))).mul(Utils.T(S_B[i], pars).powZn(k_i)).getImmutable();
		}
		Element[] D_i_prime = new Element[P_A.length];
		Element[] d_i_prime = new Element[P_A.length];
		for (int i = 0; i < P_A.length; i++) {
			Element r_i_prime = pars.getZp().newRandomElement().getImmutable();
			d_i_prime[i] = pars.get_g().duplicate().powZn(r_i_prime).getImmutable();
			D_i_prime[i] = pars.getG2().duplicate().powZn(q_prime.evaluate(P_A[i]).mul(BigInteger.valueOf(2))).mul(G_ID.powZn(h.evaluate(P_A[i]).negate())).mul(Utils.H(P_A[i], pars).powZn(r_i_prime)).getImmutable();
		}
		return new Element[][]{D_i, d_i, D_i_prime, d_i_prime};
		*/
		/* The following are revised */
		Element Dd[][] = new Element[5][S_B.length], Dd_prime[][] = new Element[5][S_B.length];
		for (int i = 0; i < S_B.length; i++) {
			Element k_i_1 = pars.getZp().newRandomElement().getImmutable(), k_i_2 = pars.getZp().newRandomElement().getImmutable();
			Dd[0][i] = pars.get_g().duplicate().powZn((k_i_1.mul(pars.getTheta()[0].mul(pars.getTheta()[1]))).add(k_i_2.mul(pars.getTheta()[2].mul(pars.getTheta()[3])))).getImmutable();
			Dd[1][i] = pars.getG2().duplicate().powZn(f.evaluate(S_B[i]).negate().mul(pars.getTheta()[1])).mul(G_ID.powZn(h.evaluate(S_B[i]).negate().mul(pars.getTheta()[1]))).mul(Utils.T(S_B[i], pars).powZn(k_i_1.negate().mul(pars.getTheta()[1]))).getImmutable();
			Dd[2][i] = pars.getG2().duplicate().powZn(f.evaluate(S_B[i]).negate().mul(pars.getTheta()[0])).mul(G_ID.powZn(h.evaluate(S_B[i]).negate().mul(pars.getTheta()[0]))).mul(Utils.T(S_B[i], pars).powZn(k_i_1.negate().mul(pars.getTheta()[0]))).getImmutable();
		   	//Dd[3][i] = Utils.T(S_B[i], pars).mul(k_i_2.negate().mul(pars.getTheta()[3].duplicate())).getImmutable();
		   	//Dd[4][i] = Utils.T(S_B[i], pars).mul(k_i_2.negate().mul(pars.getTheta()[2].duplicate())).getImmutable();
		   	Dd[3][i] = k_i_2.negate().mul(pars.getTheta()[3].duplicate()).getImmutable();
		   	Dd[4][i] = k_i_2.negate().mul(pars.getTheta()[2].duplicate()).getImmutable();
		}
		for (int i = 0; i < P_A.length; i++) {
			Element r_i_prime_1 = pars.getZp().newRandomElement().getImmutable(), r_i_prime_2 = pars.getZp().newRandomElement().getImmutable();
			Dd_prime[0][i] = pars.get_g().duplicate().powZn((r_i_prime_1.mul(pars.getTheta()[0].mul(pars.getTheta()[1]))).add(r_i_prime_2.mul(pars.getTheta()[2].mul(pars.getTheta()[3])))).getImmutable();
			Dd_prime[1][i] = pars.getG2().duplicate().powZn(q_prime.evaluate(P_A[i]).mul(2).negate().mul(pars.getTheta()[1])).mul(G_ID.powZn(h.evaluate(S_B[i]).negate().mul(pars.getTheta()[1]))).mul(Utils.H(P_A[i], pars).powZn(r_i_prime_1.negate().mul(pars.getTheta()[1]))).getImmutable();
			Dd_prime[2][i] = pars.getG2().duplicate().powZn(q_prime.evaluate(P_A[i]).mul(2).negate().mul(pars.getTheta()[0])).mul(G_ID.powZn(h.evaluate(S_B[i]).negate().mul(pars.getTheta()[0]))).mul(Utils.H(P_A[i], pars).powZn(r_i_prime_1.negate().mul(pars.getTheta()[0]))).getImmutable();
			//Dd_prime[3][i] = Utils.H(P_A[i], pars).mul(r_i_prime_2.negate().mul(pars.getTheta()[3])).getImmutable();
			//Dd_prime[4][i] = Utils.H(P_A[i], pars).mul(r_i_prime_2.negate().mul(pars.getTheta()[2])).getImmutable();
			Dd_prime[3][i] = r_i_prime_2.negate().mul(pars.getTheta()[3]).getImmutable();
			Dd_prime[4][i] = r_i_prime_2.negate().mul(pars.getTheta()[2]).getImmutable();
		}
		return new Element[][]{Dd[0], Dd[1], Dd[2], Dd[3], Dd[4], Dd_prime[0], Dd_prime[1], Dd_prime[2], Dd_prime[3], Dd_prime[4]};
	}
}
