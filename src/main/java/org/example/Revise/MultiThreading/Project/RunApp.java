package org.example.Revise.MultiThreading.Project;

public class RunApp {

    //    pobieranie ofert mieszkan z serwisu oto dom
//    porwonanie czasu dzialania aplikacji - jedno i wielowatkowo
    public static void main(String[] args) {
        try{
            OtoDom otoDom = new OtoDom();
            otoDom.getFlats();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
