package jsonfactory.createObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("CPU model")
    private String cPUModel;
    @SerializedName("Hard disk size")
    private String hardDiskSize;
    @Expose
    private Double price;
    @Expose
    private Long year;

    public String getCPUModel() {
        return cPUModel;
    }

    public void setCPUModel(String cPUModel) {
        this.cPUModel = cPUModel;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }

    public void setHardDiskSize(String hardDiskSize) {
        this.hardDiskSize = hardDiskSize;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

}
