// Scenario: A legacy printer that needs to work with a modern computer system.

// Target Interface
interface ModernPrinter {
    void print(String content);
}

// Adaptee - the one that adapts (Legacy Printer)
class LegacyPrinter {
    public void oldPrint(String content) {
        System.out.println("Legacy Printer Printing: " + content);
    }
}

// Adapter Class
class PrinterAdapter implements ModernPrinter {
    private LegacyPrinter legacyPrinter;

    public PrinterAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
    }

    @Override
    public void print(String content) {
        legacyPrinter.oldPrint(content);
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        LegacyPrinter legacyPrinter = new LegacyPrinter();
        ModernPrinter printerAdapter = new PrinterAdapter(legacyPrinter);
        
        printerAdapter.print("Document to print");
    }
}
