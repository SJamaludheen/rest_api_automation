package jsonfactory.getObject;

import com.google.gson.annotations.Expose;

public class GetObject {

    @Expose
    private Data data;
    @Expose
    private String id;
    @Expose
    private String name;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
