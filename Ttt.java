import java.io.*;
import java.util.*;
import java.util.Arrays;
import javax.swing.*;  
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ttt{
static String[][] mat = new String[3][3];
 static void initmat(){
   for(int i=0;i<3;i++)
     for(int j=0;j<3;j++)
       mat[i][j]=" _ ";
  }

final static void showmat(){
    for(int i=0;i<3;i++){
     for(int j=0;j<3;j++)
        System.out.print(" | "+ mat[i][j] +" | ");
      System.out.print("\n");
    }
 }

static int checkrow(){
   int m=0,l=0,k=-1;
   for(int i=0;i<3;i++){
     for(int j=0;j<3;j++){
      if(mat[i][j]=="X")
        l++;
      else if(mat[i][j]=="O")
        m++;
    }
    if(l==3){
     k=1;
     return k;}
    if(m==3){
     k=0;
     return k;
    }
    m=0;
    l=0;
  }
 return k;
}
static int checkcol(){
 int d=0,p=0,k=-1;
 for(int i=0;i<3;i++){
     for(int j=0;j<3;j++){
      if(mat[j][i]=="X")
        d++;
      else if(mat[j][i]=="O")
        p++;
    }
    if(d==3){
     k=1;
     return k;}
    if(p==3){
     k=0;
     return k;
    }
    d=0;
    p=0;
  }
  return k;
}

static int checkdiag(){
  int r=-1;
  if(mat[0][0]=="X" && mat[1][1]=="X" && mat[2][2]=="X"){
    r=1;
    return r;
   }
  else if(mat[0][0]=="O" && mat[1][1]=="O" && mat[2][2]=="O"){
   r=0;
   return r;
  }
  else if(mat[2][0]=="X" && mat[1][1]=="X" && mat[0][2]=="X"){
   r=1;
   return r;
  }
  else if(mat[2][0]=="O" && mat[1][1]=="O" && mat[0][2]=="O"){
   r=0;
   return r;
}
  return r;
}

static boolean checkdraw() {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {

            final Set<String> st = new HashSet<String>();
            for (int j = 0; j < 3; j++) {
                if (mat[i][j] != " _ ") {
                    st.add(mat[i][j]);
                }
            }
            if (st.size() == 2) {
                cnt = cnt + st.size();
            }

        }
        for (int i = 0; i < 3; i++) {

            final Set<String> st = new HashSet<String>();
            for (int j = 0; j < 3; j++) {
                if (mat[i][j] != " _ ") {
                    st.add(mat[j][i]);
                }
            }
            if (st.size() == 2) {
                cnt = cnt + st.size();
            }
        }

        final Set<String> st = new HashSet<String>();
        final Set<String> st2 = new HashSet<String>();
        for (int i = 0; i < 3; i++) {
            if (mat[i][i] != " _ ") {
                st.add(mat[i][i]);
            }
        }
        if (st.size() == 2) {
            cnt = cnt + st.size();
        }

        for (int i = 0; i < 3; i++) {
            if (mat[i][3 - i - 1] != " _ ") {
                st2.add(mat[i][3 - i - 1]);
            }
        }
        if (st2.size() == 2) {
            cnt = cnt + st2.size();
        }
        if (cnt == 8) {
            return true;
        }

        return false;

    }

public static void logToFile(char player, int r, int c) {
        try {
            LocalDateTime myDateObj = LocalDateTime.now();
            BufferedWriter out = new BufferedWriter(new FileWriter("Outputfile.txt", true));

            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);

            if (player == 'D' && r == 10 && c == 10)
                out.write(formattedDate + " : Nobody won\n");
            else if((player == 'O' && r == 10 && c == 10) || (player == 'X' && r == 10 && c == 10))
                out.write(formattedDate + " : " + player + " placed " + player + " at " + r + ", " + c + "\n");
            else if((player == 'O' && r == 1 && c == 1) || (player == 'X' && r == 1 && c == 1))
                out.write(formattedDate + " : " + player + " is winner! \n");
            out.close();
            System.out.println("Logged The move");
        } catch (IOException e) {
            System.out.println("An error occurred while logging");
            e.printStackTrace();
        }
    }

public static void main(String args[]){
       int r=0;
       int c=0,count=9;
       initmat();
       showmat();
       Scanner input = new Scanner(System.in);
       while(count>=1){
           if((count%2!=0) && (r>=0 && r<=3 && c>=0 && c<=3)){
              System.out.print("Enter the position of X:");
              try{
               r = input.nextInt();
               c = input.nextInt();
              }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("The index you have entered is invalid");
                System.out.println("Please enter an index number between 0 and 3");
              }
              if(mat[r][c]==" _ "){
                 mat[r][c]="X";
                 showmat();
                 logToFile('X',10,10);
               }
              else if(mat[r][c]!=" _ "){
                 System.out.println("Invalid Input!");
                 System.out.println("Enter Again!");
                 count++;
               }
             
         }
           if((count%2==0) && (r>=0 && r<=3 && c>=0 && c<=3)){
              System.out.print("Enter the position of O:");
             try{
               r = input.nextInt();
               c = input.nextInt();
              }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("The index you have entered is invalid");
                System.out.println("Please enter an index number between 0 and 3");
              }
              if(mat[r][c]==" _ "){
                mat[r][c]="O";
                showmat();
                logToFile('O',10,10);
               }
               else if(mat[r][c]!=" _ "){
                 System.out.println("Invalid Input!");
                 System.out.println("Enter Again!");
                 count++;
               }
             
           }
           if(r<0 || r>3 || c<0 || c>3){
             System.out.println("Invalid Input!");
             count++;
           }
           if((checkrow()==1) || (checkcol()==1) || (checkdiag()==1)){
            logToFile('X',1,1);
            JFrame f= new JFrame("TextField Example");  
            JTextField t1;  
            t1=new JTextField("X is the winner!");  
            t1.setBounds(50,100, 200,30);
            f.add(t1);  
            f.setSize(400,400);  
            f.setLayout(null);  
            f.setVisible(true);  
            count=1;
           }
           if((checkcol()==0) || (checkrow()==0) || (checkdiag()==0)){
            logToFile('O',1,1);
            JFrame f= new JFrame("TextField Example");  
            JTextField t1;  
            t1=new JTextField("O is the winner!");  
            t1.setBounds(50,100, 200,30);
            f.add(t1);  
            f.setSize(400,400);  
            f.setLayout(null);  
            f.setVisible(true);
            count=1;
           }
           if(checkdraw()==true)
            break;
           count--;
        }  
         if(((checkcol()==-1) && (checkrow()==-1) && (checkdiag()==-1)) || checkdraw()==true ){
            logToFile('D',10,10);
            JFrame f= new JFrame("TextField Example");  
            JTextField t1;  
            t1=new JTextField("It's a tie!");  
            t1.setBounds(50,100, 200,30);
            f.add(t1);  
            f.setSize(400,400);  
            f.setLayout(null);  
            f.setVisible(true);
         }  
    }
}