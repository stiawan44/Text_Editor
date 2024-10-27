import java.util.Stack;

class TextEditor {
    private Stack<String> undoStack;
    private Stack<String> redoStack;
    private String currentText;

    public TextEditor() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        currentText = "";
    }

    public void write(String text) {
        saveState();
        if (currentText.isEmpty()) {
            currentText = text;
        } else {
            currentText += " " + text;
        }
        redoStack.clear();
    }

    public void show() {
        if (currentText.isEmpty()) {
            System.out.println("text editor kosong.");
        } else {
            System.out.println("Teks Sekarang Menjadi: " + currentText);
        }
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("tidak bisa undo!");
            return;
        }
        redoStack.push(currentText);
        currentText = undoStack.pop();
        System.out.println("Undo berhasil!");
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("tidak bisa redo!");
            return;
        }
        undoStack.push(currentText);
        currentText = redoStack.pop();
        System.out.println("Redo berhasil");
    }

    private void saveState() {
        undoStack.push(currentText);
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();


        System.out.println("Tes Write Pertama Kali");
        editor.write("Aku Suka Pelajaran Matematika");
        editor.show();


        System.out.println("\nMelanjutkan Write Kedua");
        editor.write("Karena Mengasyikkan");
        editor.show();


        System.out.println("\nTes undo kembali ke Pertama Write");
        editor.undo();
        editor.show();

        // Langkah 4: Redo dan show
        System.out.println("\nStep 4: Test Redo Memulihkan Write kedua");
        editor.redo();
        editor.show();
    }
}