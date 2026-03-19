package axel_hernandez;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("=== EJECUTANDO PRUEBAS DE AXEL ===");
        testPathTraversal();
        System.out.println("=== PRUEBAS FINALIZADAS ===");
    }

    private static void testPathTraversal() {
        System.out.print("Caso 1: Bloqueo Path Traversal... ");
        try {
            FileValidator.getSafePath("../etc/passwd");
            System.out.println("FALLÓ (No bloqueó el acceso)");
        } catch (SecurityException e) {
            System.out.println("PASÓ (Acceso denegado correctamente)");
        } catch (Exception e) {
            System.out.println("ERROR INESPERADO: " + e.getMessage());
        }
    }
}
