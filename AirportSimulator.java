// Sai Veerabhadra Hithesh Medavarapu
// N04333402

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.List;

public class AirportSimulator {
    public static void main(String args[]){
        
        Queue<Integer> landing = new LinkedList<Integer>();
        Queue<Integer> takeoff = new LinkedList<Integer>();

        List<Integer> averageTakeoff = new ArrayList<Integer>();
        List<Integer> averageLand = new ArrayList<Integer>();

        Scanner sc =  new Scanner(System.in);

        System.out.println("Amount of minutes to land: ");
        int minLand = sc.nextInt();
        System.out.println("Amount of minutes to take off: ");
        int minTakeoff = sc.nextInt();
        System.out.println("Probability of arrival during a minute: ");
        double probLand = sc.nextDouble();
        System.out.println("Average amount of time between planes to land: ");
        double avgLand = sc.nextDouble();
        System.out.println("Probability of departure during a minute: ");
        double probTakeoff = sc.nextDouble();
        System.out.println("Average amount of time between planes to land: ");
        double avgTakeoff = sc.nextDouble();
        System.out.println("Maximum amount of time in the air before crashing: ");
        int crashTime = sc.nextInt();
        System.out.println("Total simulation minutes: ");
        int Total = sc.nextInt();

        int noCrash = 0;
        int runway=0;
        
        

        for(int i=0; i<Total; i++){
            
            int currTime = i;

            //BooleanSource prLand = new BooleanSource(probLand);
            //BooleanSource prTakeoff = new BooleanSourc(probTakeoff);

            boolean prLand = BooleanSource1(probLand);
            boolean prTakeoff = BooleanSource2(probTakeoff);

            

            if(prLand){
                landing.add(currTime);
            }
            if(prTakeoff){
                takeoff.add(currTime);
            }       

            
            if(runway <= 0){

                while(!landing.isEmpty() && currTime - landing.peek() > crashTime){
                    landing.remove();
                    noCrash++;
                }

                if (!landing.isEmpty()) {
                    int arrTime = landing.poll();
                    runway = minLand;
                        averageLand.add(currTime - arrTime);
                    }
                

                
                else if(!takeoff.isEmpty()){
                    
                    runway = minTakeoff;
                    int takeTime = takeoff.peek();
                    takeoff.remove();
                    averageTakeoff.add(currTime - takeTime);
                }
                
            }
            if(runway>=0){
                --runway;
            }  
        }


        System.out.println("Number of planes taken off: " +averageTakeoff.size());
        System.out.println("Number of planes landed: " +averageLand.size());
        System.out.println("Number of planes crashed: " +noCrash);
        System.out.println("Average waiting time for taking off: " + String.format("%.2f", calculateAverage(averageTakeoff))+ " minutes");
        System.out.println("Average waiting time for landing: " +String.format("%.2f", calculateAverage(averageLand))+ " minutes");
        

    }
    public static boolean BooleanSource1(double prob) {
        
        return (Math.random( ) < prob);
    }

    public static boolean BooleanSource2(double prob) {
        
        return (Math.random( ) < prob);    
    }

    public static double calculateAverage(List <Integer> avg) {
        double sum = 0;
        for (int i=0; i< avg.size(); i++) {
            sum += avg.get(i);
        }
        return sum / avg.size();
    }
}



