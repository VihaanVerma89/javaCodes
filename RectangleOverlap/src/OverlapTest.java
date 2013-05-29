/*
  program which takes the coordinates of two rectangles, and outputs TRUE if the squares overlap
    For example: with input as 

    0,0,4,4,1,1,7,7  --- the ouput is TRUE.
*/
public class OverlapTest 
{

    private class Point
    {
        int x;
        int y;
    }

    private class Rectangle
    {
        Point start;
        Point end;
    }

    public boolean checkSquareOverlap(String r1, String r2)
    {
        Rectangle leftRectangle = new Rectangle();
        Rectangle rightRectangle = new Rectangle();

        String [] r1Coordinates = r1.split(" ");
        String [] r2Coordinates = r2.split(" ");


        Point [] p = new Point[4];
        for(int i =0 ; i <4 ; i++)
            p[i] = new Point();

        p[0].x = Integer.valueOf(r1Coordinates[0]);
        p[0].y = Integer.valueOf(r1Coordinates[1]);
        p[1].x = Integer.valueOf(r1Coordinates[2]);
        p[1].y = Integer.valueOf(r1Coordinates[3]);
        p[2].x = Integer.valueOf(r2Coordinates[0]);
        p[2].y = Integer.valueOf(r2Coordinates[1]);
        p[3].x = Integer.valueOf(r2Coordinates[2]);
        p[3].y = Integer.valueOf(r2Coordinates[3]);

        if(p[0].x < p[2].x)
        {
            leftRectangle.start = p[0];
            leftRectangle.end = p[1];

            rightRectangle.start =p[2];
            rightRectangle.end =p[3];
        }
        else
        {
            leftRectangle.start = p[2];
            leftRectangle.end = p[3];

            rightRectangle.start =p[0];
            rightRectangle.start =p[1];
        }

        //check if they Overlap.

        if(rightRectangle.start.x > leftRectangle.start.x &&
                rightRectangle.start.x < leftRectangle.end.x &&
                rightRectangle.start.y > leftRectangle.start.y &&
                rightRectangle.start.y < leftRectangle.end.y
          )
        {
            //they overlap.
            return isSquarePresent(leftRectangle, rightRectangle);

        } else if(rightRectangle.start.x > leftRectangle.start.x &&
                rightRectangle.start.x < leftRectangle.end.x &&
                rightRectangle.end.y > leftRectangle.start.y &&
                rightRectangle.end.y < leftRectangle.end.y
                )
        {
            //they overlap.
            return isSquarePresent(leftRectangle, rightRectangle);
        }

        else
        {
            //they don't intersect and if they overlap they can't be 
        	//squares as rectangles are given as input.
            return false;
        }
    }

    private boolean isSquarePresent(Rectangle leftRectangle, Rectangle rightRectangle)
    {
        int l,b;
        l = leftRectangle.end.x - rightRectangle.start.x;
        b = leftRectangle.end.y - rightRectangle.start.y;

        return ( l == b);
    }



    public static void main(String [] args)
    {
        //Note:
        //The program only checks for square overlap in the Ist quadrant. 
        String r1 = "0 0 4 4";
        String r2 = "1 1 7 7";
        OverlapTest t  = new OverlapTest();
        System.out.println("Overlap square condition met: " + t.checkSquareOverlap(r1,r2));

        r1 = "0 0 8 8";
        r2 = "4 4 9 9";
        System.out.println("Overlap square condition met: " + t.checkSquareOverlap(r1,r2));

        r1 = "0 0 8 8";
        r2 = "3 4 9 9";
        System.out.println("Overlap square condition met: " + t.checkSquareOverlap(r1,r2));

    }

}
