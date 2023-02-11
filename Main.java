import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    static final String USER = "postgres";
    static final String PASS = "ggg816";

    static Connection conn = null;
    static Statement stmt = null;
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            int option;
            do {
                System.out.println("1. Employee");
                System.out.println("2. Customer");
                System.out.println("3. Exit");
                System.out.print("Enter option: ");
                option = scan.nextInt();
                scan.nextLine();

                switch (option) {
                    case 1:
                        employeeMenu();
                        break;
                    case 2:
                        customerMenu();
                        break;
                }
            } while (option != 3);

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se3) {
                se3.printStackTrace();
            }
        }
    }

    public static void employeeMenu() {
        System.out.print("Enter login: ");
        String Login = scan.nextLine();
        System.out.print("Enter password: ");
        int Password = scan.nextInt();

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ggg816");
            String check_login = "SELECT * FROM \"Employee\" WHERE \"Login\" = '" + Login + "' AND \"Password\" = '" + Password + "';";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(check_login);
            if (rs.next()) {
                System.out.println("Login successful");
                int option = 0;
                do {
                    System.out.println("1. List all items");
                    System.out.println("2. Add new items");
                    System.out.println("3. Remove items");
                    System.out.println("4. Exit");
                    System.out.print("Enter option: ");

                    option = scan.nextInt();
                    scan.nextLine();

                    switch (option) {
                        case 1:
                           getAllItems();
                            break;
                        case 2:
                            addNewItem();
                            break;
                        case 3:
                            removeItem();
                            break;
                    }
                } while (option != 4);
            } else {
                System.out.println("Login failed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public static void customerMenu() {
        System.out.print("Enter login: ");
        String Login = scan.nextLine();
        System.out.print("Enter password: ");
        int Password = scan.nextInt();

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ggg816");
            stmt = conn.createStatement();
            String check_login = "SELECT * FROM \"Employee\" WHERE \"Login\" = '" + Login + "' AND \"Password\" = '" + Password + "';";
            ResultSet rs = stmt.executeQuery(check_login);
            if (rs.next()) {
                System.out.println("Login successful");
                int option ;
                do {
                    System.out.println("1. List all items");
                    System.out.println("2. Add new items");
                    System.out.println("3. Remove items");
                    System.out.println("4. Buy items");
                    System.out.println("5. Exit");
                    System.out.print("Enter option: ");

                    option = scan.nextInt();
                    scan.nextLine();

                    switch (option) {
                        case 1:
                            getAllItems();
                            break;
                        case 2:
                            addNewItem();
                            break;
                        case 3:
                            removeItem();
                            break;
                        case 4:
                            buyItem();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                } while (option != 5);

            } else {
                System.out.println("Login failed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public static void getAllItems() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose category: 1.Laptops 2.Phones 3.Tablets");
        int choice = sc.nextInt();
        String category = "";
        switch (choice) {
            case 1:
                category = "Laptop";
                break;
            case 2:
                category = "Phones";
                break;
            case 3:
                category = "Tablets";
                break;
            default:
                System.out.println("Invalid choice");
        }
        try {
            String get_all = "SELECT * FROM \"Items\" WHERE \"Category\" = '" + category + "';";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(get_all);

            System.out.println("Id\tModel\tCount\tPrice");
            while (rs.next()) {
                int id = rs.getInt("DevId");
                String Model = rs.getString("Model");
                double Price = rs.getDouble("Price");
                int count = rs.getInt("Count");

                System.out.println(id + "\t" + Model + "\t" + count + "\t" + Price);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }



    public static void addNewItem() {
        System.out.print("Enter item ID: ");
        int DevId = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter item Category: ");
        String Category = scan.nextLine();
        System.out.print("Enter item model: ");
        String Model = scan.nextLine();
        System.out.print("Enter item price: ");
        int Price = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter item count: ");
        int Count = scan.nextInt();
        scan.nextLine();
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ggg816");
            String add_item = "INSERT INTO \"Items\" (\"DevId\", \"Category\", \"Model\", \"Price\", \"Count\") VALUES (?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(add_item);
            preparedStmt.setInt(1, DevId);
            preparedStmt.setString(2, Category);
            preparedStmt.setString(3, Model);
            preparedStmt.setInt(4, Price);
            preparedStmt.setInt(5, Count);
            preparedStmt.executeUpdate();
            System.out.println("Item added successfully");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    public static void removeItem() {
        System.out.print("Enter item id: ");
        int id = scan.nextInt();
        scan.nextLine();

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ggg816");
            String remove_item = "DELETE FROM \"Items\" WHERE \"DevId\" = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(remove_item);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

            System.out.println("Item removed successfully");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    public static void buyItem() {
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        boolean finished = false;
        while (!finished) {
            System.out.print("Enter item id: ");
            int id = scan.nextInt();
            scan.nextLine();
            System.out.print("Enter quantity: ");
            int quantity = scan.nextInt();
            scan.nextLine();

            ids.add(id);
            quantities.add(quantity);

            System.out.print("Do you want to add another item? (y/n)");
            String another = scan.nextLine();
            if (!another.equals("y")) {
                finished = true;
            }
        }

        double totalCost = 0;
        System.out.println("You bought the following items:");
        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            int quantity = quantities.get(i);
            try {
                String get_item = "SELECT * FROM \"Items\" WHERE \"DevId\" = '" + id + "';";
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(get_item);

                if (rs.next()) {
                    String category = rs.getString("Category");
                    double price = rs.getDouble("Price");
                    String Model=rs.getString("Model");
                    double itemCost = price * quantity;
                    totalCost += itemCost;
                    System.out.println(category +"("+ Model+")"+ " * " + quantity + " price: $" + price + " each = $" + itemCost);
                } else {
                    System.out.println("Item with id " + id + " not found");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Total cost: $" + totalCost);
    }

}



