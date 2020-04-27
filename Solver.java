import java.util.*;
import java.lang.*;
public class Solver
{

   public static void main(String[] args)
   {
   
      /*int[] testi = {0,0,1,1,1,1,1,2,2,3,3,3,3,4,4,4,4,4,5,5,5,5,6,6,7,7,7,7,7,8,8};
      int[] testj = {1,4,2,4,6,7,8,2,5,1,3,6,8,0,3,4,5,8,0,2,5,7,3,6,0,1,2,4,6,4,7};
      int[] testv = {1,5,3,1,6,5,9,6,4,6,1,5,8,9,3,8,5,6,1,5,6,2,8,3,6,2,7,3,8,4,6};
      */
      //evil below
      int[] testi = {0,0,0,1,1,1,1,1,2,2,3,3,3,5,5,5,6,6,7,7,7,7,7,8,8,8};
      int[] testj = {2,3,5,0,2,3,4,5,0,6,0,1,6,2,7,8,2,8,3,4,5,6,8,3,5,6};
      int[] testv = {8,5,1,7,5,6,8,9,2,5,9,5,1,7,8,4,4,1,1,9,2,3,7,7,4,8};
      Board board = new Board();
      for(int i=0; i<testi.length;i++)
      {
         board.setValue(testi[i],testj[i],testv[i]);
      }
      System.out.println(board);
      Board possibilityBoard = basicSolver(board);
      Board moreCorrectBoard = rachelSolver(possibilityBoard);
      Board moremoreCorrectBoard = basicSolver(moreCorrectBoard);
      moremoreCorrectBoard = rachelSolver(moreCorrectBoard);
      
      moremoreCorrectBoard = basicSolver(moremoreCorrectBoard);
      System.out.println(moremoreCorrectBoard);
      //System.out.println(moreCorrectBoard);
   
   
   }

   public static Board rachelSolver(Board board) 
   {
      for(int j=0; j < 9; j++)
      {
         List<Tile> rowTiles = board.getRowTiles(j);
         ArrayList<Integer> allPos = new ArrayList<Integer>();
         for(Tile t : rowTiles)
         {
            if(t.getValue() == -1)
            {
               allPos.addAll(t.getPossibilities());
            }
         }
         for(Tile t : rowTiles)
         {
            if(t.getValue() == -1)
            {
               for(Integer mmk : t.getPossibilities())
               {
                  int occurrences = Collections.frequency(allPos, mmk);
                  if( occurrences == 1)
                  {
                     System.out.println("found something "+t);
                     t.setValue((int)(mmk));
                     List<Tile> shared = getAllSharedTiles(board, t);
                     removePossibility(shared, (int)(mmk));
                     System.out.println(board);
                     break;
                  //int a = 23/0;
                  }
               }
            }
         }
      }
      System.out.println("##############################");
      for(int j=0; j < 9; j++)
      {
         List<Tile> colTiles = board.getColTiles(j);
         ArrayList<Integer> allPos = new ArrayList<Integer>();
         for(Tile t : colTiles)
         {
            if(t.getValue() == -1)
            {
               allPos.addAll(t.getPossibilities());
            }
         }
         for(Tile t : colTiles)
         {
            if(t.getValue() == -1)
            {
               for(Integer mmk : t.getPossibilities())
               {
                  int occurrences = Collections.frequency(allPos, mmk);
                  if( occurrences == 1)
                  {
                     System.out.println("found something "+t);
                     t.setValue((int)(mmk));
                     List<Tile> shared = getAllSharedTiles(board, t);
                     removePossibility(shared, (int)(mmk));
                     System.out.println(board);
                     break;
                  //int a = 23/0;
                  }
               }
            }
         }
      }
      System.out.println("####################################");
 System.out.println("##############################");
      for(int j=0; j < 9; j++)
      {
         List<Tile> lgTiles = board.getLocalGroupTiles(j);
         ArrayList<Integer> allPos = new ArrayList<Integer>();
         for(Tile t : lgTiles)
         {
            if(t.getValue() == -1)
            {
               allPos.addAll(t.getPossibilities());
            }
         }
         for(Tile t : lgTiles)
         {
            if(t.getValue() == -1)
            {
               for(Integer mmk : t.getPossibilities())
               {
                  int occurrences = Collections.frequency(allPos, mmk);
                  if( occurrences == 1)
                  {
                     System.out.println("found something "+t);
                     t.setValue((int)(mmk));
                     List<Tile> shared = getAllSharedTiles(board, t);
                     removePossibility(shared, (int)(mmk));
                     System.out.println(board);
                     break;
                  //int a = 23/0;
                  }
               }
            }
         }
      }

      return board;
   }
   public static Board basicSolver(Board board) 
   {
      boolean found = true;
      int a=0;
      while(found)
      {
         found = false;
         for(int i=0; i < 9; i++)
         {
            for(int j=0; j < 9; j++)
            {
               Tile curTile = board.getTile(i,j);
               if(curTile.getValue() == -1)
               {
                  List<Integer> possible = getPossibilities(board, curTile);
                  curTile.setPossibilities(possible);
                  if(possible.size() == 0)
                  {
                     System.out.println(curTile);
                     a = 34/0;
                  }
                  if(possible.size() == 1)
                  {
                     System.out.println("found something"+i+" "+j);
                     found=true;
                     curTile.setValue((int)(possible.get(0)));
                     System.out.println(board);
                  //a = 34/0;
                  }
               }
            }
         }
      }
      return board;
   }
   public static List<Tile> getAllSharedTiles(Board board, Tile tile)
   {
      List<Tile> allSharedTiles = new ArrayList();
   
      List<Tile> rowTiles = board.getRowTiles(tile.getX());
      List<Tile> colTiles = board.getColTiles(tile.getY());
      List<Tile> lgTiles = board.getLocalGroupTiles(tile.getLocalGroup());
      
      allSharedTiles.addAll(rowTiles);
      allSharedTiles.addAll(colTiles);
      allSharedTiles.addAll(lgTiles);
      
      return allSharedTiles;
   }
   public static void removePossibility(List<Tile> tiles, int poss)
   {
      for(Tile t : tiles)
      {
         List<Integer> posses = t.getPossibilities();
         if(posses.contains(poss))
         {
            t.getPossibilities().remove(new Integer(poss));
         }
      }
   }
   
   public static List<Integer> getPossibilities(Board board, Tile tile)
   {
      
      List<Integer> initialPossibilities = new ArrayList<Integer>(
            Arrays.asList(1,2,3,4,5,6,7,8,9)
            );
      List<Tile> rowTiles = board.getRowTiles(tile.getX());
      List<Tile> colTiles = board.getColTiles(tile.getY());
      List<Tile> allSharedTiles = new ArrayList(rowTiles);
      allSharedTiles.addAll(colTiles);
      allSharedTiles.addAll(board.getLocalGroupTiles(tile.getLocalGroup()));
      for(Tile t : allSharedTiles)
      {
         initialPossibilities.remove(new Integer(t.getValue()));
      }
     
      // board.getGetColTiles(tile.getY())
      return initialPossibilities;
   }
}
