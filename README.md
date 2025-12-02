# Exercici 2. Tècnica del Backtracking

Un grup d'excursionistes s'està preparant per a una sortida i necessita organitzar les seves motxilles de manera que portin els articles necessaris sense excedir ni el pes ni el volum màxim de cada motxilla.

Cada article té un pes, un volum específic, una utilitat (valor d'importància per a l'excursió), i un indicador de si és essencial (veure classe Article).

Cada motxilla individualment també té un pes i volum màxim que pot suportar, no totes les motxilles són iguals (veure classe Motxilla).

Es tracta de distribuir els articles en les diferents motxilles, minimitzant els articles que queden “fora”, és a dir no necessàriament tots els articles previstos hi caben en les motxilles.

L'objectiu és trobar la distribució d'articles en les motxilles que **maximitzi el número d’articles** que portaran. Per ser solució s’han de complir les següents restriccions:

 **1. Restriccions de pes i volum:** La suma del pes dels articles seleccionats per cada motxilla no ha d'excedir el pes màxim que pot carregar la motxilla. A més, el volum total dels articles seleccionats tampoc pot superar el volum màxim de la motxilla.

 **2. Quantitat mínima d'articles essencials:** Cada motxilla individualment ha d'incloure com a mínim un article dels considerats "essencial" (per exemple, una llanterna, una farmaciola de primers auxilis).

 **3. S’han d’emplenar totes les motxilles,** -és a dir no poden quedar motxilles buides-.

Si trobes més d’una solució que iguala el número d’articles que quedaran exclosos, entre ambdues s'ha de preferir la distribució que maximitza la utilitat total dels articles seleccionats -suma de la utilitat de tots els articles seleccionats-. A igualtat serà irrellevant ambdues de les dues solucions.

------------------------------------------------------------------------

# 🔹 Decisió
En cada nivell del backtracking ens preguntem:
*"L'article k-èssim a quina motxilla l'ubico, o el deixo fora?"*

Això significa que per a cada article tenim tantes opcions com motxilles + 1.

# 🔹 Domini
El domini de cada decisió és:
- totes les motxilles existents, o
- deixar l'article fora.

Aquest domini és exactament el mateix per a cada article, amplada exacta.

# 🔹 Acceptable

Assignar un article a una motxilla és acceptable si:
- No supera el pes màxim de la motxilla. 
- No supera el volum màxim de la motxilla.

Deixar l'article fora sempre serà accceptable.

# 🔹 Solució
Una assignació és solució si:
1.  Tots els articles han estat processats, és a dir, quan tots els articles tinguin motxilla o siguin descartats (nivell fulla).
2.  Totes les motxilles tenen almenys un article essencial.

# 🔹 Completable i poda
Un estat parcial és completable mentre:
- encara queden articles per assignar (no arribat a fulla), i

Podem aplicar una poda per millorar l'eficiència:
- si la solució en construcció té més descartats que la millor no cal continuar.

# 🔹 Espai de cerca

**Alçada de l'arbre:** és exacte, el nombre d'articles, perquè en cada nivell assignem un article, i cal que tots estiguin o a una motxilla o descartat perquè es queda fora.

**Amplada de l'arbre** és exacta, el nombre de motxilles + 1, 

![arbre](/EspaiCercaDesembre2024.drawio.png)

![arbre](/EspaiCercaDesembre2024_incorrecte.drawio.png)

### 🔹 Marcatge
No cal marcar articles perquè a cada nivell assignarem un article, per tant, no té sentit comprovar si ja s'ha assignat aquest article a un altre nivell.

No cal marcar motxilles perquè les anirem omplint, que un article no es pugui ficar a una motxilla no significa que no es pugui ficar un altre article més petit, no es necessari marcar les motxilles plenes.

### 🔹 Esquema a aplicar
Busquem la millor solució, maximitzar nombre d'articles a les motxilles, i en cas d'igualtat maximitzar utilitat.
