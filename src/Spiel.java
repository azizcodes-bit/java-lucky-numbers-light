import java.util.Scanner;
public class Spiel {
 private static Scanner sc=new Scanner(System.in);
 public static Spieler spieler1,spieler2;
 public static Zahlenstapel stapel;
 public static void main(String[] args){
  System.out.println("Herzlich Willkommen zu Lucky Numbers Light");
  int eingabe;
  do{eingabe=menu();if(eingabe==1){spielVorbereiten();spielen();}}while(eingabe!=2);
  System.out.println("Programm beendet.");sc.close();
 }
 public static int menu(){
  int zahl;
  do{
   System.out.println("Lucky Numbers Light");
   System.out.println("1 - Spiel starten");
   System.out.println("2 - Programm beenden");
   zahl=liesZahl(sc);
  }while(!checkEingabeMenu(zahl));
  return zahl;
 }
 public static boolean checkEingabeMenu(int eingabe){return eingabe==1||eingabe==2;}
 public static void spielVorbereiten(){
  System.out.println("Gib den Namen von Spieler 1 ein:");
  String name1=sc.nextLine();
  System.out.println("Gib den Namen von Spieler 2 ein:");
  String name2=sc.nextLine();
  System.out.println("Wie gross soll das Spielfeld sein?");
  int n;
  do{n=liesZahl(sc);if(n<2||n>10)System.out.println("Bitte eine Groesse von 2 bis 10 eingeben.");}while(n<2||n>10);
  stapel=new Zahlenstapel(6*n,n-1);
  spieler1=new Spieler(name1,n);spieler2=new Spieler(name2,n);
 }
 public static void spielen(){
  Spieler aktuellerSpieler=spieler1;
  do{
   spielzug(aktuellerSpieler);
   aktuellerSpieler=aktuellerSpieler==spieler1?spieler2:spieler1;
  }while(!spieler1.getSpielfeld().istVoll()&&!spieler2.getSpielfeld().istVoll()&&!stapel.istLeer());
  System.out.println(auswertung());
 }
 public static void spielzug(Spieler spieler){
  System.out.println("--------------------------------");
  System.out.println(spieler.getName()+" ist an der Reihe.");
  System.out.println("Spielfeld von "+spieler.getName()+":");
  System.out.println(spieler.getSpielfeld());
  int zahl=stapel.zieheZahl();
  System.out.println("Gezogene Zahl :"+zahl);
  System.out.println("Anzahl uebrige Zahlen im Stapel: "+stapel.anzahlUebrigeZahlen());
  System.out.println("Moechtest du die Zahl verwenden j/ja/n/nein)?");
  String eingabe;
  do{
   eingabe=sc.nextLine().trim().toLowerCase();
   if(!eingabe.equals("j")&&!eingabe.equals("ja")&&!eingabe.equals("n")&&!eingabe.equals("nein"))
    System.out.println("Bitte j/ja/n/nein eingeben.");
  }while(!eingabe.equals("j")&&!eingabe.equals("ja")&&!eingabe.equals("n")&&!eingabe.equals("nein"));
  if(eingabe.equals("j")||eingabe.equals("ja"))zahlSetzen(spieler,zahl);
  else System.out.println("Die Zahl "+zahl+" wurde verworfen.");
 }
 public static void zahlSetzen(Spieler spieler,int zahl){
  boolean gesetzt=false,verworfen=false;
  do{
   System.out.print("Zeile: ");int zeile=liesZahl(sc);
   System.out.print("Spalte: ");int spalte=liesZahl(sc);
   if(spieler.getSpielfeld().gueltigeEingabe(zeile,spalte,zahl)){
    spieler.getSpielfeld().setStelle(zeile,spalte,zahl);
    gesetzt=true;System.out.println("Die zahl "+zahl+" wurde gesetzt");
   }else{
    String eingabe;
    do{
     System.out.println("Diese Eingabe ist nicht gueltig.");
     System.out.println("Andere Position versuchen? (j/ja/n/nein)");
     System.out.println("Bei n/nein wird die Zahl verworfen.");
     eingabe=sc.nextLine();
    }while(!eingabe.equals("j")&&!eingabe.equals("ja")&&!eingabe.equals("n")&&!eingabe.equals("nein"));
    if(eingabe.equals("n")||eingabe.equals("nein")){
     verworfen=true;System.out.println("Die Zahl "+zahl+" wurde verworfen.");
    }
   }
  }while(!gesetzt&&!verworfen);
 }
 public static String auswertung(){
  if(spieler1.getSpielfeld().istVoll())return spieler1.getName()+" hat gewonnen!";
  if(spieler2.getSpielfeld().istVoll())return spieler2.getName()+" hat gewonnen!";
  return "Der Stapel ist leer. Niemand hat gewonnen.";
 }
 private static int liesZahl(Scanner scanner){
  while(true)try{return Integer.parseInt(scanner.nextLine().trim());}
  catch(NumberFormatException e){System.out.println("Bitte eine ganze Zahl eingeben.");}
 }
}