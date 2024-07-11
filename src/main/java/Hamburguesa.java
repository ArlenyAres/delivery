public class Hamburguesa extends Producto {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Hamburguesa() {
        super( "Hamburguesa", 8.90);
        System.out.println(ANSI_GREEN + "Gorra e regalo con la Hamburguesa" + ANSI_RESET);
    }
}
