package backtracking;

public class Motxilla {
    private final int pesMaxim;
    private final int volumMaxim;

    // atributs afegits per controlar l'estat actual
    private int pesActual;
    private int volumActual;
    private int quantsEssencials;

    public Motxilla(int pes, int volum) {
        this.pesMaxim = pes;
        this.volumMaxim = volum;
        this.pesActual = 0;
        this.volumActual = 0;
        this.quantsEssencials = 0;
    }
    public int getVolumMaxim() { return volumMaxim;}
    public int getPesMaxim() { return pesMaxim;}

    // mètodes afegits per controlar l'estat actual
    public boolean hiCap(int aPes, int aVolum){
        return (pesActual + aPes <= pesMaxim) &&
               (volumActual + aVolum <= volumMaxim);
    }
    public void afegirArticle(int aPes, int aVolum, boolean aEsencial){
        pesActual += aPes;
        volumActual += aVolum;
        if(aEsencial) quantsEssencials++;
    }
    public void treureArticle(int aPes, int aVolum, boolean aEsencial){
        pesActual -= aPes;
        volumActual -= aVolum;
        if(aEsencial) quantsEssencials--;
    }
    public boolean conteEssencials(){
        return quantsEssencials > 0;
    }
} //fi classe Motxilla
