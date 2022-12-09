
import java.util.ArrayList ;
import java.util.Comparator ;

public class d8p2 {

    public static int calculateScore( final int[][] iHeights , final int y , final int x ) {

	int n=0 , e=0 , s=0 , w=0 ;

	// Get n
	for ( int i = y - 1 ; i >= 0 ; i -- ) {
	    ++ n ;
	    if ( iHeights[y][x] <= iHeights[i][x] ) break ;
	}
	// Get e
	for ( int i = x + 1 ; i < iHeights[y].length ; i ++ ) {
	    ++ e ;
	    if ( iHeights[y][x] <= iHeights[y][i] ) break ;
	}
	// Get s
	for ( int i = y + 1 ; i < iHeights.length ; i ++ ) {
	    ++ s ;
	    if ( iHeights[y][x] <= iHeights[i][x] ) break ;
	}
	// Get w
	for ( int i = x - 1 ; i >= 0 ; i -- ) {
	    ++ w ;
	    if ( iHeights[y][x] <= iHeights[y][i] ) break ;
	}
	System.out.println( " " + n + e + s + w ) ;
	
	return (n * e * s * w) ; 
    }
    
    public static void main ( final String[] args ) {

	final ArrayList<Integer> scores = new ArrayList<Integer>() ; 
	// Why is length for arrays a constant, and a method for Strings? 
	int[][] iHeights = new int[args[0].split("\n").length][args[0].split("\n")[0].length()] ;
	int[][] iScores = new int[iHeights.length][iHeights[0].length] ;

	for ( int i = 0 ; i < iHeights.length ; i ++ ) {
	    for ( int j = 0 ; j < iHeights[i].length ; j ++ ) {
		iHeights[i][j] = Character.getNumericValue( args[0].split("\n")[i].charAt(j) ) ;
		System.out.print( iHeights[i][j] + " " ) ;
	    }
	    System.out.println() ;
	}
	for ( int i = 0 ; i < iHeights.length ; i ++ ) {
	    for ( int j = 0 ; j < iHeights[i].length ; j ++ ) {
		System.out.print( iHeights[i][j] + " " ) ;
		scores.add( calculateScore( iHeights , i , j ) ) ;
	    }
	    System.out.println() ;
	}

	scores.sort( Comparator.reverseOrder() ) ;
	System.out.println( scores.get(0) ) ;
    }
}
