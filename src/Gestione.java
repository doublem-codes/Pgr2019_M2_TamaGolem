import java.util.ArrayList;
/**
 * classe utilizzata per giocare la partita
 * e per gestire l'iterazione dei vari golem tramite un equilibrio che viene generato casualmente
 */
public class Gestione {

    //inserimento stringhe di comunicazione
    private static String init_par = "\n--- INIZIALIZZAZIONE PARTITA ---\n";
    private static String gen_par = "\n--- GENERAZIONE PARTITA ---\n";
    private static String gen_eql = "\n--- GENERAZIONE EQUILIBRIO ---\n";
    private static String start_par = "\n--- INIZIO PARTITA ---\n\n";
    private static String end_par = "\n--- FINE PARTITA ---";
    private static String eql_par = "\n--- EQUILIBRIO PARTITA GIOCATA ---";
    private static String str_scelta = " SCEGLERE PIETRE DEL GOLEM SCHIERATO \n ";
    private static String str_pietre = "LE PIETRE A DISPOSIZIONE SONO";
    private static String str_giocatore = "GIOCATORE ";
    private static String str_rinscelta = " RINSERIRE PIETRE DEl GOLEM SCHIERATO ";
    private static String str_elementi_gioco = "\n--- GLI ELEMENTI CON CUI SI GIOCA SONO : ";
    private static String err_pietre = "\n\n\n--- ERRORE DI INSERIMENTO PIETRE PERCHE' SONO UGUALI TRA I DUE GOLEM ---" +
            "\n--- RICARICA PIETRE NELLA SACCA ---";

    //menu vari per gestione partita e caratteri di controllo
    private static String menu_numero_elementi ="\n--- INSERIMENTO NUMERO PIETRE --- " +
                                                "\n- SE TRA 3 E 5: DIFFICOLTA FACILE" +
                                                "\n- SE TRA 6 E 8: DIFFICOLTA MEDIA" +
                                                "\n- SE TRA 9 E 10:DIFFICOLTA DIFFICLE" +
                                                "\n\n- !!! INSERIRE NUMERO MAGGORE DI 3 !!!"+
                                                "\n- !!! SE MAGGIORE DI 10 SI POTREBBE COMPROMETTERE IL GIOCO !!!\n";
    private static char charInserimentoManuale= 'M';//carattere inserimento manuale
    private static char charInserimentoAutomatico = 'A';//carattere inserimento automatico
    private static char charStampaElementi = 'S';//carattere per stampa elemnti predefiniti
    private static String menu_aquisizione_elementi = "\n--- SCEGLIERE TRA ---" +//menu per la scelta automatica o manuale degli elemnti
                                                      "\n -["+charInserimentoManuale+"] : INSERIMENTO MANUALE DEGLI ELEMENTI" +
                                                      "\n -["+charStampaElementi+"] : STAMPA SUGGERIMENTO ELEMENTI";
    private static String menu_aquisizione_elementi_automatico = "\n -["+charInserimentoAutomatico+"] : INSERIMENTO ELEMENTI AUTOMATICO" ;
    private static char charNomeIn = 'N';//carattere inserimento per nome
    private static char charCodeIn = 'C';//carattere inserimento per codice
    private static String menu_inserimento_pietre = "SI VUOLE INSERIRE PIETRA PER :" +//menu per scelgliere il tipo di inserimento
                                                    "\n -["+charCodeIn+"] : PER INSERIRE PIETRA TRAMITE CODICE TRA PARENTESI" +
                                                    "\n -["+charNomeIn+"] :PER INSERIRE PIETRA TRAMITE NOME ";
    private static char charUscita = 'E';//carattere uscita
    private static char charNuovo = 'P';//carattere nuova partita
    private static String menu_scelta = "--- INSERIRE ---" + //menu scelta fina per nuova partita o exit
                                         "\n [ " +charUscita+" ] : se si vuole uscire " +
                                         "\n [ "+charNuovo+" ] : se si vuole inziare una nuova partita ";
    private static String stringaHelp = "HELP";//stringa la visualizzazione degli elementi predefiniti durante l'insrimento degli elementi
    private static String str_elementi_stampa = "--- SE SI VUOLE VISUALIZZARE NUOVAMENTE LA LISTA DI ELEMENTI PREDEFINITI INSERIRE [ "+stringaHelp +" ] ---\n";

