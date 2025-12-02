package backtracking;

import java.util.Random;

public class Solucio {
    // atributs donats de la classe Solucio
    private Article[] articles; // articles
    private Motxilla[] motxilles; // motxilles a emplenar
	
	// Atenció: a la classe motxilla s'han afegit els atributs: pesActual, volumActual i quantsEssencials per controlar la solució actual

    // atributs de la solució actual
    private int[] solucioActual; // a l'índex article assigno l'índex motxilla o -1 (es queda a fora)
    private int quantsArticlesActual; // Núm. articles assignats a motxilles
    private int sumaUtilitatsActual; // utilitat de la solució actual

    // atributs de la millor solució trobada
    private int[] millorSolucio;
    private int quantsArticlesMillor;
    private int sumaUtilitatsMillor;

    /* Exercici 2 – Part 2 (0.5 punts) Escriu el mètode constructor, has de carregar les dades dels
        magatzems amb els articles i motxilles disponibles. Crear i inicialitzar els atributs de la classe segons
        el que m’has explicat a l’apartat c anterior. El constructor no tindrà cap paràmetre.*/
    public Solucio() {
        articles = obtenirDadesArticles();
        motxilles = obtenirDadesMotxilles();

        solucioActual = new int[articles.length];
        for (int i = 0; i < solucioActual.length; i++)
            solucioActual[i] = -1; //inicialment tots els articles fora
        quantsArticlesActual = 0;
        sumaUtilitatsActual = 0;
        millorSolucio = new int[articles.length];
        quantsArticlesMillor = Integer.MIN_VALUE; // volem maximitzar
        sumaUtilitatsMillor = Integer.MIN_VALUE; // en cas d'empat, maximitzar utilitat

    }
    /* Exercici 2 - Part 3 (0.25 punts) Escriu el mètode main que ubicaràs també a la classe Solucio.
        Aquest invocarà al backtracking i visualitzarà la solució trobada.*/
    public static void main(String[] args) {
        Solucio s = new Solucio();
        s.backMillor(0); // crida al mètode de backtracking
        System.out.println(s); // mostra la solució trobada
    }
    /* Exercici 2 – Part 4 (6.25 punts) Implementa el mètode que aplica la tècnica del backtracking
        i que ubicaràs a la classe Solucio public void backMillor(???). Aquest mètode no pot ser estàtic. Has
        de determinar el(s) paràmetre(s) necessaris, minimitzant-los. Es valorarà la descomposició funcional
        aplicada, fes mètodes privats per les diferents parts de l’esquema: esAcceptable(???), esMillor(???),...
        Es valorarà l’eficiència. Quan sigui possible cal podar l’arbre de cara a millorar l’eficiència de la
        implementació.*/
    public void backMillor(int idxArticle) {
        // IMPORTANT: amplada de l'arbre = num. motxilles + 1 (deixar fora)
        for (int idxMotxilla = 0; idxMotxilla <= motxilles.length; idxMotxilla++) {
            if (acceptable(idxArticle, idxMotxilla)) {
                anotar(idxArticle, idxMotxilla);
                if (esSolucio(idxArticle)) {
                    if (esMillor()) {
                        guardarMillorSolucio();
                    }
                } else{
                    int quantsRestants = articles.length - (idxArticle + 1);
                    if( quantsRestants > 0 && // no és fulla
                            (quantsArticlesActual + quantsRestants) >= quantsArticlesMillor ) // poda
                        backMillor(idxArticle + 1); // crida recursiva
                }
                desanotar(idxArticle, idxMotxilla); // desfem l'assignació
            }
        }
    }
    private boolean acceptable(int idxArticle, int idxMotxilla) {
        if (idxMotxilla == motxilles.length)
            return true; // deixar fora sempre és acceptable
        Motxilla m = motxilles[idxMotxilla];
        Article a = articles[idxArticle];
        return m.hiCap(a.getPes(), a.getVolum());
    }
    private void anotar(int idxArticle, int idxMotxilla) {
        if( idxMotxilla == motxilles.length )
            solucioActual[idxArticle] = -1; // deixar fora
        else {
            Motxilla m = motxilles[idxMotxilla];
            Article a = articles[idxArticle];
            m.afegirArticle(a.getPes(), a.getVolum(), a.isEssencial());
            quantsArticlesActual++;
            sumaUtilitatsActual += a.getUtilitat();
            solucioActual[idxArticle] = idxMotxilla;
        }
    }
    private void desanotar(int idxArticle, int idxMotxilla) {
        if( idxMotxilla < motxilles.length ){
            Motxilla m = motxilles[idxMotxilla];
            Article a = articles[idxArticle];
            m.treureArticle(a.getPes(), a.getVolum(), a.isEssencial());
            quantsArticlesActual--;
            sumaUtilitatsActual -= a.getUtilitat();
        }
        solucioActual[idxArticle] = -1;
    }
    private boolean esSolucio(int idxArticle) {
        if (idxArticle < articles.length-1) // cal trobar una fulla de l'arbre
            return false;
        for( Motxilla m : motxilles ){ // Cal mirar totes les motxilles tenen un essencial
            if( !m.conteEssencials() )
                return false;
        }
        return true;
    }
    private boolean esMillor() {
        //comprova si la solució actual és millor que la millor trobada fins ara
        if (quantsArticlesActual > quantsArticlesMillor)
            return true;
        if (quantsArticlesActual == quantsArticlesMillor) // empatat en num. articles, mirem utilitat
            return (sumaUtilitatsActual > sumaUtilitatsMillor);
        return false;
    }
    private void guardarMillorSolucio() {
        // guarda la solució actual com la millor trobada fins ara
        quantsArticlesMillor = quantsArticlesActual;
        sumaUtilitatsMillor = sumaUtilitatsActual;
        for (int i = 0; i < solucioActual.length; i++)
            millorSolucio[i] = solucioActual[i];
    }

