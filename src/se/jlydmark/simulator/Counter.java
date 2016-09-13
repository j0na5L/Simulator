package se.jlydmark.simulator;

public class Counter{
   
   int amountOfPassengers;
   boolean busy = false;
   boolean full = false;
   int[] gateArray = new int[6];
   public int gateFill;
   private int boatCheck = 0;
   private final int MAX_PASSENGERS_ALLOWED = 150;
   private int currentValue = 0;
   
   
   public Counter(int currentValue, int[] gateArray){
      this.currentValue = currentValue;
      this.gateArray = gateArray;
   }
   
   
   synchronized boolean setPassengers(int passengers, int name){
      this.boatCheck = this.boatCheck + passengers;
      
      if((this.boatCheck > this.MAX_PASSENGERS_ALLOWED) && (this.currentValue != this.MAX_PASSENGERS_ALLOWED)){
         this.busy = true;
         this.full = false;
         System.out.println("Gate: " + name + "tried: " + passengers);
         System.out.println(this.currentValue);
         notify();
         
      }else if((this.boatCheck == this.MAX_PASSENGERS_ALLOWED) && (this.currentValue != this.MAX_PASSENGERS_ALLOWED)){
         this.currentValue = this.currentValue + passengers;
         this.gateFill = this.gateArray[name - 1];
         this.gateArray[name - 1] = passengers + this.gateFill;
         this.busy = true;
         this.full = true;
         System.out.println("Gate: " + name + ": " + passengers);
         System.out.println("Passengers: " + this.currentValue);
         System.out.println("Gate: " + name + " filled the boat");
         System.out.println("Interrupting " + this.full + " " + "mCurrentValue " + this.currentValue + " " + "boatCheck " + this.boatCheck);
         System.out.println();
         for(int i = 0; i < this.gateArray.length; i++){
            System.out.println("Gate: " + (i + 1) + " " + this.gateArray[i]);
         }
         notify();
         
      }else if(this.currentValue == this.MAX_PASSENGERS_ALLOWED){
         this.busy = true;
         this.full = true;
         notify();
      }else{
         this.currentValue = this.currentValue + passengers;
         this.boatCheck = this.currentValue;
         this.gateFill = this.gateArray[name - 1];
         this.gateArray[name - 1] = passengers + this.gateFill;
         this.busy = true;
         this.full = false;
         System.out.println("Gate: " + name + ": " + passengers);
         System.out.println("Passengers: " + this.currentValue);
         notify();
      }
      this.boatCheck = this.currentValue;
      
      return this.full;
   }
   
   
   synchronized int getPassengers(){
      if(!this.busy) try{
         wait();
      }catch(InterruptedException e){
         System.out.println("Get: InterruptedException");
      }
      this.busy = true;
      notify();
      return this.currentValue;
   }
}
