import java.util.stream.IntStream;

public class StringToCamelCase
{

   /**
    * 1. Collect index of every '-' char
    * 2. For each dash, convert next index to uppercase
    *
    * @param string string to be converted to camelCase.
    * @return string converted to camelCase.
    */
   public static String toCamelCase( String string )
   {
      StringBuilder sb = new StringBuilder();

      int[] indices = IntStream.range( 0, string.length())
                .filter( i -> string.charAt( i ) == '-' || string.charAt( i ) == '_' )
                .toArray();

      int prev = 0;
      int i = 0;
      while( i < indices.length ) {
         sb.append( string, prev, indices[i] );
         sb.append( string.substring( indices[i]+1, indices[i]+2).toUpperCase() );
         prev = indices[i++]+2;
         if( i >= indices.length )
            sb.append( string, prev, string.length() );
      }

      return sb.toString();
   }
}
