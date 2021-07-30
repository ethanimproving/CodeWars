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
   public static int[] snail(int[][] array)
   {

      int[] result = new int[array.length*array.length];
      if(array.length==1)
         return array[0];

      int position=0;
      int rowStart=0, rowEnd=array.length-1, columnStart=0, columnEnd=array.length-1;

      for(int i=0; i<array.length/2; i++){
         for(int r=rowStart; r<=rowEnd; r++){
            result[position++] = array[columnStart][r];
         }
         columnStart++;
         for(int c=columnStart; c<=columnEnd; c++){
            result[position++] = array[c][rowEnd];
         }
         rowEnd--;
         for(int r=rowEnd; r>=rowStart; r--){
            result[position++] = array[columnEnd][r];
         }
         columnEnd--;
         for(int c=columnEnd; c>=columnStart; c--){
            result[position++] = array[c][rowStart];
         }
         rowStart++;
      }
      if(array.length%2!=0){
         result[position]=array[rowStart][columnStart];
      }

      return result;
   }
}
