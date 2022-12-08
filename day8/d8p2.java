
import java.util.ArrayList ;
import java.util.Comparator ;
public class d8p2 {
    private static class Tree {
	public boolean bVisible ;
	public final int iHeight ;

	public Tree( final int iHeight ) {
	    this.iHeight = iHeight ;
	    this.bVisible = true ;
	    return ;
	}
    }

    public static void main ( final String[] args ) {

	final ArrayList<Integer> scores = new ArrayList<Integer>() ;
	boolean bRowa = false
	    , bRowb = false
	    , bCola = false
	    , bColb = false;

	final Tree[][] trees = new Tree[args[0].split("\n").length][args[0].split("\n")[0].length()]  ;
	int iVisible = 0 ; 
	for ( int i = 0 ; i < args[0].split("\n").length ; i ++ ) {
	    for ( int j = 0 ; j < args[0].split("\n")[0].length() ; j ++ ) {
		trees[i][j] = new Tree( Character.getNumericValue( args[0].split("\n")[i].charAt(j) ) ) ;
	    }
	}

	// For each row
	for ( int row = 0 ; row < trees.length ; row ++ ) {
	    // For each column
	    for ( int col = 0 ; col < trees[row].length ; col ++ ) {
		// Calculate score
		int iNorthScore , iEastScore , iSouthScore , iWestScore ;

		// Calc N
		for ( iNorthScore = row - 1  ; iNorthScore >= 0 ; iNorthScore -- ) {
		    if ( trees[row][col].iHeight < trees[iNorthScore][col].iHeight ) break ; 
		}
		for ( iSouthScore = row + 1 ; iSouthScore < trees.length ; iSouthScore ++ ) {
		    if ( trees[row][col].iHeight < trees[iSouthScore][col].iHeight ) break ; 
		}
		for ( iEastScore = col + 1 ; iEastScore < trees[row].length ; iEastScore ++ ) {
		    if ( trees[row][col].iHeight < trees[row][iEastScore].iHeight ) break ; 
		}
		for ( iWestScore = col - 1 ; iWestScore >= 0 ; iWestScore -- ) {
		    if ( trees[row][col].iHeight < trees[row][iWestScore].iHeight ) break ; 
		}

		iNorthScore ++ ;
		iEastScore ++ ;
		iWestScore ++ ;
		iSouthScore ++ ;

		scores.add( iNorthScore * iEastScore * iWestScore * iSouthScore ) ;
		
		System.out.printf( "(%d,%d) :: %n  N: %d %n  E: %d %n  S: %d %n  W: %d %n%n",
				   row , col
				   , iNorthScore , iEastScore, iSouthScore , iWestScore ) ; 
		
	    }
	}
	scores.sort( Comparator.reverseOrder() ) ;
	System.out.println( scores.get( 0 ) ) ;
    }
}
