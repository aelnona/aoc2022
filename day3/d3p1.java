public class d3p1
{
    public static int getSumLine( final String szLine )
    {
	for ( int i = 0 ; i < szLine.length() ; i ++ ){
	    if ( szLine.substring( 0, szLine.length() / 2 ).contains( ""+szLine.charAt( i ) )
		 && szLine.substring( szLine.length() / 2 , szLine.length() )
		 .contains( ""+szLine.charAt( i ) ) ) {

		System.out.println( szLine.charAt( i ) ) ;
		
		return ( (int) szLine.charAt( i )
		    - ((szLine.charAt( i ) < 97) ? 64 - 26 : 96 ) ) ;
	    }
	}
	return 0 ;
    }
    public static void main( final String... args )
    {
	// args[0] is the input
	final String[] szSplit = args[0].split( "\n" ) ;
	int iSum = 0 ;

	for ( int i = 0 ; i < szSplit.length ; i ++ ){
	    int toA = 
	    getSumLine( szSplit[i].trim().replaceAll("\n|\t| ", "" ) ) ;

	    iSum += toA ; 
	    System.out.println( toA + " :: " + iSum) ; 
	}

	System.out.println( iSum ) ;
    }
}
