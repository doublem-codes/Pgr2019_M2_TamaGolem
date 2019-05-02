import java.util.ArrayList;

public class Gestione {
    //inserimento stringhe di comunicazione
    private static String init_par = "\n--- INIZIALIZZAZIONE PARTITA ---";
    private static String gen_par = "\n--- GENERAZIONE PARTITA ---";
    private static String gen_eql = "\n--- GENERAZIONE EQUILIBRIO ---";
    private static String start_par = "\n--- INIZIO PARTITA ---";
    private static String end_par = "\n--- FINE PARTITA ---";
    private static String eql_par = "\n--- EQUILIBRIO PARTITA GIOCATA ---";
    private static String menu_numero_elementi ="\n--- INSERIMENTO NUMERO PIETRE --- " +
                                                "\n-SE TRA 3 E 5: DIFFICOLTA FACILE\n-SE TRA 6 E 8: DIFFICOLTA MEDIA\n" +
                                                "-SE TRA 9 E 10:DIFFICOLTA DIFFICLE\n-SE MAGGIORE DI 10 SI POTREBBE COMPROMETTERE IL GIOCO";
    private static String abb_text="\n! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !\n";

    //inserimento caratteri escape del loop do gioco
    private static char charUscita = 'E';//carattere uscita
    private static char charNuovo = 'N';//carateere nuova partita

    private static int minElementi = 3;

    Partita partita ;
    Equilibrio equilibrio = new Equilibrio();

    private String vincitore = "";
    private String perdente = "";

    public void fase1(){
        System.out.println(init_par);
        String play1 = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome giocatore 1: ");// inserimento nome giocatore 1
        String play2 = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome giocatore 2: ");// inserimento nome giocatore 2
        System.out.println(menu_numero_elementi);

        int numeroElementi= it.unibs.fp.mylib.InputDati.leggiIntero("inserire: ");// inserimento numero elementi;
        while (numeroElementi < minElementi) {//controllo che in numero di elemnti non sia minore o uguale a zero
            numeroElementi = it.unibs.fp.mylib.InputDati.leggiIntero("rinserire: ");// rinserimento numero elementi;
        }

        ArrayList<String> nomeElementi = new ArrayList<>();
        for (int index =0 ; index < numeroElementi ; index++){//controllo sugli elementi inseriti che siano tutti diversi
            String nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome dell'elemento numero "+index+" : ");
            for (int j = 0 ; j < nomeElementi.size(); j++){
                if(nome.equals(nomeElementi.get(j))){
                    System.out.println("inserire nuovamente elemento " + index +" perchè duplicato");
                    nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("rinserire: ");
                    j = -1;
                }
            }
            nomeElementi.add(nome);
        }

        System.out.println(gen_par);
        partita = new Partita(numeroElementi,nomeElementi,play1,play2);

        System.out.println(gen_eql);
       // equilibrio.generaEquilibrio(numeroElementi);
    }














    //iterazione tra i golem
    public void fase2(){
        System.out.println(start_par);//inizio partita
        int golem1=0 , golem2=0;
        System.out.println("GIOCATORE "+partita.getPlayer1() + " SCEGLERE PIETRE DEL PRIMO GOLEM SCHIERATO");
        partita.getPlayer1Golem().get(golem1).setPietre(acquisiciElementiGolem());
        System.out.println("GIOCATORE "+partita.getPlayer2() + " SCEGLERE PIETRE DEL PRIMO GOLEM SCHIERATO");
        partita.getPlayer2Golem().get(golem2).setPietre(acquisiciElementiGolem());

        int pietre = 0;
        do{
            do {
                int vita1 =  partita.getPlayer1Golem().get(golem1).getVita();
                int vita2 = partita.getPlayer2Golem().get(golem2).getVita();

                String pietra1 = partita.getPlayer1Golem().get(golem1).getPietre().get(pietre);
                String pietra2 = partita.getPlayer2Golem().get(golem2).getPietre().get(pietre);

                if(!pietra1.equals(pietra2)) {
                    //gestione equilibrio
                    //aggiornamento vita
                }

                partita.getPlayer1Golem().get(golem1).setVita(vita1);
                partita.getPlayer2Golem().get(golem2).setVita(vita2);
            }while (partita.getPlayer1Golem().get(golem1).getVita() <= 0 || partita.getPlayer2Golem().get(golem2).getVita() <=0 );

            if(partita.getPlayer1Golem().get(golem1).getVita() <= 0){
                if(golem1 == partita.getNumeroGolem())//asseganzione vincitore
                {
                    vincitore = partita.getPlayer2();
                    perdente = partita.getPlayer1();
                    break;
                }
                System.out.println(partita.getPlayer1() + " SCEGLERE PIETRE DEL NUOVO GOLEM DA SCHIERARE");
                golem1++;
                partita.getPlayer1Golem().get(golem1).setPietre(acquisiciElementiGolem());
            }

            if(partita.getPlayer2Golem().get(golem2).getVita() <= 0){
                if(golem2 == partita.getNumeroGolem())//asseganzione vincitore
                {
                    perdente = partita.getPlayer2();
                    vincitore= partita.getPlayer1();
                    break;
                }
                System.out.println(partita.getPlayer2() + " SCEGLERE PIETRE DEL NUOVO GOLEM DA SCHIERARE");
                golem2++;
                partita.getPlayer2Golem().get(golem2).setPietre(acquisiciElementiGolem());
            }
            pietre++;
            if(pietre == partita.getNumeroPietreGolem() ) pietre=0;
        }while(true);
        System.out.print(end_par);//fine partita
    }


