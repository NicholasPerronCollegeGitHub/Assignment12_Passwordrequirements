import java.util.Scanner;
public class App {

    static boolean lengthCheck(String pass){
        
        if(pass.length() >= 8 && pass.length() <= 16){
            return(true);
        }else{
            return(false);
        }
    }
    static boolean[] passCheck(String pass){
        int sum = 0;
        boolean[] criteria = {false,false,false,false,false};
        for(int i = 0; i < pass.length(); i++){
            char temp = pass.charAt(i);
            if(!criteria[1])
            criteria[1] = Character.isUpperCase(temp);
            if(!criteria[2])
            criteria[2] = Character.isLowerCase(temp);
            if(!criteria[3])
            criteria[3] = Character.isDigit(temp);
            if(!criteria[4])
            criteria[4] = (temp == '~' || temp == '!' || temp == '@' || temp == '#' || temp == '$' || temp == '%' || temp == '^' || temp == '&' || temp == '*' || temp == '(' || temp == ')' || temp == '~' || temp == '-' || temp == '=' || temp == '+' || temp == '_');
        }
        for(int i = 1; i < criteria.length; i++){
            if(criteria[i]){
                sum++;
            }
        }
        criteria[0] = (sum > 2);
        return(criteria);
    }
    public static void main(String[] args) throws Exception {
        boolean done = false;
        boolean looped = false;
        boolean length = false;
        boolean[] criteria = {false,false,false,false,false};
        Scanner input = new Scanner(System.in);
        String pass = null;
        char cont = '!';
        while(!done){
            if(args.length > 0 && looped == false){
                pass = args[1];
                length = lengthCheck(pass);
                criteria = passCheck(pass);
            }else{
                if(!looped){
                    System.out.print("No command line arguments detected, please enter the password you want to check: ");
                    while(pass == null){
                        pass = input.nextLine();
                        if(pass == null){
                            System.out.print("Please enter a password to check: ");
                        }
                    }
                    length = lengthCheck(pass);
                    criteria = passCheck(pass);
                }else{
                    System.out.print("Please enter a password to check: ");
                    while(pass == null){
                        pass = input.nextLine();
                        if(pass == null){
                        System.out.print("Please enter a password to check: ");
                        }
                }
                    length = lengthCheck(pass);
                    criteria = passCheck(pass);
            }
            if(!length){
                System.out.println("Password is not within the required length (8 - 16 characters)");
            }
            if(!criteria[0]){
                System.out.println("Please make sure your password fulfills all but one or all of these criteria:");
                if(!criteria[1]){
                    System.out.println(" - Password must contain an uppercase character");
                }
                if(!criteria[2]){
                    System.out.println(" - Password must contain a lowercase character");
                }
                if(!criteria[3]){
                    System.out.println(" - Password must contain a number");
                }
                if(!criteria[4]){
                    System.out.println(" - Password must contain any of the following special characters: ~ ! @ # $ % ^ & * ( ) - = + _ ");
                }
            }
            if(length && criteria[0]){
                System.out.println("This password meets all neccessary criteria.");
            }
            System.out.print("Check another password? [Y/N]: ");
            try {
                cont = input.nextLine().toUpperCase().charAt(0);
            } catch (Exception e){

            }
            boolean contCheck = false;
            while(!contCheck){
            if(cont == 'Y'){
                done = false;
                contCheck = true;
            }else if(cont == 'N'){
                done = true;
                contCheck = true;
            }else{
                System.out.print("Please enter either [Y] or [N]: ");
                try{
                    cont = input.nextLine().toUpperCase().charAt(0);
                } catch (Exception e){

                }
            }
        }
            cont = '!';
        }
        looped = true;
        length = false;
        pass = null;
        for(int i = 0; i < criteria.length; i++){
            criteria[i] = false;
        }
    }
    input.close();
}
}