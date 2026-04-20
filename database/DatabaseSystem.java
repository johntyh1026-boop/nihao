package database;
import java.sql.*;
import java.util.ArrayList;
import entity.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class DatabaseSystem {
    //data field
    String databaseUrl = "jdbc:h2:./database/Resources/cenima";
    private static String user = "sa";
    private static String password = "";
    private static Connection dataConnect = null;

    //methods
    public void initDatabase(){
        //table
        String createCustomerTable = 
        "CREATE TABLE IF NOT EXISTS CUSTOMER (" +
            "customer_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "user_name VARCHAR(50) NOT NULL, " +
            "phone_number VARCHAR(50) UNIQUE NOT NULL, "+
            "six_digit_pin VARCHAR(6) NOT NULL, "+
            "loyalty_points INT DEFAULT 0); ";
        
        String createAdminTable = 
        "CREATE TABLE IF NOT EXISTS ADMIN (" +
            "admin_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "user_name VARCHAR(50), " +
            "phone_number VARCHAR(50) UNIQUE, "+
            "six_digit_pin VARCHAR(6)); ";

        String createAddDrinkTable = 
        "CREATE TABLE IF NOT EXISTS DRINK(" +
            "drink_id INT AUTO_INCREMENT PRIMARY KEY, "+
            "drink_flavor VARCHAR(50) UNIQUE NOT NULL," +
            "price DECIMAL(5, 2) NOT NULL);";

        String createAddPopcornTable = 
        "CREATE TABLE IF NOT EXISTS POPCORNFLAVOR (" +
            "popcorn_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "popcorn_flavor VARCHAR(50) UNIQUE NOT NULL," +
            "price DECIMAL(5, 2) NOT NULL);";

        String createMovieTable = 
        "CREATE TABLE IF NOT EXISTS MOVIE(" +
            "movie_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "movie_title VARCHAR(100) UNIQUE NOT NULL, "+ 
            "duration_min INT NOT NULL); ";
        String createHallTable = 
        "CREATE TABLE IF NOT EXISTS HALL (" +
            "hall_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "hall_type VARCHAR(50) NOT NULL);";
        String createSeatTable = 
        "CREATE TABLE IF NOT EXISTS SEAT (" +
            "seat_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "hall_id INT NOT NULL, " +
            "row_letter CHAR(1) NOT NULL, " +
            "seat_number INT NOT NULL, " +
            "seat_type VARCHAR(10) DEFAULT 'Standard', " +
        "FOREIGN KEY (hall_id) REFERENCES HALL(hall_id) ON DELETE CASCADE, " +
        "UNIQUE (hall_id, row_letter, seat_number));";

        String createScheduleTable = 
        "CREATE TABLE IF NOT EXISTS SCHEDULE (" +
            "schedule_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "movie_id INT NOT NULL, " +
            "hall_id INT NOT NULL, " +
            "show_time TIMESTAMP NOT NULL, " +
            "end_time TIMESTAMP NOT NULL, " +  
            "base_price DECIMAL(5, 2) NOT NULL, " +
            
            "FOREIGN KEY (movie_id) REFERENCES MOVIE(movie_id) ON DELETE CASCADE, " +
            "FOREIGN KEY (hall_id) REFERENCES HALL(hall_id) ON DELETE CASCADE, " +
            
            "UNIQUE (hall_id, show_time));";

        String createTicketTable = 
        "CREATE TABLE IF NOT EXISTS TICKET (" +
            "ticket_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "customer_id INT NOT NULL, " +
            "schedule_id INT NOT NULL, " +
            "seat_id INT NOT NULL, " +
            "snap_movie_title VARCHAR(255) NOT NULL, " +
            "snap_hall_name VARCHAR(50) NOT NULL, " +
            "snap_show_time TIMESTAMP NOT NULL, " +
            "price DECIMAL(5, 2) NOT NULL, " +
            "pay_by VARCHAR(50) NOT NULL, " +
            "purchase_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "UNIQUE (schedule_id, seat_id), " +
        "FOREIGN KEY (customer_id) REFERENCES CUSTOMER(customer_id), " +
        "FOREIGN KEY (schedule_id) REFERENCES SCHEDULE(schedule_id), " + 
        "FOREIGN KEY (seat_id) REFERENCES SEAT(seat_id));";

        String createOrderTable = 
        "CREATE TABLE IF NOT EXISTS SNACKORDER (" +
            "order_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "customer_id INT NOT NULL, " + 
            "total_price DECIMAL(10, 2) NOT NULL, " +
            "order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (customer_id) REFERENCES CUSTOMER(customer_id));";


        String createOrderItemTable = 
        "CREATE TABLE IF NOT EXISTS ORDERITEM (" +
            "record_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "order_id INT NOT NULL, " +
            "item_name VARCHAR(100), " +
            "unit_price DECIMAL(10, 2), " +
            "quantity INT NOT NULL, " +
            "subtotal DECIMAL(10, 2) NOT NULL, " +
            
            "FOREIGN KEY (order_id) REFERENCES SNACKORDER(order_id) ON DELETE CASCADE);";
        String searchLog = 
        "CREATE TABLE IF NOT EXISTS SEARCH_LOG (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "customer_id INT NOT NULL, " +
            "keyword VARCHAR(100), " +
            "search_time VARCHAR(50), " +
            "note VARCHAR(255) DEFAULT 'None', " +
            "FOREIGN KEY (customer_id) REFERENCES CUSTOMER(customer_id));";
        //try to create table
        //try to bulid connetion with database
        try(Connection connect = connectToDatabase();){

            Statement databaseCommand = connect.createStatement();
            //create table
            databaseCommand.execute(createCustomerTable);
            databaseCommand.execute(createAdminTable);
            databaseCommand.execute(createAddDrinkTable);
            databaseCommand.execute(createAddPopcornTable);
            databaseCommand.execute(createMovieTable);
            databaseCommand.execute(createHallTable);
            databaseCommand.execute(createSeatTable);
            databaseCommand.execute(createScheduleTable);
            databaseCommand.execute(createTicketTable);
            databaseCommand.execute(createOrderTable);
            databaseCommand.execute(createOrderItemTable);
            databaseCommand.execute(searchLog);

        }catch(SQLException e){
            System.out.println("Database: has problem,solve it!");
            e.printStackTrace();
        }
    }
    public Connection connectToDatabase(){
        try{
            dataConnect = DriverManager.getConnection(databaseUrl, user, password);
            System.out.println("Database:database connect sucessfully!");
            return dataConnect;
        }catch(SQLException e){
            System.out.println("DataBase: has connecting problem,solve it!");
            e.printStackTrace();
            return null;
        }
    }

