package jsonfactory.createObject;

import com.google.gson.annotations.Expose;

public class CreateObject {

    @Expose
    private Data data;
    @Expose
    private String name;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}