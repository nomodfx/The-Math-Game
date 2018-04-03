//This is a simple math game to teach children basic calculations
//Generates random math problems
//while rewarding them with $0.05 for correct answers and $0.03 deductions for incorrect solutions
import java.io*;
import java.util.Random;
import java.util.Scanner;

public class TheMathGame {

  //Static function displays splash screen with credits
  //also validates user's choice to replay or end the current game
  Scanner input = new Scanner(System.in);

  String userContinue;
  boolean yesContinue;

  System.out.println("***********************");
  System.out.println("***********************");
  System.out.println("***********************");
  System.out.println("******TheMathGame******");
  System.out.println("******By nomodfx ******");
  System.out.println("******           ******");
  System.out.println("***********************");
  System.out.println("***********************");
  System.out.println("***********************");

  System.out.println("\n\nTo continue the game press y. " +
  "Any other key will exit the game.");

  //Validates if user wants to continue on
  //If they do, displays menu screen
  do {
    userContinue = input.nextLine().trim().toLowerCase();

    if(userContinue.equals("y") || userContinue.equals("Y"))
    {
      yesContinue = true;
      break;
    }
    else
    {
      yesContinue = false;
      System.exit(0);
    }
  } while ((userContinue.equals("y")) || (userContinue.equals("Y")));
}

//Scope variables are accessed by entire program
public static int wrongSolution;
public static int rightSolution;
public static String userName;

//Main consists of mostly function calls
public static void main(String[] args) throws IOException {

  //Retrieves statistics from text file of returning player
  retrieveStats();

  boolean quit = false;

  while(!quit)
  {
    //Displays the menu
    String userInput = menu();

    //Validates user's input is withing menu possible menu categories
    if(!validateUserResponse(userInput))
      continue;

    int menuSelection = Integer.parseInt(userInput);

    //Select operation based on menu opetion
    switch(menuSelection) {
      case 1:
        generateAddition();
        break;

      case 2:
        generateSubtraction();
        break;

      case 3:
        generateMultiplication();
        break;

      case 4:
        generateDivision();
        break;

      case 5:
        displayStats();
        break;

      case 6:
        credits();
        break;

      case 7:
        //Saves player's stats when exiting
        saveStats();
        quit = true;
        break;
    }
  }
}

//This function saves/writes player stats on new external text file
public static void saveStats() throws IOException {

  //Saves to text file
  PrintWriter out = new PrintWriter(userName + ".txt");
  out.write(userName + "\n\r");
  out.write("\n\r" + rightSolution + "\n\r");
  out.write("\n\r" + wrongSolution + "\n\r");
  out.write("\n\r" + (rightSolution * 0.05 - wrongSolution * 0.03));
  out.close();
}

