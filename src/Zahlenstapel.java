import java.util.Random;
public class Zahlenstapel {
 private int[] zahlen;private int naechsterIndex;private Random rd;
 public Zahlenstapel(int maxZahl,int anzahlProZahl){
  zahlen=new int[maxZahl*anzahlProZahl];naechsterIndex=0;rd=new Random();
  initialisiere(maxZahl,anzahlProZahl);mischen();
 }
 public void initialisiere(int maxZahl,int anzahlProZahl){
  int index=0;
  for(int zahl=1;zahl<=maxZahl;zahl++)for(int i=0;i<anzahlProZahl;i++)zahlen[index++]=zahl;
 }
 public void mischen(){
  for(int i=zahlen.length-1;i>=1;i--){
   int j=rd.nextInt(i+1),temp=zahlen[i];zahlen[i]=zahlen[j];zahlen[j]=temp;
  }
 }
 public int getZahl(int index){return zahlen[index];}
 public int zieheZahl(){return istLeer()?-1:zahlen[naechsterIndex++];}
 public boolean istLeer(){return naechsterIndex>=zahlen.length;}
 public int anzahlUebrigeZahlen(){return zahlen.length-naechsterIndex;}
}