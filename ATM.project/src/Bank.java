import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    /**
     * Create a new Bank object with empty lists of users and accounts
     * @param name  the name of the bank
     */
    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();

    }

    /**
     * Generate a new universally unique ID for a user
     * @return  the uuid
     */
    public String getNewUserUUID() {
        //initialize
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique = false;

        //continue looping until we get a unique ID
        do {
            //generate the number
            uuid = "";
            for (int c =0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            //check and make sure it is unique
            nonUnique= false;
            for (User u : this.users){
                if (uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        }while (nonUnique);


        return uuid;

    } // to not forget to make it exist

    public String getNewAccountUUID() {
        //initialize
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique = false;

        //continue looping until we get a unique ID
        do {
            //generate the number
            uuid = "";
            for (int c =0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            //check and make sure it is unique
            nonUnique= false;
            for (Account a : this.accounts){
                if (uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        }while (nonUnique);
        return uuid;

    } // skeleton to help layout the general structure of how classes work

    /**
     * Add an account
     * @param anAcct    the account to add
     */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    /**
     * Create a new user of the bank
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's pin
     * @return          the new User object
     */
    public User addUser(String firstName,String lastName,String pin ){
        // create a new User object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //create a savings account for the user and add to User and Bank account lists
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount); // Add an account
        this.accounts.add(newAccount);

        return newUser;

    }

    /**
     * Get the User object associated with a particular userID and pin, if they are valid
     * @param userID the UUID of the user
     * @param pin   the pin of the user
     * @return      the User object, if the login is successful, or null, if it is not
     */
    public User userLogin(String userID, String pin){

        // search through list of users to find correct ID
        for (User u : this.users){

            //check user ID is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }
        //if user or pin not valid
        return null;
    }

    /**
     * Get the name of the Bank
     * @return  the name of the Bank
     */
    public String getName(){
        return this.name;

    }

}