    private static String abb_text="\n! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !\n";
    private static String abb_capo = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    //array di elementi predefiniti
    private static String[] arrayElementi ={"NORMALE","FUOCO","LOTTA","ACQUA","VOLANTE","ERBA","VELENO","ELETTRO",
                                            "TERRA","PSICO","GHIACCIO","COLEOTTERO","DRAGO","SPETTRO","BUIO","ACCIAIO",
                                            "SCONOSCIUTO","OMBRA","METALLO","FERRO"};

    private static int minElementi = 3;// numero minimo di elemnti per aver senso il gioco

    Partita partita ;
    Equilibrio equilibrio = new Equilibrio();

    private String vincitore = "";//stringa vincitote partita
    private String perdente = "";//stringa perdente partita

    /**
     * fase1
     * gestone della creazione di una nuova partita
     * tramite aquisizione dei dati giocatori e del numero di elementi per giocare
     * inoltre si genera l'equilibrio della partita
     */
    public void fase1(){
        System.out.println(init_par);
        String play1 = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome giocatore 1: ");// inserimento nome giocatore 1
        String play2 = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome giocatore 2: ");// inserimento nome giocatore 2
        System.out.println(menu_numero_elementi);

        int numeroElementi= it.unibs.fp.mylib.InputDati.leggiIntero("inserire: ");// inserimento numero elementi;
        while (numeroElementi < minElementi) {//controllo che il numero di elemneti non sia minore del numero minimo
            numeroElementi = it.unibs.fp.mylib.InputDati.leggiIntero("rinserire: ");// rinserimento numero elementi;
        }

        char inMenu;
        if(numeroElementi >arrayElementi.length){
            System.out.println(menu_aquisizione_elementi);
             inMenu = Character.toUpperCase(it.unibs.fp.mylib.InputDati.leggiChar("inserire"));
            while(inMenu != charStampaElementi && inMenu != charInserimentoManuale){//controllo dato in input
                inMenu = it.unibs.fp.mylib.InputDati.leggiChar("rinserire");//rinserimento dato
            }
        }else{
            System.out.println(menu_aquisizione_elementi + menu_aquisizione_elementi_automatico);
             inMenu = Character.toUpperCase(it.unibs.fp.mylib.InputDati.leggiChar("inserire"));
            while(inMenu != charStampaElementi &&  inMenu != charInserimentoAutomatico && inMenu != charInserimentoManuale){//controllo dato in input
                inMenu = it.unibs.fp.mylib.InputDati.leggiChar("rinserire");//rinserimento dato
            }
        }

        ArrayList<String> nomeElementi =new ArrayList<>();
        switch (inMenu){//scelta tipo di inserimento
            case 'A'://inserimento automatico
                nomeElementi = aquisiciElementiAutomatico(numeroElementi);
                break;
            case 'S'://stampa
                stampaArrayElementi();
            case 'M'://inserimento manuale
                nomeElementi = aquisiciElementiManuale(numeroElementi);
                break;
        }
        System.out.println(str_elementi_gioco);
        for (int i = 0; i < nomeElementi.size(); i++){
            System.out.println("["+(i+1)+"] : "+ nomeElementi.get(i));
        }
        System.out.println(gen_eql);
        equilibrio.generaEquilibrio(numeroElementi);//generazione equilibrio
        System.out.println(gen_par);
        int vita = equilibrio.cercaMassimoMatrice();//estrazione supW per set della vita
        partita = new Partita(numeroElementi,nomeElementi,play1,play2,vita);//generazione partita
    }

