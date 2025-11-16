import java.io.*;
import java.util.*;
class ExpenseTracker {
    private static final String file_name="expenses.txt";
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        ArrayList<Expense> expenses= loadExpenses();

        while(true){
            System.out.println("=======Total Expense Tracker=======");
            System.out.println("1. Add Expense");
            System.out.println("2. View all Expenses");
            System.out.println("3. View Total spending");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter category(food/shop/travel): ");
                    String category=sc.next();
                    System.out.print("Enter amount: ");
                    double amount=sc.nextDouble();
                    Expense expense=new Expense(category, amount);
                    expenses.add(expense);
                    saveExpenses(expense);
                    System.out.println("Expense added successfully!");
                    break;
                case 2:
                    System.out.println("All Expenses:");
                    for(Expense exp: expenses){
                        System.out.println(exp);
                    }
                    break;
                case 3:
                    double total=0;
                    for(Expense exp: expenses){
                        total+=exp.amount;
                    }
                    System.out.println("Total Spending: Rs "+total);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }


      }
    }
    private static ArrayList<Expense> loadExpenses(){
        ArrayList<Expense> list=new ArrayList<>();
        try{
            File file =new File(file_name);
            if((!file.exists()))  return list;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line=br.readLine())!=null){
                String[] parts= line.split(",");
                list.add(new Expense(parts[0], Double.parseDouble(parts[1])));
            
            }
            br.close();
        }catch(Exception e){
            System.out.println("Error loading expenses");

        }
        return list;

    }

    private static void saveExpenses(Expense e) {
        try {
            FileWriter fw = new FileWriter(file_name, true);
            fw.write(e.category + "," + e.amount + "\n");
            fw.close();
        } catch (Exception ex) {
            System.out.println("Error saving expense.");
        }
    }
  
}