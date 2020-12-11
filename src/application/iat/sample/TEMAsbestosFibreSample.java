package application.iat.sample;

public class TEMAsbestosFibreSample {
    String number;
    String location;
    String PCMConcentration;
    String TEMConcetration;
    String asbestosTypes;

    TEMAsbestosFibreSample(){
        this.number="";
        this.location="";
        this.PCMConcentration="";
        this.TEMConcetration="";
        this.asbestosTypes="";
    }

    public TEMAsbestosFibreSample(String n, String l, String PCM, String TEM, String aT){
        this.number=n;
        this.location=l;
        this.PCMConcentration=PCM;
        this.TEMConcetration=TEM;
        this.asbestosTypes=aT;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPCMConcentration() {
        return PCMConcentration;
    }

    public void setPCMConcentration(String PCMConcentration) {
        this.PCMConcentration = PCMConcentration;
    }

    public String getTEMConcetration() {
        return TEMConcetration;
    }

    public void setTEMConcetration(String TEMConcetration) {
        this.TEMConcetration = TEMConcetration;
    }

    public String getAsbestosTypes() {
        return asbestosTypes;
    }

    public void setAsbestosTypes(String asbestosTypes) {
        this.asbestosTypes = asbestosTypes;
    }
}
