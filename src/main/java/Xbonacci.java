import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Xbonacci
{

   /**
    * 1. for each n number of times
    * 2. sum the last 3 numbers in the array, and add it
    *
    * @param s array of doubles to be sequenced.
    * @param n number of elements to be returned.
    * @return a tribonacci sequence.
    */
   public double[] tribonacci(double[] s, int n) {
      List<Double> list = DoubleStream.of( s ).boxed().collect( Collectors.toList());

      for( int i = 0; i < n - s.length; i++ )
         list.add( list.get( list.size() - 1 ) + list.get( list.size() - 2 ) + list.get( list.size() - 3 ) );

      if( s.length > n )
         list = list.subList( 0, n );
      return list.stream().mapToDouble( Double::doubleValue ).toArray();
   }

}
