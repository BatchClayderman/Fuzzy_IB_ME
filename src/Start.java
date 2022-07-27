import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;

import java.math.BigInteger;
import java.util.List;

public class Start {
    public static void main(String[] args){
        int n = 10;
        int d = 10;
        PARS pars = Setup.setup(n);
        Element[] S_A = new Element[n];
        for (int i = 0; i < S_A.length; i++) {
            S_A[i] = pars.getZp().newElement(BigInteger.valueOf(100 + i)).getImmutable();
        }
        Element[] S_B = new Element[n];
        for (int i = 0; i < S_B.length; i++) {
            S_B[i] = pars.getZp().newElement(BigInteger.valueOf(100 + i)).getImmutable();
        }
        Element[] P_A = new Element[n];
        for (int i = 0; i < P_A.length; i++) {
            P_A[i] = pars.getZp().newElement(BigInteger.valueOf(100 + i)).getImmutable();
        }
        Element[] P_B = new Element[n];
        for (int i = 0; i < P_B.length; i++) {
            P_B[i] = pars.getZp().newElement(BigInteger.valueOf(100 + i)).getImmutable();
        }
        Element[][] temp = EKGen.eKGen(d, pars, P_A);
        Element[] E_i = temp[0];
        Element[] e_i = temp[1];
        temp = DKGen.dKGen(d, pars, P_B, P_A);
        Element[][] Dd = {temp[0], temp[1], temp[2], temp[3], temp[4]}; // Revised
        Element[][] Dd_prime = {temp[5], temp[6], temp[7], temp[8], temp[9]}; // Revised
        Element M = pars.getGT().newRandomElement().getImmutable();
        List<Object> list = Enc.enc(d, pars, S_A, P_B, E_i, e_i, M);
        Element C_0 = ((Element) list.get(0)).getImmutable();
        Element C_1 = ((Element) list.get(1)).getImmutable();
        Element C_2 = ((Element) list.get(2)).getImmutable();
        Element[] C_1_i = (Element[]) list.get(3);
        Element[] C_2_i = (Element[]) list.get(4);
        Element[] C_3_i = (Element[]) list.get(5);
        Element[] C_4_i = (Element[]) list.get(6);
        Element[] C_5_i = (Element[]) list.get(7);
        Element M_prime = Dec.dec(d, P_A, S_A, P_B, S_B, pars, Dd, Dd_prime, C_0, C_1, C_2, C_1_i, C_2_i, C_3_i, C_4_i, C_5_i); // Revised
        System.out.println("M = " + M);
        System.out.println("M_prime = " + M_prime);
        System.out.println("Result = " + M.equals(M_prime));
    }
}
