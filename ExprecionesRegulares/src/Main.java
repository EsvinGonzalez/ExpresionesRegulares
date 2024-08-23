import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String texto = "A continuacion se muestra lo que es informacion de 3 personas lo cual " +
                        "contienen nombre de la persona, numero de telefono y Correos electrónicos \n" +

                        "Esvin Gonzalez" +
                        "5523 4677" +
                        "esvingonzalez@gmail.com.\n " +

                        "Jairo Arriola" +
                        "7846-4855" +
                        "JairoArriola@dominio.gt.\n" +

                        "Esteban Garrido"+
                        "5564 9565" +
                        "Esteban@empresa.com.";

        String codigo1 = "//Si nos damos cuenta arriba si nos mostro solo los correos\n";
        String codigo2 = "//Le solicitamos que nos mostrara los correos y si nos lo mostro\n";



        String fechaValida = "23/08/2024";
        String fechaInvalida = "25/40/2024";

        System.out.println("Correos encontrados: " + extraerCorreos(texto));
        System.out.println("Comentarios encontrados: " + extraerComentarios(codigo1));
        System.out.println("Comentarios encontrados: " + extraerComentarios(codigo2));
        System.out.println("Fecha " + fechaValida + " es válida: " + validarFecha(fechaValida));
        System.out.println("Fecha " + fechaInvalida + " es válida: " + validarFecha(fechaInvalida));
    }

    public static String extraerCorreos(String texto) {
        String patronCorreo = "[a-zA-Z._-]+@[a-zA-Z._-]+\\.[a-zA-Z]+";
        Pattern pattern = Pattern.compile(patronCorreo);
        Matcher matcher = pattern.matcher(texto);

        StringBuilder correos = new StringBuilder();
        while (matcher.find()) {
            correos.append(matcher.group()).append("\n");
        }
        return correos.toString();
    }

    public static String extraerComentarios(String codigo) {
        String patronComentarioSimple = "//.*?$";
        String patronComentarioMultilinea = "/\\*.*?\\*";

        Pattern patternSimple = Pattern.compile(patronComentarioSimple, Pattern.MULTILINE);
        Pattern patternMultilinea = Pattern.compile(patronComentarioMultilinea, Pattern.DOTALL);

        Matcher matcherSimple = patternSimple.matcher(codigo);
        Matcher matcherMultilinea = patternMultilinea.matcher(codigo);

        StringBuilder comentarios = new StringBuilder();

        while (matcherSimple.find()) {
            comentarios.append(matcherSimple.group()).append("\n");
        }

        while (matcherMultilinea.find()) {
            comentarios.append(matcherMultilinea.group()).append("\n");
        }

        return comentarios.toString();
    }

    public static boolean validarFecha(String fecha) {
        String patronFecha = "(2[1-9]|8-+9*[0-2])/(0[1-9]|[12][0-9]|3[01])/([0-9]{4})";
        Pattern pattern = Pattern.compile(patronFecha);
        Matcher matcher = pattern.matcher(fecha);

        return matcher.matches();
    }
}
