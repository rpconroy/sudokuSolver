import java.util.*;
public class Board
{

   private int boardDimensions = 9;
	//ArrayList<ArrayList<Tile>> tiles;
   private Tile tiles[][] = new Tile[boardDimensions][boardDimensions];
   private Map<Integer, List<Tile>> localGroupMap = new HashMap<Integer, List<Tile>>(); 
   private Map<Integer, List<Tile>> colMap = new HashMap<Integer, List<Tile>>();
   private Map<Integer, List<Tile>> rowMap = new HashMap<Integer, List<Tile>>();

   public Board()
   {
      for(int i=0; i < boardDimensions;i++)
      {
         localGroupMap.put(new Integer(i), new ArrayList<Tile>());
         rowMap.put(new Integer(i), new ArrayList<Tile>());
         colMap.put(new Integer(i), new ArrayList<Tile>());
      }
      for(int i=0; i < boardDimensions; i++)
      {
         for(int j=0; j < boardDimensions; j++)
         {
            int localGroup = ((i/3)*3) + (j/3);
            System.out.println(i+" "+j+" "+localGroup);
            Tile curTile = new Tile(i,j,localGroup);
            tiles[i][j] = curTile;
            localGroupMap.get(localGroup).add(curTile);
            rowMap.get(i).add(curTile);
            colMap.get(j).add(curTile);
         
         
         }
      }	
   }
   public void setValue(int i,int j,int value)
   {
      tiles[i][j].setValue(value);
   }
   public Tile getTile(int i,int j)
   {
      return tiles[i][j];
   }
   public List<Tile> getLocalGroupTiles(int localGroup)
   {
      return localGroupMap.get(localGroup);
   }
   public List<Tile> getRowTiles(int row)
   {
      return rowMap.get(row);
   }
   public List<Tile> getColTiles(int column)
   {
      return colMap.get(column);
   }

   public String toString()
   {
      String boardString = "";
      for(int i=0; i < boardDimensions; i++)
      {
         if( i%3 == 0) 
            boardString += "===================\n";
      
         
         boardString += "|";
         for(int j=0; j < boardDimensions; j++)
         {
            Tile curTile = tiles[i][j];
            int tileValue = curTile.getValue();
            if(tileValue != -1)
            {
               boardString += tileValue;
            }
            else
            {
               boardString += " ";
            }
            if( (j+1)%3 == 0)
               boardString += "|";
            else
               boardString += " ";
         }
         boardString += "\n";
         
      }
      boardString += "===================\n";
      return boardString;
   }
}
