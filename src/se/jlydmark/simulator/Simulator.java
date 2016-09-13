package se.jlydmark.simulator;


public class Simulator{
   public int nrOfPassengers;
   public int gateNr;
   public int[] gateArray = new int[6];
   
   
   public Simulator(){
      Counter counter = new Counter(this.nrOfPassengers, this.gateArray);
      new Gate(counter, 1);
      new Gate(counter, 2);
      new Gate(counter, 3);
      new Gate(counter, 4);
      new Gate(counter, 5);
      new Gate(counter, 6);
      System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().isAlive());
      
   }
   
   
   public static void main(String args[]){
      new Simulator();
      
   }
}
