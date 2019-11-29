package com.reshika.firstassignment;

public class Calculations {
    private int room,price,stayedDay;
    private double VAT,TOTAL,GRANDTOTAL;

    public double getVAT(){
        return VAT;
    }

    public double getTOTAL(){
        return TOTAL;
    }

    public double getGRANDTOTAL(){
        return GRANDTOTAL;
    }

    public int getStayedDay(){return stayedDay;}

    public void setStayedDay(int stayedDay ){this.stayedDay=stayedDay;}

    public int getRoom(){
        return room;
    }

    public void setRoom(int room){
        this.room=room;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price=price;
    }

    public double calculate()
    {
        TOTAL=room*stayedDay*price;
        VAT=13/100d*TOTAL;
        GRANDTOTAL=TOTAL+VAT;
        return GRANDTOTAL;
    }
}
