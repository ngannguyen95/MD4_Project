package ra.model.entity;

public class Catalog {
    private int cataId;
    private String cataName;
    private boolean cataStatus;

    public Catalog() {
    }

    public Catalog(String cataName) {
        this.cataName = cataName;
    }

    public Catalog(int cataId, String cataName, boolean cataStatus) {
        this.cataId = cataId;
        this.cataName = cataName;
        this.cataStatus = cataStatus;
    }

    public int getCataId() {
        return cataId;
    }

    public void setCataId(int cataId) {
        this.cataId = cataId;
    }

    public String getCataName() {
        return cataName;
    }

    public void setCataName(String cataName) {
        this.cataName = cataName;
    }

    public boolean isCataStatus() {
        return cataStatus;
    }

    public void setCataStatus(boolean cataStatus) {
        this.cataStatus = cataStatus;
    }
}
