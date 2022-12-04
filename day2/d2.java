public class d2
{
    enum Result
    {
	win,loss,draw;
    }
    enum Move
    {
	rock(1),
	paper(2),
	scissors(3);

	private int score;
	
	Move(final int score)
	{
	    this.score = score;
	}
	public int getScore() { return this.score; }
    }

    static Result checkResult(final Move opponent, final Move ii)
    {
	Result res = Result.loss ;

	if ( opponent == ii ) return Result.draw ;
	switch( opponent ){
	case rock:
	    if ( ii == Move.paper ){
		res = Result.win ;
	    }
	    break ;
	case paper:
	    if ( ii == Move.scissors ){
		res = Result.win ;
	    }
	    break ;
	case scissors:
	    if ( ii == Move.rock ){
		res = Result.win ;
	    }
	    break;
	}
	
	return res; 	
    }
    public static void main( final String... args )
    {
	int score = 0 ;
	final String[] lines = args[0].split( "\n" ) ;

	for ( int i = 0 ; i < lines.length ; i ++ ){
	    final String[] line = lines[i].trim().split(" ") ;
	    Move op , ii = Move.rock;

	    switch( line[0].trim() ){
	    case "A":
		op = Move.rock;

		switch( line[1].trim() ){
		case "X":
		    ii = Move.scissors;
		    break ;
		case "Y":
		    ii = Move.rock ;
		    break;
		case "Z":
		    ii = Move.paper;
		    break ;
		}
		
		break;
	    case "B":
		op = Move.paper;
		switch( line[1].trim() ){
		case "Z":
		    ii = Move.scissors;
		    break ;
		case "X":
		    ii = Move.rock ;
		    break;
		case "Y":
		    ii = Move.paper;
		    break;
		}
		break;
	    default:
		op = Move.scissors;
		switch( line[1].trim() ){
		case "Y":
		    ii = Move.scissors;
		    break ;
		case "Z":
		    ii = Move.rock ;
		    break;
		case "X":
		    ii = Move.paper;
		    break;
		}
		break;
	    }

	    score += ii.getScore() ;

	    switch( checkResult( op , ii ) ){
	    case win:
		score += 6 ;
		break;
	    case draw:
		score += 3;
		break;
	    }
	}
	System.out.println( score ) ;
    }
}
