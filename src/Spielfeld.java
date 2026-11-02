public class Spielfeld {
 private int[][] feld;private int n;
 public Spielfeld(int n){this.n=n;feld=new int[n][n];}
 public Spielfeld(int[][] feld){this.feld=feld;this.n=feld.length;}
 public int getN(){return n;}
 public int getStelle(int zeile,int spalte){return feld[zeile][spalte];}
 public void setStelle(int zeile,int spalte,int zahl){feld[zeile][spalte]=zahl;}
 public boolean gueltigeEingabe(int zeile,int spalte,int zahl){
  if(zahl<=0||zeile>=n||zeile<0||spalte>=n||spalte<0||feld[zeile][spalte]!=0)return false;
  for(int j=0;j<spalte;j++)if(feld[zeile][j]>zahl)return false;
  for(int j=spalte+1;j<n;j++)if(feld[zeile][j]<zahl&&feld[zeile][j]!=0)return false;
  for(int i=0;i<zeile;i++)if(feld[i][spalte]>zahl)return false;
  for(int i=zeile+1;i<n;i++)if(feld[i][spalte]<zahl&&feld[i][spalte]!=0)return false;
  return true;
 }
 public boolean istVoll(){
  for(int i=0;i<n;i++)for(int j=0;j<n;j++)if(feld[i][j]==0)return false;
  return true;
 }
 public String toString(){
  String ausgabe="     ";
  for(int j=0;j<n;j++)ausgabe+=String.format("%4d",j);
  ausgabe+="\n----+";
  for(int j=0;j<n;j++)ausgabe+="----";
  ausgabe+="\n";
  for(int i=0;i<n;i++){
   ausgabe+=String.format("%3d |",i);
   for(int j=0;j<n;j++)ausgabe+=feld[i][j]==0?String.format("%4s","-"):String.format("%4d",feld[i][j]);
   ausgabe+="\n";
  }
  return ausgabe;
 }
}