import java.util.ArrayList ;
import java.util.Comparator ;
public class d7p2 {
    static int iTotal = 0 ;
    static ArrayList<Integer> totals = new ArrayList<Integer>() ; 

    private static class Tree{
	final String szContent ;
	final Tree parent ;
	final ArrayList<Tree> children ;
	int iSize = -1 ; 
	
	public Tree( final String szContent , final Tree parent ){
	    this.szContent = szContent ;
	    this.parent = parent ;
	    this.children = new ArrayList<Tree>() ; 
	}
	public Tree( final String szContent , final Tree parent , final int iSize ) {
	    this( szContent , parent ) ;
	    this.iSize = iSize ; 
	}
	public void display ( final int iLevel) {
	    
	    for ( int j = 0 ; j < iLevel ; j ++ ) System.out.print( " " ) ;
	    System.out.println( this.szContent + " : " +  this.getSize() ) ;
	    for ( int i = 0 ; i < this.children.size() ; i ++ ){
		for ( int j = 0 ; j <= iLevel ; j ++ ) System.out.print( " " ) ;
		this.children.get( i ).display( iLevel + 1 ) ; 
	    }
	}
	public int getSize() {
	    if ( this.iSize == -1 ) {
		this.iSize = 0 ;
		for ( int i = 0 ; i < this.children.size() ; i ++ ) {
		    this.iSize += this.children.get(i).getSize() ;
		}
		if ( (70000000 - (43837783 - this.iSize) ) >= 30000000 ) {
		    totals.add( this.iSize ) ;
		}
	    }
	    return this.iSize ; 
	}
    }

    public static String removeLastAdded( final String szPwd ) {
	for ( int i = szPwd.length() - 2 ; i >= 0 ; i -- ) {
	    if ( szPwd.charAt( i ) == '/' ) return szPwd.substring( 0 , ++i ).trim() ;
	}
	return szPwd.trim() ; 
    }
    
    public static void main ( final String[] args ) {
	final String[] szInstructions = args[0].split( "\\$ " ) ;
	final ArrayList<String> szPaths = new ArrayList<String>() ;

	final Tree root = new Tree( "." , null ) ;
	Tree current = root ; 

	String szPwd = "" ;

	// Start at 2 to ignore initial cd and some weirdness
	for ( int i = 2 ; i < szInstructions.length ; i ++ ) {
	    final String[] instr = szInstructions[i].trim().split( "\n" ) ;

	    if ( instr[0].substring( 0 , 2 ).equals( "cd" ) ) {
		if ( instr[0].substring( 2 ).trim().equals( ".." ) ) {
		    szPwd = removeLastAdded( szPwd ) ;
		    current = current.parent ; 
		}
		else {
		    szPwd = szPwd + instr[0].substring(2).trim() + "/" ;
		    Tree chid = new Tree( instr[0].substring(2).trim() , current );
		    current.children.add( chid ) ;
		    current = chid ;
		}
	    }
	    else {
		for ( int j = 1 ; j < instr.length ; j ++ ) {
		    final String[] instrs = instr[j].split( " " ) ;

		    if ( instrs[0].trim().equalsIgnoreCase( "dir" ) ) {
			szPaths.add( szPwd + instrs[1].trim() + "/" ) ;
		    }
		    else {
			szPaths.add( szPwd + instrs[1].trim() + ":" + instrs[0].trim() ) ;
			current.children.add( new Tree ( instrs[1].trim()
							 , current
							 , Integer.parseInt( instrs[0].trim() ) ) ) ;
		    }
		}
	    }
	}

	root.display(0) ;
	System.out.println( root.iSize ) ;
	totals.sort(Comparator.naturalOrder() ) ;
	System.out.println( totals.get( 0 ) ) ;
    }
}
