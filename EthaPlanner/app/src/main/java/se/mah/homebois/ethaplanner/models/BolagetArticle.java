package se.mah.homebois.ethaplanner.models;

/**
 * Created by Simon on 10/23/2016.
 */

public class BolagetArticle {
    public int id;

    public String nr;

    public double Apk;

    public String Producent;

    public String PrisPerLiter;

    public String Ursprung;

    public String Artikelid;

    public String Varugrupp;

    public String Typ;

    public String Forslutning;

    public String Ursprunglandnamn;

    public String Prisinklmoms;

    public String Ekologisk;

    public String Alkoholhalt;

    public String Utgått;

    public String Koscher;

    public String Argang;

    public String Volymiml;

    public String Varnummer;

    public String Namn;

    public String Etiskt;

    public String Forpackning;

    public String Stil;

    public String Leverantor;

    public String Namn2;

    public String Sortiment;

    public String Saljstart;

    public String Provadargang;

    public String RavarorBeskrivning;

    public String Pant;

    public String EtisktEtikett;

    public void calculateAPK() {
        double price = Double.parseDouble(Prisinklmoms);
        double procent = Double.parseDouble(Alkoholhalt.replace("%", ""));
        double ml  = Double.parseDouble(Volymiml);

        Apk = ((procent * 0.01) * ml) / price;
    }
}
