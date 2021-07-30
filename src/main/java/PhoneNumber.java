import java.util.HashMap;
import java.util.Map;

public class PhoneNumber
{
   public static String createPhoneNumber( int[] numbers )
   {
      Map<Integer, String> rules = new HashMap<Integer, String>() {{
         put( 0, "(" );
         put( 3, ") " );
         put( 6, "-" );
      }};

      StringBuilder sb = new StringBuilder();

      for( int i = 0; i < numbers.length ; i++ )
      {
         sb.append( rules.getOrDefault( i, "" ) );
         sb.append( numbers[i] );
      }
      return sb.toString();
   }
}
