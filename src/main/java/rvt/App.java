package rvt;

class App {
    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
    // Declaring the background color
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    // Main driver method
    public static void main(String[] args) {
        // Now add the particular background color
        System.out.println(ANSI_RED_BACKGROUND + "The background color is red" + ANSI_RESET);

        System.out.println(ANSI_YELLOW + "This text is yellow" + ANSI_RESET);
    }
}
