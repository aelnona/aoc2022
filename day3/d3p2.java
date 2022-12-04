// lol
public class d3p2 {
    public static void main( final String... args ){
	int iSum = 0 ;
	for ( int i = 0 ; i < args[0].split("\n").length; i +=3 ){
	    for ( int j = 1 ; j < 53 ; j ++ ){
		iSum +=	((args[0].split("\n")[i].contains(""+(char)(j + ((j > 26) ? 38 : 96 )))
			  && args[0].split("\n")[i+1].contains(""+(char)(j+((j > 26) ? 38 : 96)))
			  && args[0].split("\n")[i+2].contains(""+(char)(j+((j > 26) ? 38 : 96)))) ? j : 0 );}}
	System.out.println( iSum ) ;}}
