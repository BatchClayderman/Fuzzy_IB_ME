import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;

import java.util.ArrayList;
import java.util.List;

public class Enc {
    public static List<Object> enc(int d, PARS pars, Element[] S_A, Element[] P_B, Element[] E_i, Element[] e_i, Element M){
        Element s = pars.getZp().newRandomElement().getImmutable(), s_1 = pars.getZp().newRandomElement().getImmutable(), s_2 = pars.getZp().newRandomElement().getImmutable();
        Element tau = pars.getZp().newRandomElement().getImmutable();
        Element K_s = pars.getPairing().pairing(pars.getG1().duplicate(), pars.getG2().duplicate()).powZn(s).getImmutable();
        Element K_l = pars.getY().duplicate().powZn(s).mul(pars.getPairing().pairing(pars.getG2().duplicate(), pars.get_g().duplicate().powZn(tau.negate()))).getImmutable();
        Element C_0 = M.duplicate().mul(K_s.duplicate()).duplicate().mul(K_l.duplicate()).getImmutable();
        Element C_1 = pars.get_g().duplicate().powZn(s).getImmutable();
        Element[] C_1_i = new Element[P_B.length];
        for (int i = 0; i < P_B.length; i++) {
            //C_1_i[i] = Utils.T(P_B[i], pars).powZn(s).getImmutable();
        	C_1_i[i] = pars.getEta()[0].powZn(s.sub(s_1)).getImmutable(); // Revised
        }
        Element[] C_2_i = new Element[S_A.length];
        for (int i = 0; i < S_A.length; i++) {
            //C_2_i[i] = Utils.H(S_A[i], pars).powZn(s).getImmutable();
        	C_2_i[i] = pars.getEta()[1].powZn(s_1).getImmutable(); // Revised
        }

        Polynomial l = Utils.newRandomPolynomial(d, tau.duplicate(), pars);
        Element C_2 = pars.getPairing().pairing(pars.getG2().duplicate(), pars.get_g().duplicate()).duplicate().powZn(tau.duplicate()).getImmutable();
        Element[] C_3_i = new Element[d];
        Element[] C_4_i = new Element[d];
        Element[] C_5_i = new Element[d];
        for (int i = 0; i < C_3_i.length; i++) {
            Element xi_i = pars.getZp().newRandomElement().getImmutable();
            Element chi_i = pars.getZp().newRandomElement().getImmutable();
            //C_3_i[i] = e_i[i].duplicate().mul(pars.get_g().duplicate().powZn(xi_i.duplicate())).getImmutable();
            //C_4_i[i] = pars.get_g().duplicate().powZn(chi_i.duplicate()).getImmutable();
            C_3_i[i] = pars.getEta()[2].powZn(s.sub(s_2)).getImmutable(); // Revised
            C_4_i[i] = pars.getEta()[3].powZn(s_2).getImmutable(); // Revised
            C_5_i[i] = E_i[i].duplicate().powZn(s.duplicate()).mul(pars.getG2().duplicate().powZn(l.evaluate(S_A[i]).duplicate())).duplicate().mul(Utils.H(S_A[i].duplicate(), pars).duplicate().powZn(xi_i.duplicate().mul(s.duplicate()))).duplicate().mul(pars.getPairing().getG1().newElementFromBytes(Utils.byteMergerAll(C_0.toBytes(), C_1.toBytes(), C_1_i[i].toBytes(), C_2_i[i].toBytes(), C_3_i[i].toBytes(), C_4_i[i].toBytes())).duplicate().powZn(chi_i.duplicate()));
        }

        List<Object> result = new ArrayList<>();
        result.add(C_0);
        result.add(C_1);
        result.add(C_2);
        result.add(C_1_i);
        result.add(C_2_i);
        result.add(C_3_i);
        result.add(C_4_i);
        result.add(C_5_i);
        return result;
    }
}
