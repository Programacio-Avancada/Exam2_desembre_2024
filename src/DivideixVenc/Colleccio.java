package DivideixVenc;

/* Exercici 1 (1.5 punts). Tècnica Divideix i Venç
S’està implementant una col·lecció que emmagatzema els seus elements en una seqüència doblement enllaçada,
 sense node capçalera. Es demana d’escriure una funció que determini el valor màxim de la col·lecció
 utilitzant la tècnica del divideix i venç.
La seqüència enllaçada té dos atributs, un que sempre referencia al primer node de la seqüència i l’altre
 al darrer. Si la col·lecció només té un node ambdós referenciaran al mateix. I si la col·lecció és buida
 ambdós tindran una referencia nul.la.
 */
public class Colleccio<T extends Comparable<T>> {
    private class Node{
        private T inf;
        private Node seg, ant;
        public Node ( T inf){
            this.inf = inf;
            this.seg = null;
            this.ant = null;
        }
    }
    private Node inici; //Sempre referència al primer node de la seqüència
    private Node fi; //Sempre referència al darrer node de la seqüència

    public T trobaMaxim() throws Exception{
        //Cal aplicar la tècnica del divideix i venç.
        // Llança una excepció si la col·lecció està buida.
        // Es valorarà l’eficiència
        if( inici == null || fi == null )
            throw new Exception("Col·lecció buida");
        return trobaMaxim(inici, fi);
    }

    private T trobaMaxim( Node esq, Node dret){
        if( esq == dret )
            return esq.inf;
        // comparo esquerra i dreta i guardo el màxim a una variable local
        T max = esq.inf;
        if( max.compareTo(dret.inf) < 0 )
            max = dret.inf;
        // hem acabat el recorregut?
        if( esq.seg == dret )
            return max;
        // fem recursivitat i comparo el valor
        T max2 = trobaMaxim(esq.seg, dret.ant);
        if( max2.compareTo(max) > 0)
            max = max2;
        return max;
    }

    // mètodes que implemento per fer exemples:
    public Colleccio(){
        this.inici = null;
        this.fi = null;
    }
    public void inserir( T inf ){
        Node nou = new Node(inf);
        if( this.inici == null ){
            this.inici = nou;
            this.fi = nou;
        }else{
            this.fi.seg = nou;
            nou.ant = this.fi;
            this.fi = nou;
        }
    }
    public String toString(){
        String retorn = "";
        Node aux = this.inici;
        while( aux != null){
            retorn += aux.inf + ", ";
            aux = aux.seg;
        }
        return retorn;
    }

    public static void main(String[] args) {
        Colleccio<Double> valors = new Colleccio();
        try {
            System.out.println( valors + "\n màxim: "+ valors.trobaMaxim());
        } catch (Exception e) {
            System.out.println("No hi ha res"+ valors + "\n excepció ");
        }
        valors.inserir(Math.random());
        System.out.println("Un únic valor: "+ valors);
        try {
            System.out.println("màxim: "+ valors.trobaMaxim());
        } catch (Exception e) {
            System.out.println("ERROR! ");
        }
        valors.inserir(Math.random());
        System.out.println("Dos valors: "+ valors);
        try {
            System.out.println("màxim: "+ valors.trobaMaxim());
        } catch (Exception e) {
            System.out.println("ERROR! ");
        }
        valors.inserir(Math.random());
        try {
            System.out.println("Tres valors: "+ valors + "\n màxim: "+ valors.trobaMaxim());
        } catch (Exception e) {
            System.out.println("ERROR! ");
        }
        valors.inserir(Math.random());
        try {
            System.out.println("Quatre valors: "+ valors + "\n màxim: "+ valors.trobaMaxim());
        } catch (Exception e) {
            System.out.println("ERROR! ");
        }
    }

}
