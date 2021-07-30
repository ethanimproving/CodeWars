public class Snail
{

   /**
    * [0[0,1,2]]
    * [1[0,1,2]]
    * [2[0,1,2]]
    * ==> 0[0][0] 1[0][1] 2[0][2] 3[1][2] 4[2][2] 5[2][1] 6[2][0] 7[1][0] 8[1][1]
    *
    * -If first array, move forward
    * -If middle, if last element, move down
    * -If last array, move left
    */
   public static int[] snail(int[][] array) {

      int[] sequence = new int[array.length * array[0].length];

      for( int i = 0; i < array.length; i++ )
      {
         for( int j = 0; j < array[i].length; j++ )
         {
            int sequenceIndex = 0;

            if( i == 0 ) sequenceIndex = j;
            else if (i == array.length - 1) sequenceIndex = array[i].length - j;
            else if (j == array[i].length) sequenceIndex = array[i].length;
            else sequenceIndex =

            sequence[sequenceIndex] = array[i][j];
         }
      }

      return sequence;
   }
}
