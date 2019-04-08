package sample;

import javafx.beans.property.SimpleStringProperty;

public class TicketInfo {
    private final SimpleStringProperty train;
    private final SimpleStringProperty fromTime;
    private final SimpleStringProperty toTime;
    private final SimpleStringProperty costTime;
    private final SimpleStringProperty firstClass;
    private final SimpleStringProperty secondClass;
    private final SimpleStringProperty softBed;
    private final SimpleStringProperty hardBed;
    private final SimpleStringProperty hardSeat;
    private final SimpleStringProperty noSeat;
    private final SimpleStringProperty advanceBed;

    public TicketInfo(String train, String fromTime, String toTime, String costTime, String firstClass, String secondClass, String softBed, String hardBed, String hardSeat,String noSeat,String advanceBed) {
        this.train = new SimpleStringProperty(train);
        this.fromTime = new SimpleStringProperty(fromTime);
        this.toTime = new SimpleStringProperty(toTime);
        this.costTime = new SimpleStringProperty(costTime);
        this.firstClass = new SimpleStringProperty(firstClass);
        this.secondClass = new SimpleStringProperty(secondClass);
        this.softBed = new SimpleStringProperty(softBed);
        this.hardBed = new SimpleStringProperty(hardBed);
        this.hardSeat = new SimpleStringProperty(hardSeat);
        this.noSeat = new SimpleStringProperty(noSeat);
        this.advanceBed = new SimpleStringProperty(advanceBed);
    }

    public String getTrain() {
        return train.get();
    }

    public SimpleStringProperty trainProperty() {
        return train;
    }

    public String getHardSeat() {
        return hardSeat.get();
    }

    public SimpleStringProperty hardSeatProperty() {
        return hardSeat;
    }

    public void setHardSeat(String hardSeat) {
        this.hardSeat.set(hardSeat);
    }

    public String getFromTime() {
        return fromTime.get();
    }

    public SimpleStringProperty fromTimeProperty() {
        return fromTime;
    }

    public String getToTime() {
        return toTime.get();
    }

    public SimpleStringProperty toTimeProperty() {
        return toTime;
    }

    public String getCostTime() {
        return costTime.get();
    }

    public SimpleStringProperty costTimeProperty() {
        return costTime;
    }

    public String getFirstClass() {
        return firstClass.get();
    }

    public SimpleStringProperty firstClassProperty() {
        return firstClass;
    }

    public String getSecondClass() {
        return secondClass.get();
    }

    public SimpleStringProperty secondClassProperty() {
        return secondClass;
    }

    public String getSoftBed() {
        return softBed.get();
    }

    public SimpleStringProperty softBedProperty() {
        return softBed;
    }

    public String getHardBed() {
        return hardBed.get();
    }

    public SimpleStringProperty hardBedProperty() {
        return hardBed;
    }

    public String getNoSeat() {
        return noSeat.get();
    }

    public SimpleStringProperty noSeatProperty() {
        return noSeat;
    }

    public void setTrain(String train) {
        this.train.set(train);
    }

    public void setFromTime(String fromTime) {
        this.fromTime.set(fromTime);
    }

    public void setToTime(String toTime) {
        this.toTime.set(toTime);
    }

    public void setCostTime(String costTime) {
        this.costTime.set(costTime);
    }

    public void setFirstClass(String firstClass) {
        this.firstClass.set(firstClass);
    }

    public void setSecondClass(String secondClass) {
        this.secondClass.set(secondClass);
    }

    public void setSoftBed(String softBed) {
        this.softBed.set(softBed);
    }

    public void setHardBed(String hardBed) {
        this.hardBed.set(hardBed);
    }

    public void setNoSeat(String noSeat) {
        this.noSeat.set(noSeat);
    }

    public void setAdvanceBed(String advanceBed) {
        this.advanceBed.set(advanceBed);
    }

    public String getAdvanceBed() {
        return advanceBed.get();
    }

    public SimpleStringProperty advanceBedProperty() {
        return advanceBed;
    }
}