    /**
     * fase2
     * metodo per il controllo flusso di gioco
     * iterazione tra i golem dei due giocatori
     */
    public void fase2(){
        System.out.println(start_par);//inizio partita
        int golem1=0 , golem2=0; //set primo golem
        String stmapaScelta = str_scelta; //stringa uscita
        do {//ciclo inserimento pietre due giocatori con controllo diversità
            System.out.println(str_giocatore+partita.getPlayer1() + stmapaScelta );
            partita.getPlayer1Golem().get(golem1).setPietre(acquisisciPietreGolem());//aquisizione pietre giocatore1
            System.out.println(abb_capo);
            System.out.println(str_giocatore+partita.getPlayer2() + stmapaScelta );
            partita.getPlayer2Golem().get(golem2).setPietre(acquisisciPietreGolem());//aquisizione pietre giocatore2
            stmapaScelta = str_rinscelta;//aggiornamento stringa uscita
        }while (controlloElementiGiocatori(golem1,golem2,true));//controllo pietre golem diverse
        System.out.println(abb_capo);

        int danno1Tot=0;//inizializzazione danno1
        int danno2Tot=0;//inizializzazione danno2
        int vita1 = partita.getPlayer1Golem().get(golem1).getVita();//settaggio vita1
        int vita2 = partita.getPlayer2Golem().get(golem2).getVita();//settaggio vita2
        do {

            int pietre = 0;//posizione pietre di gioco
            System.out.println("--- NUOVO SCONTRO ---\n VITA DEI GOLEM E':");
            System.out.print("GOLEM "+golem1+" DEL GIOCATORE " + partita.getPlayer1());
            stampaVita(vita1);
            System.out.print("GOLEM "+golem2+" DEL GIOCATORE " + partita.getPlayer2());
            stampaVita(vita2);
            System.out.println("--- FASE DI SCONTRO ---\n");

            do {
                int pietra1 =0;//indice della matrice per aquisire i danni
                for (int i = 0;i<partita.getNomeElementi().size();i++){
                    if(partita.getPlayer1Golem().get(golem1).getPietre().get(pietre).equals(partita.getNomeElementi().get(i))) break;
                    pietra1++;
                }
                int pietra2 =0;//indice della matrice per aquisire i danni
                for (int i = 0;i<partita.getNomeElementi().size();i++){
                    if(partita.getPlayer2Golem().get(golem2).getPietre().get(pietre).equals(partita.getNomeElementi().get(i))) break;
                    pietra2++;
                }
                int danno = equilibrio.danni(pietra1,pietra2);//aquisizione danni dalla matrice
                //se è positivo è il giocatore uno che fa danno a giocatore due
                //se negativo e il giocatore due che fa danno a giocatore uno
                if (danno>0){// aggiornamento statisctiche vita e danno
                    vita2 -= danno;
                    danno1Tot += danno;
                }else{
                    vita1 += danno;
                    danno = -danno;
                    danno2Tot += danno;
                }

                //stampa le iterazione degli elementi
                //e della vita restante ai vari golem
                System.out.println("\n" + partita.getPlayer1Golem().get(golem1).getPietre().get(pietre) + " --------> "+partita.getPlayer2Golem().get(golem2).getPietre().get(pietre));
                System.out.print("GOLEM "+golem1+" DEL GIOCATORE " + partita.getPlayer1());
                stampaVita(vita1);
                System.out.print("GOLEM "+golem2+" DEL GIOCATORE " + partita.getPlayer2());
                stampaVita(vita2);

                partita.getPlayer1Golem().get(golem1).setVita(vita1);//aggiornamento vita1
                partita.getPlayer2Golem().get(golem2).setVita(vita2);//aggiornamento vita2

                pietre++;//incremento posizione pietre dei golem
                if(pietre == partita.getNumeroPietreGolem() ) pietre = 0;// se fuori bound la metto a zero cioè rinizio lo scorrimento

            } while (partita.getPlayer1Golem().get(golem1).getVita() > 0 && partita.getPlayer2Golem().get(golem2).getVita() > 0);

            if (partita.getPlayer1Golem().get(golem1).getVita() <= 0)//giocatore 1 aggiormamento statistiche o scelta nuovo golem
            {
                if (golem1 == partita.getNumeroGolem()-1)//asseganzione vincitore
                {
                    vincitore = partita.getPlayer2();
                    perdente = partita.getPlayer1();
                    System.out.println("\nDANNI FATTI DAL GOLEM NUMERO "+golem1+" MENTRE ERA INVITA DEL GIOCATORE "+ partita.getPlayer1()+" SONO " + danno1Tot);
                    System.out.println("\nDANNI FATTI DAL GOLEM NUMERO "+golem2+" MENTRE ERA INVITA DEL GIOCATORE "+ partita.getPlayer2()+" SONO " + danno2Tot);
                    break;
                }

                System.out.println("\nDANNI FATTI DAL GOLEM NUMERO "+golem1+" MENTRE ERA INVITA DEL GIOCATORE "+ partita.getPlayer1()+" SONO " + danno1Tot);
                System.out.println("VITA RIMASTA AL GOLEM "+partita.getPlayer2()+" E': " +partita.getPlayer2Golem().get(golem2).getVita());
                //creazione e set di un nuovo golem
                danno1Tot = 0;
                golem1++;//invocazione nuovo golem
                stmapaScelta = str_scelta;
                do {
                    System.out.println(partita.getPlayer1() + stmapaScelta );
                    partita.getPlayer1Golem().get(golem1).setPietre(acquisisciPietreGolem());//aquisizione pietre del nuovo golem schierato
                    stmapaScelta = str_rinscelta;
                }while (controlloElementiGiocatori(golem1, golem2,false));//controllo pietre golem diverse
                System.out.println(abb_capo);
                vita1 = partita.getPlayer1Golem().get(golem1).getVita();

            }

            if(partita.getPlayer2Golem().get(golem2).getVita() <= 0)//giocatore 2 aggiormamento statistiche o scelta nuovo golem
            {
                if(golem2 == partita.getNumeroGolem()-1)//asseganzione vincitore
                {
                    perdente = partita.getPlayer2();
                    vincitore = partita.getPlayer1();
                    System.out.println("\nDANNI FATTI DAL GOLEM NUMERO "+golem1+" MENTRE ERA INVITA DEL GIOCATORE "+ partita.getPlayer1()+" SONO " + danno1Tot);
                    System.out.println("\nDANNI FATTI DAL GOLEM NUMERO "+golem2+" MENTRE ERA INVITA DEL GIOCATORE "+ partita.getPlayer2()+" SONO " + danno2Tot);
                    break;
                }
                System.out.println("\nDANNI FATTI DAL GOLEM NUMERO "+golem2+" MENTRE ERA INVITA DEL GIOCATORE "+ partita.getPlayer2()+" SONO " + danno2Tot);
                System.out.println("VITA RIMASTA AL GOLEM "+partita.getPlayer1()+" E': " +partita.getPlayer1Golem().get(golem1).getVita());
                //creazione e set di un nuovo golem
                golem2++;//invocazione nuovo golem
                danno2Tot = 0;
                stmapaScelta = str_scelta;
                do {
                    System.out.println(partita.getPlayer2() + stmapaScelta );
                    partita.getPlayer2Golem().get(golem2).setPietre(acquisisciPietreGolem());//aquisizione pietre del nuovo golem schierato
                    stmapaScelta = str_rinscelta;
                }while (controlloElementiGiocatori(golem1, golem2,false));//controllo pietre golem diverse
                System.out.println(abb_capo);
                vita2 = partita.getPlayer2Golem().get(golem2).getVita();
            }
        }while(true);

        System.out.print(end_par);//fine partita
    }

