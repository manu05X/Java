import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.math.BigInteger;

public class Fermat
{
        public void trialDivision(BigInteger N)
        {
                assert(N>1);
                assert(N%2!=0);
                BigInteger a = 3;
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
                BigInteger b =(BigInteger)N/a;
                BigInteger p = a;
                BigInteger q = b;
                display(p,q);
        }
        public void FermatFact(BigInteger N)
        {
                assert(N>1);
                assert(N%2!=0);
                        BigInteger a = (BigInteger)Math.ceil(Math.sqrt(N));
                BigInteger b2 = (a.multiply(a)).subtract(N);

                BigInteger one = new BigInteger("1");
                while(!isSquare(b2))
                {
                        a.add(one);
                        b2 = (a.multiply(a)).subtract(N);
                }
                BigInteger b =(BigInteger)Math.sqrt(b2);
                BigInteger p = a.subtract(b);
                BigInteger q = a.add(b);
                display(p , q);
        }

        public boolean isSquare(BigInteger N)
        {
                BigInteger sqr = (BigInteger)Math.sqrt(N);
                if(sqr*sqr == N ||(sqr+1)*(sqr+1) == N)
                        return true;
                return false;
        }
        public void display(BigInteger p,BigInteger q)
        {
                System.out.println("\nRoots Of Number :"+p+","+q);
        }
        public static void main(String args[])
        {
                // Scanner sc = new Scanner(System.in);
                // System.out.println("Enter the ODD NUMBER");
                // long N = sc.nextLong();
                System.out.println("Given ODD NUMBER is: "+args[0]);
                BigInteger N = new BigInteger(args[0]);      //input number is taken as String is convert into Integer and it will store in a 

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