    public void fase3(){//stamoa a video del vincitore
        System.out.println(abb_text);
        System.out.println("\t\t\t\t\t IL VINCITORE E' " + vincitore);//stampa vincitore
        System.out.println(abb_text);
        System.out.println("\t\t\t\t\t IL PERDENTE E' " + perdente);//stampa perdente
        System.out.println(abb_text);

        System.out.println(eql_par);
        System.out.println("--- STAMPA INDICI ELEMENTI PER DECODIFICA TABELLA ---");
        stampaElementi();
        System.out.println("--- TABELLA EQUILIBRIO DI GIOCO ---");
        //equilibrio.printMatrix();//stampa a video dell'equilibrio di gioco_________________!!!!!!!!!!!!!!!!!!!!
    }

    public boolean scelta() {
        char input;
        System.out.println("inserire carattere \n [ " +charUscita+" ] : se si vuole uscire \n [ "+charNuovo+" ] : se si vuole inziare una nuova partita ");
        input = it.unibs.fp.mylib.InputDati.leggiChar("inserire");
        while(input != charUscita &&  input != charNuovo){
            input = it.unibs.fp.mylib.InputDati.leggiChar("rinserire");
        }
        return input != charUscita;
    }

    private void stampaElementi(){
        for(int i = 0 ; i<partita.getNomeElementi().size();i++){
            System.out.println("elemento " + (i+1) + " : " + partita.getNomeElementi().get(i));
        }
    }

    private void stampaSacca(){
        String elmentoSacca = partita.getSacca().get(0);
        int numeroRestante = 1;
        for(int i = 1; i<partita.getSacca().size();i++){
            if (elmentoSacca.equals(partita.getSacca().get(i))){
                numeroRestante++;
            }else{
                System.out.println(elmentoSacca +": "+ numeroRestante);
                numeroRestante = 1;
                elmentoSacca = partita.getSacca().get(i);
            }
        }
        System.out.println(elmentoSacca +": "+ numeroRestante);
    }

    private boolean controlloElemntiGiocatori(int numGolem1,int numGolem2){
        int i=0;
        if(partita.getPlayer1Golem().get(numGolem1).getPietre().get(i) == partita.getPlayer2Golem().get(numGolem2).getPietre().get(i)){
            i++;
            if(partita.getPlayer1Golem().get(numGolem1).getPietre().get(i) == partita.getPlayer2Golem().get(numGolem2).getPietre().get(i)){
                i++;
                if (partita.getPlayer1Golem().get(numGolem1).getPietre().get(i) == partita.getPlayer2Golem().get(numGolem2).getPietre().get(i)){
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String> acquisiciElementiGolem(){
        ArrayList<String>elementi = new ArrayList<>();
        do {
            stampaSacca();
            String elemento;
            boolean isFind=true;
            do {
                elemento = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome pietra numero" + (elementi.size() + 1));
                for (String nomeElemento :partita.getNomeElementi()) {
                    if(elemento.equals(nomeElemento)) isFind = false;
                }
            }while (isFind);
            int i;
            for (i = 0; i < partita.getSacca().size(); i++) {
                if (elemento.equals(partita.getSacca().get(i))) {
                    elementi.add(elemento);
                    break;
                }
            }
            partita.getSacca().remove(i);
        }while(elementi.size() != partita.getNumeroPietreGolem());
        return elementi;
    }

}