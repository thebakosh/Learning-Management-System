package Client;

import Certificates.*;
import Courses.*;
import Decorators.*;
import Interfaces.*;
import Teachers.*;

import java.sql.SQLException;
import java.util.Scanner;
import User.UserAuthentication;

import static User.DatabaseHelper.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the LMS system!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Please select an option (1 or 2): ");

        int choice = 0;

        boolean validInput = false;
        while (!validInput) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1 || choice == 2) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice! Please select 1 or 2.");
                    System.out.print("Please select an option (1 or 2): ");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number (1 or 2).");
                scanner.nextLine();
                System.out.print("Please select an option (1 or 2): ");
            }
        }

        try {
            if (choice == 1) {
                System.out.println("Registering new user...");
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                UserAuthentication.registerUser(username, password);
                System.out.println("Registration successful!");

            } else if (choice == 2) {
                System.out.println("Login...");
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (UserAuthentication.loginUser(username, password)) {
                    System.out.println("Login successful!");

                    System.out.println("Please select a course to enroll in:");
                    System.out.println("1. Math Course");
                    System.out.println("2. Programming Course");
                    int courseChoice = 0;

                    boolean validCourseInput = false;
                    while (!validCourseInput) {
                        try {
                            courseChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (courseChoice == 1 || courseChoice == 2) {
                                validCourseInput = true;
                            } else {
                                System.out.println("Invalid choice! Please select 1 or 2.");
                                System.out.print("Please select a course (1 or 2): ");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input! Please enter a number (1 or 2).");
                            scanner.nextLine(); // consume invalid input
                            System.out.print("Please select a course (1 or 2): ");
                        }
                    }
                    if (courseChoice == 1) {
                        System.out.println("You have selected Math Course!");

                        String[] mathQuestions = {"What is 1 + 2?", "What is 3 + 5?", "What is 9 + 8?"};
                        String[] mathAnswers = {"3", "8", "17"};

                        iCourse mathCourse = new MathCourse();
                        iCertificate mathCertificate = new MathCertificate();
                        iTeacher mathTeacher = new MathTeacher();
                        iCourse mathWithMentor = new MentorSupportDecorator(mathCourse);
                        iCourse mathWithTeacher = new TeacherDecorator(mathWithMentor, mathTeacher);
                        iCourse mathWithGamification = new GamificationDecorator(mathWithTeacher, mathQuestions, mathAnswers);

                        StudentPortalFacade studentPortal = new StudentPortalFacade(mathWithGamification, mathCertificate);
                        studentPortal.enrollInCourse();
                        studentPortal.startLearning();
                        int score = studentPortal.completeCourse();
                        int courseId = getCourseIdByName("MathCourse");
                        int userId = getUserIdByName(username);

                        System.out.println("Your final score in the Math Course is: " + score);
                        saveTestResult(userId, courseId, score);

                    } else if (courseChoice == 2) {
                        System.out.println("You have selected Programming Course!");

                        String[] progQuestions = {
                                "System.out.println(2); What will be output?",
                                "What is the value of 3 * 3?",
                                "What does 'int' mean in Java?"
                        };
                        String[] progAnswers = {"2", "9", "integer"};

                        iCourse programmingCourse = new ProgrammingCourse();
                        iCertificate programmingCertificate = new ProgrammingCertificate();
                        iTeacher programmingTeacher = new ProgrammingTeacher();
                        iCourse programmingWithMentor = new MentorSupportDecorator(programmingCourse);
                        iCourse programmingWithTeacher = new TeacherDecorator(programmingWithMentor, programmingTeacher);
                        iCourse programmingWithGamification = new GamificationDecorator(programmingWithTeacher, progQuestions, progAnswers);

                        StudentPortalFacade studentPortal = new StudentPortalFacade(programmingWithGamification, programmingCertificate);


                        studentPortal.enrollInCourse();
                        studentPortal.startLearning();
                        int score = studentPortal.completeCourse();
                        int courseId = getCourseIdByName("ProgrammingCourse");
                        int userId = getUserIdByName(username);
                        System.out.println("Your final score in the Programming Course is: " + score);
                        saveTestResult(userId, courseId, score);
                    }

                } else {
                    System.out.println("Incorrect username or password.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while working with the database: " + e.getMessage());
        }
    }
}
