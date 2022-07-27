package entity;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;

public class PARS {
    private Element g;
    private Element g1;
    private Element g2;
    private Element Y;
    private Element[] t;
    private Element[] l;
    private Element[] eta; // revised
    private Element[] theta; // revised
    private Element alpha;
    private Element beta;
    private Pairing pairing;
    private Field<Element> G;
    private Field<Element> GT;
    private Field<Element> Zp;

    public Element get_g() {
        return g;
    }
    public void set_g(Element g) {
        this.g = g;
    }

    public Element getG1() {
        return g1;
    }
    public void setG1(Element g1) {
        this.g1 = g1;
    }

    public Element getG2() {
        return g2;
    }
    public void setG2(Element g2) {
        this.g2 = g2;
    }

    public Element getY() {
        return Y;
    }
    public void setY(Element Y) {
        this.Y = Y;
    }

    public Element[] getT() {
        return t;
    }
    public void setT(Element[] t) {
        this.t = t;
    }

    public Element[] getL() {
        return l;
    }
    public void setL(Element[] l) {
        this.l = l;
    }

    public Element[] getEta() {
        return eta;
    } // revised
    public void setEta(Element[] eta) {
        this.eta = eta;
    } // revised
    
    public Element[] getTheta() {
        return theta;
    } // revised
    public void setTheta(Element[] theta) {
        this.theta = theta;
    } // revised
    
    public Element getAlpha() {
        return alpha;
    }
    public void setAlpha(Element alpha) {
        this.alpha = alpha;
    }

    public Element getBeta() {
        return beta;
    }
    public void setBeta(Element beta) {
        this.beta = beta;
    }

    public Pairing getPairing() {
        return pairing;
    }
    public void setPairing(Pairing pairing) {
        this.pairing = pairing;
    }

    public Field<Element> getG() {
        return G;
    }
    public void setG(Field<Element> G) { this.G = G; }

    public Field<Element> getGT() {
        return GT;
    }
    public void setGT(Field<Element> GT) {
        this.GT = GT;
    }

    public Field<Element> getZp() {
        return Zp;
    }
    public void setZp(Field<Element> zp) {
        Zp = zp;
    }

}