    /**
     * fase3
     * stampa vincitore e perdente ed equilibrio
     */
    public void fase3(){//stamoa a video del vincitore
        System.out.println(abb_text);
        System.out.println("\t\t\t\t\t IL VINCITORE E' " + vincitore);//stampa vincitore
        System.out.println(abb_text);
        System.out.println("\t\t\t\t\t IL PERDENTE E' " + perdente);//stampa perdente
        System.out.println(abb_text);
        System.out.println(eql_par);
        equilibrio.printMatrix(partita.getNomeElementi());//stampa a video dell'equilibrio di gioco
    }

    /**
     * metodo scelta nuova partita o uscita
     * @return valore di escape dal loop del main true nuova partita false esci
     */
    public boolean scelta() {
        System.out.println(menu_scelta);
        char input = Character.toUpperCase(it.unibs.fp.mylib.InputDati.leggiChar("inserire"));
        while(input != charUscita &&  input != charNuovo){//controllo dato in input
            input = Character.toUpperCase(it.unibs.fp.mylib.InputDati.leggiChar("rinserire"));//rinserimento dato
        }
        return input != charUscita;
    }


    /**
     * metodo che evita loop infinito in caso di scelta di pietre uguali tra i due giocatori
     * in caso siano uguali si procedere a rimeterre le pietre nella sacca
     *
     * @param numGolem1 passaggio indice golem del primo giocatore
     * @param numGolem2 passaggio indice golem del secondo giocatore
     * @param isDue utilizzato per riempire la sacca
     * @return true se sono uguli false se sono diversi
     */
    private boolean controlloElementiGiocatori(int numGolem1,int numGolem2,boolean isDue){
        int conta = 0;
        for (int i = 0; i <partita.getNumeroPietreGolem();i++ ){//conteggio elemnti uguali
            if(partita.getPlayer1Golem().get(numGolem1).getPietre().get(i).equals(partita.getPlayer2Golem().get(numGolem2).getPietre().get(i))){
                conta++;
            }
        }
        if(conta == partita.getNumeroPietreGolem()){//se il numero di elenti uguali e al numeo dele pietre dei golem allora rinserisco gli elementi nella sacca
            System.out.println(err_pietre);
            if(isDue){
                for(int k = 0; k < partita.getNumeroPietreGolem(); k++)//rinserimento elementi
                {
                    int indice =partita.getIndiceSacca(partita.getPlayer1Golem().get(numGolem1).getPietre().get(k));//estrazione indice elemnto uguale se non c'è si aggiunge in testa
                    partita.getSacca().add(indice,partita.getPlayer1Golem().get(numGolem1).getPietre().get(k));
                    partita.getSacca().add(indice,partita.getPlayer2Golem().get(numGolem2).getPietre().get(k));
                }
            }else{//basta uno dei due essendo le pietre uguali
                for(int k=0; k < partita.getNumeroPietreGolem();k++)//rinserimento elementi
                {
                    int indice =partita.getIndiceSacca(partita.getPlayer1Golem().get(numGolem1).getPietre().get(k));//estrazione indice elemnto uguale se non c'è si aggiunge in testa
                    partita.getSacca().add(indice,partita.getPlayer1Golem().get(numGolem1).getPietre().get(k));
                }
            }
            return true;
        }
        return false;
    }