// ==========================================CUSTOMER=======================================================
    public boolean addCustomer(String name, String phone, String pin) {
        String sql = "INSERT INTO CUSTOMER (user_name, phone_number, six_digit_pin) VALUES (?, ?, ?)";
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, name);
            command.setString(2, phone);
            command.setString(3, pin);
            return command.executeUpdate() > 0;
            
        } catch (SQLException e) {
            return false;
        }
    }

    public Customer verifyCustomerLogin(String phone, String pin) {
        String sql = "SELECT * FROM CUSTOMER WHERE phone_number = ? AND six_digit_pin = ?";
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, phone);
            command.setString(2, pin);
            ResultSet result = command.executeQuery();
            
            if (result.next()) {
                return new Customer(
                    result.getInt("customer_id"),
                    result.getString("user_name"),
                    result.getString("phone_number"),
                    result.getString("six_digit_pin"),
                    result.getInt("loyalty_points")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER";
        
        try (Connection connect = connectToDatabase();
             Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                Customer cust = new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("user_name"),
                    rs.getString("phone_number"),
                    rs.getString("six_digit_pin"),
                    rs.getInt("loyalty_points")
                );
                customers.add(cust);
            }
        } catch (SQLException e) {
            System.out.println("DataBase: Error fetching customers!");
            e.printStackTrace();
        }
        
        return customers;
    }
    public boolean updateCustomerPoints(String phone, int pointsToAdd) {
        String sql = "UPDATE CUSTOMER SET loyalty_points = loyalty_points + ? WHERE phone_number = ?";
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setInt(1, pointsToAdd); 
            command.setString(2, phone);
            
            return command.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }  
    }
    public boolean deductCustomerPoints(String phone, int pointsToDeduct) {
        String sql = "UPDATE CUSTOMER SET loyalty_points = loyalty_points - ? WHERE phone_number = ? AND loyalty_points >= ?";
        //only work when point > 0
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setInt(1, pointsToDeduct); 
            command.setString(2, phone);
            command.setInt(3, pointsToDeduct);
            return command.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }  
    }
