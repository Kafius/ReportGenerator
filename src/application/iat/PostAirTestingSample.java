package application.iat;

public class PostAirTestingSample {
    String number;
    String location;
    String volume;
    String airborneFibreConc;

    PostAirTestingSample(){
        this.number = "";
        this.location = "";
        this.volume = "";
        this.airborneFibreConc = "";
    }

    PostAirTestingSample(String n, String l, String v,String aFC){
        this.number=n;
        this.location=l;
        this.volume=v;
        this.airborneFibreConc=aFC;
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
