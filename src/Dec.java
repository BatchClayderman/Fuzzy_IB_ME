import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;

public class Dec {
    public static Element dec(int d, Element[] P_A, Element[] S_A, Element[] P_B, Element[] S_B, PARS pars, Element[][] Dd, Element[][] Dd_prime, Element C_0, Element C_1, Element C_2, Element C_3, Element C_4, Element[] C_1_i, Element[] C_2_i, Element[] C_3_i, Element[] C_4_i, Element[] C_5_i){
        Element[] W_A_prime = Utils.intersect(P_A, S_A);
        Element[] W_B_prime = Utils.intersect(P_B, S_B);
        if (W_A_prime.length >= d && W_B_prime.length >= d){
            Element[] W_A = new Element[d];
            System.arraycopy(W_A_prime, 0, W_A, 0, W_A.length);
            Element[] W_B = new Element[d];
            System.arraycopy(W_B_prime, 0, W_B, 0, W_B.length);

            Element K_s_prime = pars.getGT().newOneElement().getImmutable();
            for (int i = 0; i < W_B.length; ++i) {
                //K_s_prime = K_s_prime.mul(pars.getPairing().pairing(D_i[i].duplicate(), C_1.duplicate()).div(pars.getPairing().pairing(d_i[i].duplicate(), C_1_i[i].duplicate())).powZn(Utils.delta(pars.getZp().newZeroElement(), W_B[i], W_B, pars))).getImmutable();
            	K_s_prime = K_s_prime.mul((pars.getPairing().pairing(Dd[0][i].duplicate(), C_1_i[i].duplicate())).mul(pars.getPairing().pairing(Dd[1][i].duplicate(), C_1.duplicate()))).getImmutable(); // Revised
            }
            Element K_l_prime = pars.getGT().newOneElement().getImmutable();
            for (int i = 0; i < W_A.length; ++i) {
                //K_l_prime = K_l_prime.mul(pars.getPairing().pairing(D_i_prime[i].duplicate(), C_1.duplicate()).duplicate().mul(
                //        pars.getPairing().pairing(pars.getPairing().getG1().newElementFromBytes(Utils.byteMergerAll(C_0.toBytes(), C_1.toBytes(), C_1_i[i].toBytes(), C_2_i[i].toBytes(), C_3_i[i].toBytes(), C_4_i[i].toBytes())).duplicate(),C_4_i[i].duplicate()).duplicate().mul(pars.getPairing().pairing(C_3_i[i].duplicate(), C_2_i[i].duplicate()).duplicate())).div(pars.getPairing().pairing(d_i_prime[i].duplicate(), C_2_i[i].duplicate()).duplicate().mul(pars.getPairing().pairing(C_5_i[i].duplicate(), pars.get_g().duplicate()).duplicate())).powZn(Utils.delta(pars.getZp().newZeroElement(), W_A[i], W_A, pars))).getImmutable();
            	K_l_prime = K_l_prime.mul(
            			pars.getPairing().pairing(Dd_prime[0][i].duplicate(), C_1_i[i].duplicate()).duplicate().mul(
            					pars.getPairing().pairing(Dd_prime[1][i].duplicate(), C_1.duplicate()).duplicate().mul(
            							pars.getPairing().pairing(Dd_prime[2][i], C_3).mul(
            									pars.getPairing().pairing(Dd_prime[3][i], C_4).mul(
            											pars.getPairing().pairing(pars.get_g(), Dd_prime[4][i]).mul(
            													pars.getPairing().pairing(Dd_prime[2][i].duplicate(), C_2.duplicate()).duplicate().div(
            															pars.getPairing().pairing(C_4_i[i], Utils.H(pars.getG().newRandomElement(), pars)).div(
            																	pars.getPairing().pairing(C_2_i[i], C_3_i[i])
            															)
            													)
            											)
            									)
            							)
            					)
            			)
            	); // Revised
            }
            Element M = C_1.duplicate().div(K_s_prime.duplicate().mul(K_l_prime.duplicate())).getImmutable();
            return M;
        } else
            return null;
    }
}