// ==========================================ADMIN====================================================
    public boolean addAdmin(String name, String phone, String pin) {
        String sql = "INSERT INTO ADMIN (user_name, phone_number, six_digit_pin) VALUES (?, ?, ?)";
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, name);
            command.setString(2, phone);
            command.setString(3, pin);
            return command.executeUpdate() > 0;
            
        } catch (SQLException e) {
            return false;
        }
    }

    public Admin verifyAdminLogin(String phone, String pin) {
        String sql = "SELECT * FROM ADMIN WHERE phone_number = ? AND six_digit_pin = ?";
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, phone);
            command.setString(2, pin);
            ResultSet result = command.executeQuery();
            
            if (result.next()) {
                return new Admin(
                    result.getInt("admin_id"),
                    result.getString("user_name"),
                    result.getString("phone_number"),
                    result.getString("six_digit_pin")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
//=====================================Custommer&Admin Management=================================================
    public boolean setName(String phone, String newName, String table) {
        String sql = "UPDATE " + table + " SET user_name = ? WHERE phone_number = ?";
            if (newName == null || newName.trim().isEmpty()) {
                return false;
            }

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, newName); 
            command.setString(2, phone);
            
            return command.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }  
    }
    public boolean setPin(String phone,String newPin, String table) {
        String sql = "UPDATE " + table + " SET six_digit_pin = ? WHERE phone_number = ?";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, newPin); 
            command.setString(2, phone);
            
            return command.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }  
    }
