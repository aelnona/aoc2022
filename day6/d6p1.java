import java.util.HashSet ;
public class d6p1 {
    public static void main ( final String[] args ) {
	for ( int i = 0 ; i < args[0].length() ;  i++ ){
	    HashSet<Character> set = new HashSet<Character>();
	    for ( int j = i ; j < i + 4 ; j ++ ) {
		set.add( args[0].charAt( j ) ) ;
	    }
	    if ( set.size() >= 4 ) {
		System.out.println( i + 4 ) ; break ;
	    }
	}
    }
}
