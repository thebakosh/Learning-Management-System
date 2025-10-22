package Decorators;
import Interfaces.*;

import java.util.Scanner;

public class GamificationDecorator extends CourseDecorator implements ScoredCourse {
    int score = 0;
    String[] questions;
    String[] answers;


    public GamificationDecorator(iCourse decoratedCourse, String[] questions, String[] answers) {
        super(decoratedCourse);
        this.questions = questions;
        this.answers = answers;
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        addGamification();
        score = conductTest();
        System.out.println("Test completed. You scored: " + score + " points.");
    }

    private void addGamification() {
        System.out.println("Adding points and leaderboards to the course.");
    }

    private int conductTest() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            String answer = scanner.nextLine();
            if (answer.equals(answers[i])) {
                score++;
            }
        }

        return score;
    }

    @Override
    public int getLastScore() {
        return score;
    }

}
