import java.util.*;
public class Tile
{
   private int value;
   private int x;
   private int y;
   private int localGroup;
   private List<Integer> possibilities; 


   public Tile(int x,int y, int localGroup)
   {
      this.value = -1;
      this.x = x;
      this.y = y;
      this.localGroup = localGroup;
      this.possibilities = new ArrayList<Integer>();
   }
   public Tile(int value, int x,int y, int localGroup)
   {
      this.x = x;
      this.y = y;
      this.localGroup = localGroup;
      this.possibilities = new ArrayList<Integer>();
      this.value = value;
      
   }


   public int getX()
   {
      return this.x;
   }
   public int getY()
   {
      return this.y;
   }
   public int getLocalGroup()
   {
      return this.localGroup;
   }
   public int getValue()
   {
      return this.value;
   }
   public void setValue(int value)
   {
      this.value = value;
   }
   public void setPossibilities(List<Integer> pos)
   {
      this.possibilities = pos;
   }
   public List<Integer> getPossibilities()
   {
      return this.possibilities;    
   }
   public String toString()
   {
      String returnString = "";
      returnString += "\nrow = " + x; 
      returnString += "\ncol = " + y; 
      returnString += "\ngrp = " + localGroup; 
      returnString += "\npossible = " + possibilities; 
      return returnString;
   }

}
