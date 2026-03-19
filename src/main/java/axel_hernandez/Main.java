package axel_hernandez;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Importador CSV Seguro - Axel Hernandez");
        
        Importer importer = new Importer();
        importer.execute("test.csv");
    }
}