//=====================================Drinks===========================================================
    public boolean addDrink(String newDrinkFlavor, double price) {
        String sql = "INSERT INTO DRINK(drink_flavor, price) VALUES(?, ?)";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, newDrinkFlavor);
            command.setDouble(2, price);

            return command.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean removeDrink(String targetDrinkFlavor) {
        String sql = "DELETE FROM DRINK WHERE drink_flavor = ?";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
            command.setString(1, targetDrinkFlavor);

            int rowsAffected = command.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("DataBase: '" + targetDrinkFlavor + "' flavor removed successfully!");
                return true;
            } else {
                System.out.println("DataBase: Error! '" + targetDrinkFlavor + "' flavor not found.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("DataBase: Error!!!!");
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Drink>getAllDrink() {
        ArrayList<Drink>drinks = new ArrayList<>();
        String sql = "SELECT drink_flavor , price FROM drink";
        try(Connection connect = connectToDatabase();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
                while(rs.next()) { //look for the result, if NO then FALSE, else YES then TRUE
                    drinks.add(new Drink(rs.getString("drink_flavor"), rs.getDouble("price")));
                }
            } catch (SQLException e) {
                System.out.println("DataBase: Error fetching drinks!");
                e.printStackTrace();
            }
        return drinks;
    }
    public Drink getDrink(String flavor) {
        String sql = "SELECT drink_flavor , price FROM DRINK WHERE drink_flavor = ?";
        try(Connection connect = connectToDatabase();
            PreparedStatement command = connect.prepareStatement(sql)) {
                command.setString(1, flavor);
                ResultSet rs = command.executeQuery();
                if(rs.next()) { //look for the result, if NO then FALSE, else YES then TRUE
                    return new Drink(rs.getString("drink_flavor"), rs.getDouble("price"));
                }
            } catch (SQLException e) {
                System.out.println("DataBase: Error fetching drink!");
                e.printStackTrace();
            }
        return null;
    }
    
//===================================Popcorn==========================================
    public boolean addPopcorn(String newPopcornFlavor,double price) {
        String sql = "INSERT INTO POPCORNFLAVOR(popcorn_flavor, price) VALUES(?, ?)";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
            command.setString(1, newPopcornFlavor);
            command.setDouble(2, price);

            return command.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
    public boolean removePopcorn(String targetPopcornFlavor) {
        String sql = "DELETE FROM POPCORNFLAVOR WHERE popcorn_flavor = ?";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, targetPopcornFlavor);

            int rowsAffected = command.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("DataBase: '" + targetPopcornFlavor + "' flavor removed successfully!");
                return true;
            } else {
                System.out.println("DataBase: Error! '" + targetPopcornFlavor + "' flavor not found.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("DataBase: Error!!!!");
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Popcorn>getAllPopcorn() {
        ArrayList<Popcorn>popcorn = new ArrayList<>();
        String sql = "SELECT popcorn_flavor , price FROM POPCORNFLAVOR";
        try(Connection connect = connectToDatabase();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
                while(rs.next()) {
                    popcorn.add(new Popcorn(rs.getString("popcorn_flavor"), rs.getDouble("price")));
                }
            } catch (SQLException e) {
                System.out.println("DataBase: Error fetching popcorn!");
                e.printStackTrace();
            }
        return popcorn;
    }
    public Popcorn getPopcorn(String flavor) {
        String sql = "SELECT popcorn_flavor , price FROM POPCORNFLAVOR WHERE popcorn_flavor = ?";
        try(Connection connect = connectToDatabase();
            PreparedStatement command = connect.prepareStatement(sql)) {
                command.setString(1, flavor);
                ResultSet rs = command.executeQuery();
                if(rs.next()) { 
                    return new Popcorn(rs.getString("popcorn_flavor"), rs.getDouble("price"));
                }
            } catch (SQLException e) {
                System.out.println("DataBase: Error fetching popcorn!");
                e.printStackTrace();
            }
        return null;
    }
//=============================================================popcorn and drink price update===========================================================
    public boolean updatePopcornPrice(String flavor, double newPrice) {
        String sql = "UPDATE POPCORNFLAVOR SET price = ? WHERE popcorn_flavor = ?";
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setDouble(1, newPrice); 
            command.setString(2, flavor);
            
            return command.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }  
    }
    public boolean updateDrinkPrice(String flavor, double newPrice) {
        String sql = "UPDATE DRINK SET price = ? WHERE drink_flavor = ?";
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setDouble(1, newPrice); 
            command.setString(2, flavor);
            
            return command.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }  
    }
//=============================================================order===========================================================
    public boolean createFoodOrder(int customerId, double totalPrice, ArrayList<OrderItem> cart) {
        String sqlOrder = "INSERT INTO SNACKORDER (customer_id, total_price) VALUES (?, ?)";
        String sqlItem = "INSERT INTO ORDERITEM (order_id, item_name, unit_price, quantity, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection connect = connectToDatabase()) {
            connect.setAutoCommit(false); 

            try (PreparedStatement cmdOrder = connect.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement cmdItem = connect.prepareStatement(sqlItem)) {
                 
                cmdOrder.setInt(1, customerId);
                cmdOrder.setDouble(2, totalPrice);
                int affectedRows = cmdOrder.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = cmdOrder.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int newOrderId = generatedKeys.getInt(1); 
                            
                            for (OrderItem item : cart) {
                                cmdItem.setInt(1, newOrderId);
                                cmdItem.setString(2, item.getItemname());
                                cmdItem.setDouble(3, item.getPrice()); 
                                cmdItem.setInt(4, item.getQuantity());
                                cmdItem.setDouble(5, item.getSubtotal());
                                
                                cmdItem.addBatch(); 
                            }
                            cmdItem.executeBatch(); 
                        }
                    }
                }
                connect.commit();
                return true;

            } catch (SQLException e) {
                connect.rollback(); 
                e.printStackTrace();
                return false;
            } finally {
                connect.setAutoCommit(true); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Order> getOrders(Customer customer) {
        ArrayList<Order> orders = new ArrayList<>();
        
        String sql = "SELECT o.order_id, o.total_price, o.order_time, " +
                     "i.item_name, i.unit_price, i.quantity, i.subtotal " +
                     "FROM SNACKORDER o " +
                     "JOIN ORDERITEM i ON o.order_id = i.order_id " +
                     "WHERE o.customer_id = ? " +
                     "ORDER BY o.order_time DESC, o.order_id DESC";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setInt(1, customer.getId());
            ResultSet result = command.executeQuery();
            
            Order currentOrder = null;

            while (result.next()) {
                int orderId = result.getInt("order_id");
                if (currentOrder == null || currentOrder.getOrderId() != orderId) {
                    currentOrder = new Order(
                        orderId, 
                        customer, 
                        result.getTimestamp("order_time").toLocalDateTime(), 
                        result.getDouble("total_price")
                    );
                    orders.add(currentOrder);
                }
                OrderItem item = new OrderItem(
                    result.getString("item_name"), 
                    result.getDouble("unit_price"), 
                    result.getInt("quantity"), 
                    result.getDouble("subtotal")
                );
                currentOrder.addItem(item); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
//============================================movie====================================
    public boolean addMovie(String title, int duration_min) {
        String sql = "INSERT INTO MOVIE(movie_title, duration_min) VALUES(?, ?)";
        
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, title);
            command.setInt(2, duration_min);

            return command.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }

    public boolean removeMovie(int targetMovieid) {
        String sql = "DELETE FROM MOVIE WHERE movie_id = ?";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setInt(1, targetMovieid);

            int rowsAffected = command.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM MOVIE";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql);
             ResultSet result = command.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("movie_id");
                String title = result.getString("movie_title");
                int duration = result.getInt("duration_min");

                Movie movie = new Movie(id, title, duration);
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    public Movie getMovie(int movieId) {
        String sql = "SELECT * FROM MOVIE WHERE movie_id = ?";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setInt(1, movieId);
            ResultSet result = command.executeQuery();

            if (result.next()) {
                return new Movie(
                    result.getInt("movie_id"),
                    result.getString("movie_title"),
                    result.getInt("duration_min")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//============================================hall and seat====================================
    public boolean addHall(String hallType, int numRows, int seatsPerRow) {
        String sqlHall = "INSERT INTO HALL (hall_type) VALUES (?)";
        String sqlSeat = "INSERT INTO SEAT (hall_id, row_letter, seat_number) VALUES (?, ?, ?)";
        
        try (Connection connect = connectToDatabase();
             PreparedStatement cmdHall = connect.prepareStatement(sqlHall, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement cmdSeat = connect.prepareStatement(sqlSeat)) {
             
            cmdHall.setString(1, hallType);
            if (cmdHall.executeUpdate() > 0) {
                try (ResultSet generatedKeys = cmdHall.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newHallId = generatedKeys.getInt(1);
                        
                        for (int r = 1; r <= numRows; r++) {
                            // if r=1,，'A' + 0 = 'A'
                            // if r=2,，'A' + 1 = 'B'
                            char rowLetter = (char) ('A' + (r - 1)); 
                            for (int c = 1; c <= seatsPerRow; c++) {
                                cmdSeat.setInt(1, newHallId);
                                cmdSeat.setString(2, String.valueOf(rowLetter)); 
                                cmdSeat.setInt(3, c); 
                                cmdSeat.executeUpdate(); 
                            }
                        }
                        return true; 
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
    public boolean alterSeatType(int hallId, char rowLetter, int seatNumber, String newType) {
        String sql = "UPDATE SEAT SET seat_type = ? WHERE hall_id = ? AND row_letter = ? AND seat_number = ?";
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, newType);
            command.setInt(2, hallId);
            command.setString(3, String.valueOf(rowLetter));
            command.setInt(4, seatNumber);

            return command.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
    public ArrayList<Hall> getAllHalls() {
        ArrayList<Hall> halls = new ArrayList<>();
        String sqlHall = "SELECT * FROM HALL";

        try (Connection connect = connectToDatabase();
             PreparedStatement cmdHall = connect.prepareStatement(sqlHall);
             ResultSet rsHall = cmdHall.executeQuery()) {

            while (rsHall.next()) {
                int hallId = rsHall.getInt("hall_id");
                String hallType = rsHall.getString("hall_type");
                Hall hall = new Hall(hallId, hallType);
                halls.add(hall);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return halls;
    }
    public Hall getHall(int hallId) {
        String sqlHall = "SELECT * FROM HALL WHERE hall_id = ?";

        try (Connection connect = connectToDatabase();
             PreparedStatement cmdHall = connect.prepareStatement(sqlHall)) {
             
            cmdHall.setInt(1, hallId);
            ResultSet rsHall = cmdHall.executeQuery();

            while (rsHall.next()) {
                String hallType = rsHall.getString("hall_type");
                Hall hall = new Hall(hallId, hallType);
                return hall;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Hall> getAllHallsWithSeatCount() {
        ArrayList<Hall> halls = new ArrayList<>();
        
        String sql = "SELECT h.hall_id, h.hall_type, COUNT(s.seat_id) AS total_seats " +
                     "FROM HALL h " +
                     "LEFT JOIN SEAT s ON h.hall_id = s.hall_id " +
                     "GROUP BY h.hall_id, h.hall_type " +
                     "ORDER BY h.hall_id ASC";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql);
             ResultSet result = command.executeQuery()) {

            while (result.next()) {
                halls.add(new Hall(
                    result.getInt("hall_id"),
                    result.getString("hall_type"),
                    result.getInt("total_seats")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return halls;
    }
//============================================schedule====================================
    public boolean addSchedule(Movie movie, int hallId, LocalDateTime showTime, double basePrice) {
        
        int durationMins = movie.getDurationInMin(); 
        LocalDateTime endTime = showTime.plusMinutes(durationMins + 15);
        if (!isTimeSlotAvailable(hallId, showTime, endTime)) {
            return false; 
        }

        String sql = "INSERT INTO SCHEDULE (movie_id, hall_id, show_time, end_time, base_price) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setInt(1, movie.getid());
            command.setInt(2, hallId);
            
            command.setObject(3, showTime);
            command.setObject(4, endTime);
            
            command.setDouble(5, basePrice);

            return command.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Database Error: Failed to add schedule.");
            e.printStackTrace(); 
            return false;
        }
    }

    public boolean isTimeSlotAvailable(int hallId, LocalDateTime newStartTime, LocalDateTime newEndTime) {
        String checkSql = "SELECT COUNT(*) FROM SCHEDULE " +
                          "WHERE hall_id = ? " +
                          "AND show_time < ? " +
                          "AND end_time > ?";

        try (Connection connect = connectToDatabase();
             PreparedStatement checkCmd = connect.prepareStatement(checkSql)) {
             
            checkCmd.setInt(1, hallId);
            checkCmd.setObject(2, newEndTime);
            checkCmd.setObject(3, newStartTime);
            
            ResultSet rs = checkCmd.executeQuery();
            if (rs.next()) {
                int conflictCount = rs.getInt(1);
                return conflictCount == 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }    
    public Schedule getSchedule(int scheduleId) {
        String sql = "SELECT s.schedule_id, s.movie_id, s.hall_id, s.show_time, s.end_time, s.base_price, " +
                     "m.movie_title, h.hall_type " +
                     "FROM SCHEDULE s " +
                     "JOIN MOVIE m ON s.movie_id = m.movie_id " +
                     "JOIN HALL h ON s.hall_id = h.hall_id " +
                     "WHERE s.schedule_id = ?";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setInt(1, scheduleId);
            ResultSet result = command.executeQuery();

            if (result.next()) {
                return new Schedule(
                    result.getInt("schedule_id"),
                    new Movie(result.getInt("movie_id"), result.getString("movie_title"), 0), 
                    new Hall(result.getInt("hall_id"), result.getString("hall_type")), 
                    result.getTimestamp("show_time").toLocalDateTime(),
                    result.getTimestamp("end_time").toLocalDateTime(),
                    result.getDouble("base_price")
                );
            }
        } catch (SQLException e) {
            System.out.println("Database Error: Failed to retrieve schedule.");
            e.printStackTrace(); 
        }
        return null; 
    }
    public ArrayList<Schedule> getHallSchedule(Hall hall){
        ArrayList<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT s.schedule_id, s.movie_id, s.hall_id, s.show_time, s.end_time, s.base_price, " +
                     "m.movie_title, h.hall_type " +
                     "FROM SCHEDULE s " +
                     "JOIN MOVIE m ON s.movie_id = m.movie_id " +
                     "JOIN HALL h ON s.hall_id = h.hall_id " +
                     "WHERE s.hall_id = ?";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setInt(1, hall.getHallId());
            ResultSet result = command.executeQuery();

            while (result.next()) {
                schedules.add(new Schedule(
                    result.getInt("schedule_id"),
                    new Movie(result.getInt("movie_id"), result.getString("movie_title"), 0), 
                    new Hall(result.getInt("hall_id"), result.getString("hall_type")), 
                    result.getTimestamp("show_time").toLocalDateTime(),
                    result.getTimestamp("end_time").toLocalDateTime(),
                    result.getDouble("base_price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Database Error: Failed to retrieve schedule.");
            e.printStackTrace(); 
        }
        return schedules;
    }
    public ArrayList<Schedule> getAllSchedules() {
        ArrayList<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT s.schedule_id, s.movie_id, s.hall_id, s.show_time, s.end_time, s.base_price, " +
                     "m.movie_title, h.hall_type " +
                     "FROM SCHEDULE s " +
                     "JOIN MOVIE m ON s.movie_id = m.movie_id " +
                     "JOIN HALL h ON s.hall_id = h.hall_id";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql);
             ResultSet result = command.executeQuery()) {

            while (result.next()) {
                Schedule schedule = new Schedule(
                    result.getInt("schedule_id"),
                    new Movie(result.getInt("movie_id"), result.getString("movie_title"), 0), 
                    new Hall(result.getInt("hall_id"), result.getString("hall_type")), 
                    result.getTimestamp("show_time").toLocalDateTime(),
                    result.getTimestamp("end_time").toLocalDateTime(),
                    result.getDouble("base_price")
                );
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println("Database Error: Failed to retrieve schedules.");
            e.printStackTrace(); 
        }
        return schedules;
    }

//============================================ticket====================================
    public boolean insertTicket(int customerId, int scheduleId, int seatId, String snapMovieTitle, String snapHallName, LocalDateTime snapShowTime, double price, String payBy) {
        String sql = "INSERT INTO TICKET (customer_id, schedule_id, seat_id, snap_movie_title, snap_hall_name, snap_show_time, price, pay_by) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setInt(1, customerId);
            command.setInt(2, scheduleId);
            command.setInt(3, seatId);
            command.setString(4, snapMovieTitle);
            command.setString(5, snapHallName);
            command.setTimestamp(6, Timestamp.valueOf(snapShowTime));
            command.setDouble(7, price);
            command.setString(8, payBy);

            return command.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Database Error: Failed to book ticket.");
            e.printStackTrace(); 
            return false;
        }
    }
    public ArrayList<Ticket> getTicketsByCustomer(Customer customer) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM TICKET JOIN seat ON ticket.seat_id = seat.seat_id WHERE customer_id = ?  ORDER BY purchase_time DESC";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setInt(1, customer.getId());
            ResultSet result = command.executeQuery();

            while (result.next()) {
                Ticket ticket = new Ticket(
                    result.getInt("ticket_id"),
                    customer,
                    new Seat(
                        result.getInt("seat_id"), 
                        result.getInt("hall_id"), 
                        result.getString("row_letter").charAt(0), 
                        result.getInt("seat_number"),   
                        result.getString("seat_type")
                    ),
                    result.getString("snap_movie_title"),
                    result.getString("snap_hall_name"),
                    result.getTimestamp("snap_show_time").toLocalDateTime(),
                    result.getTimestamp("purchase_time").toLocalDateTime(),
                    result.getDouble("price")
                );
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Database Error: Failed to retrieve tickets.");
            e.printStackTrace(); 
        }
        return tickets;
    }
// ========================================== SEARCH HISTORY ==========================================
    public ArrayList<Movie> searchMoviesByTitle(String keyword, int customerId) {
        saveSearchLog(keyword, customerId);
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM MOVIE WHERE movie_title LIKE ?";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql)) {
             
            command.setString(1, "%" + keyword + "%");
            ResultSet result = command.executeQuery();

            while (result.next()) {
                movies.add(new Movie(
                    result.getInt("movie_id"),
                    result.getString("movie_title"),
                    result.getInt("duration_min")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return movies;
    }
    public boolean saveSearchLog(String keyword, int customerId) {
        String sql = "INSERT INTO SEARCH_LOG (keyword, search_time, customer_id) VALUES (?, ?, ?)";
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        try (Connection connect = connectToDatabase();
             PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setString(1, keyword);
            pstmt.setString(2, time);
            pstmt.setInt(3, customerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<SearchLog> getAllSearchLogs(int customerId) {
        ArrayList<SearchLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM SEARCH_LOG WHERE customer_id = ? ORDER BY id DESC";

        try (Connection connect = connectToDatabase();
             PreparedStatement stmt = connect.prepareStatement(sql)) {
            
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                logs.add(new SearchLog(
                    rs.getInt("id"),
                    rs.getString("keyword"),
                    rs.getString("search_time"),
                    rs.getString("note")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    public boolean clearAllSearchLogs(int customerId) {
        String sql = "DELETE FROM SEARCH_LOG WHERE customer_id = ?";
        try (Connection connect = connectToDatabase();
             PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//============================================report and statics====================================
    public ArrayList<MovieSales> getMostPopularMovies() {
        ArrayList<MovieSales> popularMovies = new ArrayList<>();
        String sql = "SELECT snap_movie_title, COUNT(*) AS ticket_count " +
                     "FROM TICKET " +
                     "GROUP BY snap_movie_title " +
                     "ORDER BY ticket_count DESC";

        try (Connection connect = connectToDatabase();
             PreparedStatement command = connect.prepareStatement(sql);
             ResultSet result = command.executeQuery()) {

            while (result.next()) {
                String movieTitle = result.getString("snap_movie_title");
                int count = result.getInt("ticket_count");
                popularMovies.add(new MovieSales(movieTitle, count));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return popularMovies;
    }
}