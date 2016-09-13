package se.jlydmark.simulator;

import java.util.Random;


public class Gate implements Runnable{
   
   Counter counter;
   private int passengers;
   private int gateNumber;
   int FINAL_MAX_PASSENGERS = 1500;
   Random randomGen = new Random();
   int gatePassengers = 0;
   int totalPassengers = 0;
   public boolean counterBoolean = false;
   public boolean fillBoat = false;
   
   
   public Gate(Counter counter, int gateNumber){
      this.counter = counter;
      this.gateNumber = gateNumber;
      new Thread(this).start();
      System.out.println("Gate " + gateNumber + " started");
      
   }
   
   
   public void run(){
      try{
         Thread.sleep(5);
      }catch(InterruptedException e){
         e.printStackTrace();
      }
      while(this.fillBoat == this.counterBoolean){
         if(this.gateNumber >= 4){
            this.passengers = setCarPassengers();
            try{
               Thread.sleep(this.randomGen.nextInt(500));
            }catch(InterruptedException e){
               e.printStackTrace();
            }
         }else{
            this.passengers = setPeopleGate();
            try{
               Thread.sleep(this.randomGen.nextInt(100));
            }catch(InterruptedException e){
               e.printStackTrace();
            }
            
         }
         this.counterBoolean = this.counter.setPassengers(this.passengers, this.gateNumber);
      }
   }
   
   
   public int setPeopleGate(){
      int leaveOrStay;
      leaveOrStay = this.randomGen.nextInt(10);
      if(this.counter.getPassengers() == 0){
         return 1;
      }
      if(leaveOrStay >= 3){
         leaveOrStay = 1;
      }else{
         leaveOrStay = -1;
      }
      return leaveOrStay;
   }
   
   
   public int setCarPassengers(){
      this.passengers = this.randomGen.nextInt(5) + 1;
      return this.passengers;
      
   }
}