//This function will display saved stats on screen
public static void displayStats() {

  //Displays stats upon player's request
  System.out.println("");
  System.out.println("************************************");
  System.out.println("************************************");
  System.out.println("******                        ******");
  System.out.println("******" + userName+ "                 ******");
  System.out.println("******" +"Total correct: " + rightSolution
                + "        ******");
  System.out.println("******"+"Total wrong: " + wrongSolution
               + "          ******");
  System.out.println("******"+ "Earnings: " + String.format("$ %.2f",
                       (rightSolution * 0.05 - wrongSolution * 0.03))
               + "       ******");
  System.out.println("******                        ******");
  System.out.println("************************************");
  System.out.println("************************************");
  System.out.println("");

   }

  //This function will keep the total running games in RAM
  public static void updateStas(boolean updateCorrect) {
    //Will update stats everytime the user chooses stat option
    if(updateCorrect) {
      rightSolution++;
    } else {
      wrongSolution++;
    }
  }

  //Function will check if answer entered is correct or incorrect given random problems
  public static boolean checkUserAnswer(int userInput, int answer) {
    if(userInput == answer) {
      System.out.println("******CORRECT ANSWER!!******");
      System.out.println("");
      return true;
    }
    else
    {
      System.out.println("******WRONG ANSWER!!******");
      System.out.println("");
      return false;
    }
  }

  //Validates user input is ONLY a numeric answer
  public static String validateUserAnswer(String userAnswer) {
    int userInputLength = userAnswer.length();
    int counter = 0;
    Scanner input  = new Scanner(System.in);

    //Checks if input is blank
    while(userInputLength == 0)
    {
      System.out.println("This is not a postitive integer. Enter a number!");
      userAnswer = input.nextLine();
      userInputLength = userAnswer.length();
    }

    //Checks if input is not blank
    while(counter < userInputLength)
    {
      if(!Character.isDigit(userAnswer.charAt(counter)))
      {
        System.out.println("This is not a positive integer. Enter a number!");
        userAnswer = input.nextLine();
        userInput = userAnswer.length();
        counter = 0;
      }

      else
      {
        counter++;
      }
      while(userInputLength == 0)
      {
        System.out.println("This is not a positive integer. Enter a number!");
        userAnswer = input.nextLine();
        userInputLength = userAnswer.length();
      }
    }
    return userAnswer;
  }

  //This function will create random addition problems
  public static int generateAddition() {
    Scanner input = new Scanner(System.int);
    int solution = 0;
    final int MAX_LIMIT = 10;

    //Random number generator creates addition problems
    Random rand = new random();
    int first = rand.nextInt(MAX_LIMIT);
    int second = rand.nextInt(MAX_LIMIT);

    System.out.println("******ADDITION******");
    System.out.println("********************");
    System.out.println("********************");
    System.out.println("**** " + first + " + " + second+" = ?" + " ****");
    System.out.println("********************");
    System.out.println("********************");

    //Accepts user response
    solution = first + second;

    String userAnswer = input.nextLine();
    userAnswer = validateUserAnswer(userAnswer);

    //Validates user's solutions to make sure if correct vs incorrect
    userAnswer = validateUserAnswer(userAnswer);

    //Update stats after validation
    updateStats(checkUserAnswer(Integer.parseInt(userAnswer), solution));

    return solution;
  }

  //Function creates random subtraction problems
  public static int generateSubtraction() {
    Scanner input = new Scanner(System.in);
    int solution = 0;
    final int MAX_LIMIT = 10;

    Scanner input = new Scanner(System.in);
    int solution = 0;
    final int MAX_LIMIT = 10;

    //Random number generator creates subtraction problems
    Random rand = new Random();
    int first = rand.nextInt(MAX_LIMIT);
    int second = rand.nextInt(MAX_LIMIT);

    //Loop makes sure generated problem only contains positive and not negative numbers
    if(first < second)
    {
      int positive = first;
      first = second;
      second = positive;
    }

    System.out.println("****SUBTRACTION****");
    System.out.println("********************");
    System.out.println("********************");
    System.out.println("**** " + first + " - " + second +" = ?" + " ****");
    System.out.println("********************");
    System.out.println("********************");

    //Accepts user response
    solution = first - second;

    String userAnswer = input.nextLine();
    userAnswer = validateUserAnswer(userAnswer);

    userAnswer = validateUserAnswewr(userAnswer);

    //Update stats
    updateStats(checkUserAnswer(Integer.parseInt(userAnswer), solution));

    return solution;
  }

  //Function randomly generates multiplication problems
  public static int generateMultiplication() {

    Scanner input = new Scanner(System.in);
    int solution = 0;
    final int MAX_LMIT = 10;

    Random rand = new Random();
    int first = rand.nextInt(MAX_LIMIT);
    int second = rand.nextInt(MAX_LIMIT);

    System.out.println("***MULTIPLICATION***");
    System.out.println("********************");
    System.out.println("********************");
    System.out.println("**** " + first + " * " + second +" = ?"+ " ****");
    System.out.println("********************");
    System.out.println("********************");

    solution = first * second;

    String userAnswer = input.nextLine();
    userAnswer = validateUserAnswer(userAnswer);

    userAnswer = valdiateUserAnswer(userAnswer);

    //Update stats
    updateStats(checkUserAnswer(Integer.parseInt(userAnswer), solution));

    return solution;
  }

  //Function will generate random division problems
  public static int generateDivision() {

    Scanner input = new Scanner(System.in)
    int solution = 0;
    final int MAX_LIMIT = 10;

    random rand = new Random();
    int first = rand.nextInt(MAX_LIMIT);

    //Avoid division by zero
    int second = rand.nextInt(MAX_LIMIT - 1) + 1;

    System.out.println("******DIVISION******");
    System.out.println("********************");
    System.out.println("********************");
    System.out.println("**** " + first * second + " / " + second + " = ?"+
                " ****");
    System.out.println("********************");
    System.out.println("********************");

    solution = (first * second / second);

    String userAnswer = input.nextLine();
    userAnswer = validateUserAnswer(userAnswer);

    userAnswer = validateUserAnswer(userAnswer);

    //update stats
    updateStats(checkUserAnswer(Integer.parseInt(userAnswer), solution));

    return solution;

  }


  //Validates user input regarding menu options
  public static boolean validateUserResponse(String input) {
    //Throws exception make sure user input it between 1-4
    try {
      int userOptionChoice = Integer.parseInt(input);

      if(userOptionChoice >= 1 && userOptionChoice <= 7)
      {
        return true;
      }
      System.out.println("This is an invalid menu option");
      System.out.println("");
      return false;
    }

    //catches exception to prevent input of chars
    catch(Exception nonIntegerInput)
    {
      System.out.println("This is an invalid menu option");
      System.out.println("");
      return false;
    }
  }

  //This function is used to display user input from menu
  public static String menu() {
    Scanner input = new Scanner(System.in);

    System.out.println("******CHOOSE A PROBLEM******");
    System.out.println("****************************");
    System.out.println("****************************");
    System.out.println("*******               ******");
    System.out.println("****** 1.ADD           *****");
    System.out.println("****** 2.SUBTRACT     ******");
    System.out.println("****** 3.MULTIPLY     ******");
    System.out.println("****** 4.DIVISION     ******");
    System.out.println("****** 5.STATS        ******");
    System.out.println("****** 6.CREDITS      ******");
    System.out.println("****** 7.EXIT         ******");
    System.out.println("******                ******");
    System.out.println("****************************");
    System.out.println("****************************");

    return (input.next());

    //This function displays the CREDITS and gives brief explanation of program purpose
    public static void credits() {
      System.out.println("");
       System.out.println("TheMathGame"
       + "\nBy nomodfx");
       System.out.println("");
       System.out.println("The program generates random math problems for students to answer.\n\r"
               + "You will win $0.05 for each correct answer "
               + "and lose $0.03 for each wrong answer.");
       System.out.println("");
    }

    //Function will only retrieve player data
    public static void retrieveStats() throws IOException {
      System.out.println("Enter your name and press <Enter>");

      Scanner input = new Scanner(System.in);
      userName = input.nextLine();

      //Validates to ensure name is only chars
      int userNameLength = userName.length();
      int counter = 0;

      while(userNameLength == 0)
      {
        System.out.println("Nothing was entered.");
        System.out.println("Enter your name and press <Enter>");
        userName = input.nextLine();
        userNameLength = userName.length();

      }

      //Checks to see if input is not blank
      while(counter < userNameLength)
      {
        if(!Character.isLetter(userName.charAt(counter)))
        {
          System.out.println("That is not a name");
          System.out.println("Enter your name and press <Enter>");
          userName = input.nextLine();
          userNameLength = userName.length();
          counter = 0;
        }

        //Will check is whitespace is present
        else if(userName.trim().isEmpty())
        {
          System.out.println("That is not a name.");
          System.out.println("Enter your name and press <Enter>");
          userName = input.nextLine();
          userNameLength = userName.length();
        }

        else
        {
          counter++;
        }
        while(userNameLength == 0)
       {
       System.out.println("Nothing was entered.");
       System.out.println("Enter your name and press <Enter>");
       userName = input.nextLine();
       userNameLength = userName.length();
       }

       //Checks to see if input if not blank with counter
       while(counter < userNameLength)
       {
           if(!Character.isLetter(userName.charAt(counter)))
           {
               System.out.println("That is not a name. ");
               System.out.println("Enter your name and press <Enter>");
               userName = input.nextLine();
               userNameLength = userName.length();
               counter = 0;
           }

           //Will check to see if a whitespace is present
           else if(userName.trim().isEmpty())
           {
                System.out.println("That is not a name.");
                System.out.println("Enter your name and press <Enter>");
                userName = input.nextLine();
                userNameLength = userName.length();
           }
           else
           {
               counter++;
           }
           while(userNameLength == 0)
           {
                System.out.println("That is not a name.");
                System.out.println("Enter your name and press <Enter>");
                userName = input.nextLine();
                userNameLength = userName.length();
          }
      String filename = userName + ".txt";
      File file = new File(filename);

      //Checks if external txt file exists for returning player
      if(file.exists())
      {

          //Will read from external file
          BufferedReader output = new BufferedReader(new FileReader(file));
          rightSolution = output.read();
          wrongSolution = output.read();
          output.close();

      }

      /*Sets values to zero if statistics are not available
        for new players only if file doesn't exist */
      else
      {
          rightSolution = 0;
          wrongSolution = 0;
      }
    }
  }
}
