package application.iat.sample;

public class AirTestingSample {
    String date;
    String type;
    String number;
    String location;
    String volume;
    String airborneFibreConc;

    AirTestingSample(){
        this.date="";
        this.type="";
        this.number="";
        this.location="";
        this.volume="";
        this.airborneFibreConc="";
    }

    public AirTestingSample(String d, String t, String n, String l, String v, String aFC){
        this.date=d;
        this.type=t;
        this.number=n;
        this.location=l;
        this.volume=v;
        this.airborneFibreConc=aFC;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getAirborneFibreConc() {
        return airborneFibreConc;
    }

    public void setAirborneFibreConc(String airborneFibreConc) {
        this.airborneFibreConc = airborneFibreConc;
    }
}
