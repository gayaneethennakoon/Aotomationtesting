package methodChaining;

public class Tour {

    public String place;
    public String modeOfTravel;

    public String getPlace() {
        return place;
    }

    public Tour setPlace(String place) {
        this.place = place;
        return this;
    }

    public String getModeOfTravel() {
        return modeOfTravel;
    }

    public Tour setModeOfTravel(String modeOfTravel) {
        this.modeOfTravel = modeOfTravel;
        return this;
    }

    public void heyManWhereAreYouGoing(){
        System.out.println(getPlace());
         System.out.println("Ooh! Are you going "+getPlace()+ " with fun. Going by "+getModeOfTravel());

    }

    public static void main(String[] args) {
        Tour Tour =new Tour();
        Tour.setPlace("America").setModeOfTravel("Flight").heyManWhereAreYouGoing();

    }
}
