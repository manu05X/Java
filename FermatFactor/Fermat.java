import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Fermat
{
        public void trialDivision(long N)
        {
                assert(N>1);
                assert(N%2!=0);
                long a = 3;
                while (a <= N)
                {
                        if (N % a == 0)
                        {
                        break;
                        }
                else
                        {
                                a += 1;
                        }
                }
                long b =(long)N/a;
                long p = a;
                long q = b;
                display(p,q);
        }
        public void FermatFact(long N)
        {
                assert(N>1);
                assert(N%2!=0);
                                long a = (long)Math.ceil(Math.sqrt(N));
                long b2 = a * a - N;
                while(!isSquare(b2))
                {
                        a++;
                        b2 = a*a - N;
                }
                long b =(long)Math.sqrt(b2);
                long p = a - b;
                long q = a + b;
                display(p , q);
        }

        public boolean isSquare(long N)
        {
                long sqr = (long)Math.sqrt(N);
                if(sqr*sqr == N ||(sqr+1)*(sqr+1) == N)
                        return true;
                return false;
        }
        public void display(long p,long q)
        {
                System.out.println("\nRoots Of Number :"+p+","+q);
        }
        public static void main(String args[])
        {
                // Scanner sc = new Scanner(System.in);
                // System.out.println("Enter the ODD NUMBER");
                // long N = sc.nextLong();
                System.out.println("Given ODD NUMBER is: "+args[0]);
                int N = Integer.parseInt(args[0]);      //input number is taken as String is convert into Integer and it will store in a 

                Fermat ff = new Fermat();

                long start = System.nanoTime();
                ff.FermatFact(N);
                long end = System.nanoTime();

                long microsecondsF = (end - start) / 1000;
                System.out.println("\nTime required for Fermat:"+microsecondsF+" milliSec");

                long startD = System.nanoTime();
                ff. trialDivision(N);
                long endD = System.nanoTime();

                long microsecondsD = (endD - startD) / 1000;
                System.out.println("\nTime required TrialDivision:"+microsecondsD+" milliSec");

        }

}