package backtracking;

public class Article {
    private final int pes;
    private final int volum;
    private final int utilitat; //[1,5] → 5 és la màxima
    private final int identificador;
    private final boolean esEssencial;
    public Article(int pes, int volum, int utilitat, int identificador,
                   boolean esEssencial) {
        this.pes = pes;
        this.volum = volum;
        this.utilitat = utilitat;
        this.identificador = identificador;
        this.esEssencial = esEssencial;
    }
    public int getPes() { return pes;}
    public int getVolum() { return volum;}
    public int getUtilitat() { return utilitat;}
    public boolean isEssencial() { return esEssencial == true;}
    public String toString() {return identificador + " ";}
}//fi classe Article
