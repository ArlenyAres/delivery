public class Burrito extends Producto {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Burrito() {
        super("Burrito", 6.5);
        System.out.println(ANSI_GREEN+ "¡Pin de regalo con el Burrito!" + ANSI_RESET);
    }
}
