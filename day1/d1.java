import java.util.ArrayList ;
import java.util.Comparator ; 

// I wrote this one in five minutes

public class d1
{
    public static final void main( final String[] args )
    {
	final String[] sp = args[0].split( "\n" ) ;
	final ArrayList<Integer> ints = new ArrayList<Integer>() ; 
	int sum = 0 ;
	int ret = 0 ; 

	for ( int i = 0 ; i < sp.length ; i ++ ) {
	    if ( sp[i].trim().length() < 1 ) {
		ints.add( sum ) ; 
		sum = 0 ;
	    }
	    else {
		sum += Integer.parseInt( sp[i].trim() ) ;
	    }
	}

	ints.sort( Comparator.reverseOrder() ) ;

	System.out.println( (ints.get(0) +
	ints.get(1) +
	ints.get(2)) );
    }
}
