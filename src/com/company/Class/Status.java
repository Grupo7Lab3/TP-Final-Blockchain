package src.com.company.Class;
import src.com.company.Enums.TransferStatus;

public class Status {

    private int id; // 1 to validated, 2 to pending
    private TransferStatus category;

    public Status() {
    }

    public Status(int id, TransferStatus category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransferStatus getCategory() {
        return category;
    }

    public void setCategory(TransferStatus category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return " " + category;
    }
}