    /* Exercici 2 – Part 5 (0.5 punts) Redefineix el mètode toString() a la classe Solucio per mostrar
    la solució trobada. Ha de retornar una cadena que mostri l’identificador dels articles carregats en cada
    motxilla i per a cadascuna de les motxilla. Les motxilles les pots enumerar amb 1, 2, 3...*/
    public String toString(){
        // visualitza la solució trobada, si hi ha
        if( quantsArticlesMillor<=0)
            return "No hi ha solució vàlida\n";

        StringBuilder sb = new StringBuilder();
        sb.append("Millor solució trobada porta:"+quantsArticlesMillor+"\n");
        for(int m=0; m<motxilles.length; m++){
            sb.append("Motxilla " + (m+1) + ": ");
            for(int a=0; a<articles.length; a++){
                if(millorSolucio[a]==m){
                    sb.append(articles[a].toString());
                }
            }
            sb.append("\n");
        }
        sb.append("Total articles carregats: " + quantsArticlesMillor + "\n");
        sb.append("Utilitat total: " + sumaUtilitatsMillor + "\n");
        return sb.toString();
    }
    // mètodes suposadament ja implementats
    private static Article[] obtenirDadesArticles(){
        //crea i retorna els articles que es volen portar
        Random rand = new Random();
        Article[] articles = new Article[rand.nextInt(10,15)];
        for(int i=0; i<articles.length; i++) {
            int pes = rand.nextInt(1, 10);
            int volum = rand.nextInt(1, 10);
            int utilitat = rand.nextInt(1, 6);
            boolean esEssencial = rand.nextBoolean();
            articles[i] = new Article(pes, volum, utilitat, i, esEssencial);
        }
        return articles;
    }
    private static Motxilla[] obtenirDadesMotxilles(){
        // crea i retorna les motxilles que s’han d’emplenar
        Random rand = new Random();
        Motxilla[] motxilles = new Motxilla[rand.nextInt(2,4)];
        for(int i=0; i<motxilles.length; i++) {
            int pesMaxim = rand.nextInt(15, 25);
            int volumMaxim = rand.nextInt(15, 25);
            motxilles[i] = new Motxilla(pesMaxim, volumMaxim);
        }
        return motxilles;
    }
} //fi classe Solució
