import java.util.HashSet ;
public class d6p2 {
    public static void main ( final String[] args ) {
	for ( int i = 0 ; i < args[0].length() ;  i++ ){
	    HashSet<Character> set = new HashSet<Character>();
	    for ( int j = i ; j < i + 14 ; j ++ ) {
		set.add( args[0].charAt( j ) ) ;
	    }
	    if ( set.size() >= 14 ) {
		System.out.println( i + 14 ) ; break ;
	    }
	}
    }
}
