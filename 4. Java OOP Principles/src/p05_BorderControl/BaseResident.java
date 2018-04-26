package p05_BorderControl;

public abstract class BaseResident implements Identifiable {
    private String name;
    private String id;

    protected BaseResident(String name, String id) {
        this.setName(name);
        this.setId(id);
    }

    @Override
    public String getId() {
        return id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setId(String id) {
        this.id = id;
    }
}