    /**
     * metodo utilizzato per l'aquisizone degli elementi del golem
     * una volta inserito si rimuove dalla sacca se c'è se no si richiede
     * @return arraylist con gli elenti del golem
     */

    private ArrayList<String> acquisisciPietreGolem(){
        ArrayList<String>elementi = new ArrayList<>();

        do {
            String messaggio = "inserire nome pietra numero ";
            String messaggio1 = "re"+ messaggio +"perchè sbagliato ";
            String messaggio2 = "inserire codice pietra numero ";
            String messaggio3 = "re"+ messaggio2+"perchè sbagliato ";
            System.out.println(str_pietre);
            stampaSacca(partita.getSacca());
            String elemento;
            boolean isFind=true;
            System.out.println(menu_inserimento_pietre);

            char selezione;//carattere per scegliere il tipo di inserimento della pietra
            do {
                selezione =Character.toUpperCase(it.unibs.fp.mylib.InputDati.leggiChar("inserire carattere"));
            }while(selezione != charCodeIn && selezione != charNomeIn);

            if(selezione == charNomeIn) {//aquisizione per nome

                do {
                    elemento = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota(messaggio + (elementi.size() + 1)+" ").toUpperCase(); //lettura elemento
                    for (String nomeElemento : partita.getNomeElementi()) {//ricerca elemento in arraylist elementi
                        if (elemento.equals(nomeElemento)) {
                            for (String nomeInSacca : partita.getSacca()){
                                if(elemento.equals(nomeInSacca)){
                                    isFind = false;
                                    break;
                                }
                            }
                            if(!isFind)break;
                        }
                    }
                    messaggio = messaggio1;
                } while (isFind);

                for (int i = 0; i < partita.getSacca().size(); i++) {//ricerca elemento in sacca
                    if (elemento.equals(partita.getSacca().get(i))) {
                        elementi.add(elemento); //aggiunta ad elemnti
                        partita.getSacca().remove(i); //rimozione da sacca
                        break;
                    }
                }
            }else {//aquisizione per codice
                int intE;

                do {

                    do {
                        intE = it.unibs.fp.mylib.InputDati.leggiIntero(messaggio2 + (elementi.size() + 1) + " ");
                        messaggio2=messaggio3;
                    } while (!(intE>=0 && intE<partita.getNumeroElementi()));
                    elemento = partita.getNomeElementi().get(intE);
                    for (String nomeElemento : partita.getNomeElementi()) {//ricerca elemento in arraylist elementi
                        if (elemento.equals(nomeElemento)) {
                            for (String nomeInSacca : partita.getSacca()){
                                if(elemento.equals(nomeInSacca)){
                                    isFind = false;
                                    break;
                                }
                            }
                            if(!isFind)break;
                        }
                    }
                }while(isFind);
                for (int i = 0; i < partita.getSacca().size(); i++) {//ricerca elemento in sacca
                    if (elemento.equals(partita.getSacca().get(i))) {
                        elementi.add(elemento); //aggiunta ad elemnti
                        partita.getSacca().remove(i); //rimozione da sacca
                        break;
                    }
                }

            }
        }while(elementi.size() != partita.getNumeroPietreGolem());//controllo elementi lungo come numero pietre golem
        return elementi;
    }

