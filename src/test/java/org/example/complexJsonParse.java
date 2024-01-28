package org.example;

import io.restassured.path.json.JsonPath;
import org.example.Files.Payload;

public class complexJsonParse {

    public static void main(String[] args){


        JsonPath js = new JsonPath(Payload.courseAPI());


        //No of courses in the Json file
       int count =  js.getInt("courses.size()");
       System.out.println(count);


       //Print purchase amount
        int purchaseAmt = js.get("dashboard.purchaseAmount");
        System.out.println(purchaseAmt);

        //Print title of the first course
        String firstTitle = js.getString("courses[0].title");
        System.out.println(firstTitle);

        //Print all course titles and its prices

        for(int i=0;i<count;i++){
            String title = js.getString("courses" + "[" + i +"]" +".title");
            double price = js.getInt("courses" + "[" + i +"]" +".price");
            System.out.println(title + " has the price of "+price);

        }


        //Print number of copies sold
        int copy = 0;
        for(int i=0;i<count;i++){
            int copies = js.getInt("courses" + "[" + i +"]" +".copies");
            copy = copy + copies;
        }
        System.out.println(copy);

        //Verify the total of prices * copy matches with purchase amount
        int totaL = 0;
        for(int i=0;i<count;i++) {
            int coPY = js.getInt("courses" + "[" + i + "]" + ".copies");
            int priCe = js.getInt("courses" + "[" + i + "]" + ".price");
            int totalP = coPY * priCe;
            totaL = totaL + totalP;
        }

        if (totaL == 910){
            System.out.println("Matches with purshase amount");
        }else{
            System.out.println("No Match");
        }











    }



}
