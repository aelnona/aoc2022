public class d8p1 {
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
	for ( int row = 1 ; row < trees.length - 1 ; row ++ ) {
	    for ( int col = 1 ; col < trees[row].length - 1 ; col ++ ) {
		bRowa = false ; bRowb = false ; bCola = false ; bColb = false ; // True if blocked
		// Check row
		for ( int i = 0 ; i < col ; i ++ ) {
		    if ( trees[row][i].iHeight >= trees[row][col].iHeight ) bRowa = true ;
		}
		for ( int i = col + 1 ; i < trees[row].length ; i ++ ) {
		    if ( trees[row][i].iHeight >= trees[row][col].iHeight ) bRowb = true ; 
		}
		// Check column
		for ( int i = 0 ; i < row ; i ++ ) {
		    if ( trees[i][col].iHeight >= trees[row][col].iHeight ) bCola = true ;
		}
		for ( int i = row + 1 ; i < trees.length ; i ++ ) {
		    if ( trees[i][col].iHeight >= trees[row][col].iHeight ) bColb = true ;
		}
		// if not all blocked, visible
		trees[row][col].bVisible = ! ( bRowa && bRowb && bCola && bColb ) ;
	    }
	}
	for ( int i = 0 ; i < trees.length ; i ++ ) {
	    for ( int j = 0 ; j < trees[i].length ; j ++ ) {
		System.out.print( trees[i][j].iHeight + ":" + (trees[i][j].bVisible ? "#t" : "#f" ) + "," );
		if ( trees[i][j].bVisible ) iVisible ++ ;
	    }
	    System.out.println() ;
	}
	System.out.println( iVisible ) ;
    }
}