    /**
     * metodo per gestire l'inserimento manuale degli elementi
     *
     * @param numeroElementi numero di elemnti dainserire
     * @return arraylist di elmenti inseriti manualmente
     */
    private ArrayList<String> aquisiciElementiManuale(int numeroElementi){
        ArrayList<String> elementi = new ArrayList<>();
        for (int index =0 ; index < numeroElementi ; index++){//aquisizione arraylist di elementi
            String nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome dell'elemento numero "+index+" : ").toUpperCase(); //inserimento di un elemento
            if(nome.equals(stringaHelp.toUpperCase())){//stampa nuovamente l'array di elementi suggeriti
                stampaArrayElementi();
                nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome dell'elemento numero "+index+" : ").toUpperCase(); //inserimento di un elemento

            }
            for (int j = 0 ; j < elementi.size(); j++){//controllo sugli elementi inseriti che siano tutti diversi
                if(nome.equals(elementi.get(j))){//controllo che non ci sia nella lista
                    System.out.println("inserire nuovamente elemento " + index +" perchè duplicato");
                    nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("rinserire: ");//rinserimento elemto
                    j = -1;//riscorro l'arraylist per vedere non è stato rinserito lo stesso elemento
                }
            }
            elementi.add(nome);//essendo elemnto diverso lo inserisco nell'arraylist
        }
        return elementi;
    }

    /**
     * metodo per gestire l'inserimento automatico degli elementi
     *
     * @param numeroElementi numero di elemnti dainserire
     * @return arraylist di elmenti inseriti manualmente
     */
    private ArrayList<String> aquisiciElementiAutomatico(int numeroElementi){
        ArrayList<String> elementi = new ArrayList<>();
        for (int index = 0 ; index < numeroElementi ; index++){//aquisizione arraylist di elementi
            elementi.add(arrayElementi[index]);//essendo elemnto diverso lo inserisco nell'arraylist
        }
        return elementi;
    }

    /**
     * metodo per stampare array di elemnti defaul info
     */
    private void stampaArrayElementi(){
        for (int i=0; i<arrayElementi.length;i++){
            System.out.println("\t-"+arrayElementi[i]);
        }
        System.out.println(str_elementi_stampa);
    }

    /**
     *metodo per stampare la vita che rimane al golem
     *  si stampa * quella che rimane
     *  si stampa _ quella che ha perso
     * @param vita numero di vita che ha il golem
     */
    private void stampaVita(int vita) {
        System.out.print('[');
        for (int i = 0; i < partita.getVitaset(); i++){
            if (i < vita) {
                System.out.print(" * ");

            } else {
                System.out.print(" _ ");
            }
        }
        System.out.print("]\n");
    }

    /**
     * metodo utilizzato per stampare gli elementi della sacca
     * divisi per elemnti con cancolo di quanti c'è ne sono
     * @param sacca passaggio sacca partita
     */

    private void stampaSacca(ArrayList<String> sacca){
        if(sacca.size()==0){//caso sacca vuota
            System.out.println("SACCA VUOTA");
            return;
        }
        String elementoSacca = sacca.get(0);//lettura prima elemento sacca
        int numero = partita.getIndiceElemento(elementoSacca);
        int numeroRestante = 1;
        for(int i = 1; i<sacca.size();i++){//ciclo che calcola in numero di elemnti restanti
            if (elementoSacca.equals(sacca.get(i))){
                numeroRestante++;
            }else{//se diverso inizializzo il conto degli elenti
                System.out.println("\t [ " + numero +" ]--> "+ elementoSacca +": "+ numeroRestante);//stampa codice elemnto e numero pietre restanti
                elementoSacca = sacca.get(i);//aggiornamento nuovo elemento
                numero = partita.getIndiceElemento(elementoSacca);
                numeroRestante = 1;

            }
        }
        System.out.println("\t [ " + numero +" ]--> "+ elementoSacca +": "+ numeroRestante);//stampa codice elemnto e numero pietre restanti
    }
}