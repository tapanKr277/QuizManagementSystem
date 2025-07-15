import { Question } from "./question";

export class Quiz {
    quizId: number;
    title: string;
    category: string;
    difficultyLevel: string;
    questionList: Question[];
}