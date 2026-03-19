package axel_hernandez;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
public class Importer {
    public void execute(String fileName) {
        List<Transaction> validTransactions = new ArrayList<>();
        int invalidCount = 0;
        try {
            Path path = FileValidator.getSafePath(fileName);
            try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
                String line;
                br.readLine(); 
                while ((line = br.readLine()) != null) {
                    try {
                        Transaction t = parse(line);
                        validTransactions.add(t);
                    } catch (Exception e) {
                        invalidCount++;
                    }
                }
            }
            generateSummary(validTransactions, invalidCount);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    private Transaction parse(String line) {
        String[] parts = line.split(",");
        Money money = new Money(new BigDecimal(parts[2]), parts[3]);
        return new Transaction(parts[0], TxType.valueOf(parts[1]), money, parts[4]);
    }
    private void generateSummary(List<Transaction> list, int invalid) {
        BigDecimal totalIn = list.stream()
            .filter(t -> t.type() == TxType.IN)
            .map(t -> t.money().amount())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("--- RESUMEN DE IMPORTACIÓN ---");
        System.out.println("Válidos: " + list.size());
        System.out.println("Inválidos: " + invalid);
        System.out.println("Total Ingresos: " + totalIn);
    }
}
