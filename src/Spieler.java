class Spieler {
 private String name;private Spielfeld sf;
 public Spieler(String name,int n){this.name=name;sf=new Spielfeld(n);}
 public String getName(){return name;}
 public Spielfeld getSpielfeld(){return sf;}
}