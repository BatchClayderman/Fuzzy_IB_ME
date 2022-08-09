import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

public class Setup {
	public static PARS setup(int n){
		int rBits = 160;
		int qBits = 512;
		TypeACurveGenerator pg = new TypeACurveGenerator(rBits, qBits);
		PairingParameters pairingParameters = pg.generate();
		Pairing pairing = PairingFactory.getPairing(pairingParameters);

		PARS pars = new PARS();
		pars.setPairing(pairing);
		pars.setG(pairing.getG1());
		pars.setGT(pairing.getGT());
		pars.setZp(pairing.getZr());
		Element g = pars.getG().newRandomElement().duplicate().getImmutable();
		Element alpha = pars.getZp().newRandomElement().getImmutable();
		Element beta = pars.getZp().newRandomElement().getImmutable();
		Element[] theta = {
				pars.getZp().newRandomElement().getImmutable(), 
				pars.getZp().newRandomElement().getImmutable(), 
				pars.getZp().newRandomElement().getImmutable(), 
				pars.getZp().newRandomElement().getImmutable()
		}; // revised
		Element g1 = g.powZn(alpha).getImmutable();
		Element g2 = pars.getG().newRandomElement().duplicate().getImmutable();
		Element[] eta = {
			g.powZn(theta[0]).getImmutable(), 
			g.powZn(theta[1]).getImmutable(), 
			g.powZn(theta[2]).getImmutable(), 
			g.powZn(theta[3]).getImmutable(),  
		}; // revised
		Element Y = pairing.pairing(g2, g).duplicate().powZn(beta).getImmutable();
		Element[] t = new Element[n + 1];
		Element[] l = new Element[n + 1];
		for (int i = 0; i < n + 1; ++i) {
			t[i] = pars.getG().newRandomElement().duplicate().getImmutable();
			l[i] = pars.getG().newRandomElement().duplicate().getImmutable();
		}
		pars.set_g(g);
		pars.setG1(g1);
		pars.setG2(g2);
		pars.setY(Y);
		pars.setT(t);
		pars.setL(l);
		pars.setEta(eta); // revised
		pars.setTheta(theta); // revised
		pars.setAlpha(alpha);
		pars.setBeta(beta);
		return pars;
	}
}
