import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main
{

    private int rows;
    private int columns;
    private int start_A;
    private int start_B;
    private int end_A;
    private int end_B;
    private String[][] block;

    public int getStart_A()
    {
        return start_A;
    }

    public int getStart_B()
    {
        return start_B;
    }

    public int getEnd_A()
    {
        return end_A;
    }

    public int getEnd_B()
    {
        return end_B;
    }

    public String[][] getBlock()
    {
        return block;
    }

    /**
     * This method reads the file and stores the values in a 2D array
     *
     * @param filePath
     */

    public void fileReader( String filePath )
    {
        try
        {
            rows = Files.readAllLines( Paths.get( filePath ) ).size();
            columns = Files.readAllLines( Paths.get( filePath ) ).get( 0 ).length();
            block = new String[rows][columns];

            for( int x = 0; x < rows; x++ )
            {
                String line = Files.readAllLines( Paths.get( filePath ) ).get( x );
                for( int y = 0; y < columns; y++ )
                {
                    block[x][y] = String.valueOf( line.charAt( y ) );

                    if( String.valueOf( line.charAt( y ) ).equals( "S" ) )
                    {      //checking for Starting point (S)
                        start_A = y;
                        start_B = x;
                    }
                    if( String.valueOf( line.charAt( y ) ).equals( "F" ) )
                    {      //checking for finishing point (F)
                        end_A = y;
                        end_B = x;
                    }
                }
            }

        }
        catch( IOException ignored )
        {
            System.out.println( "File not Found" );
        }
    }

    public static void main( String[] args )
    {

        String filePath = "AlgorithemsCW/src/Course_Work/benchmark_series/puzzle_40.txt";
        Main parser = new Main();
        parser.fileReader( filePath );

        // Time calculator for the puzzle solving
        Sliddingpuzzel iceSlider = new Sliddingpuzzel();
        long startTime = System.currentTimeMillis();
        iceSlider.solve( parser.getBlock(), parser.getStart_A(), parser.getStart_B(), parser.getEnd_A(), parser.getEnd_B() );
        long currentTime = System.currentTimeMillis();
        double timeTaken = ( currentTime - startTime ) / 1000.0;
        System.out.println( "    " );
        System.out.println( "Time Taken: " + timeTaken + " seconds." );
        System.out.println("---------------------------------------------");
    }
}