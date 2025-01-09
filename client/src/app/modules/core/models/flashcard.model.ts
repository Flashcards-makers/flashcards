export interface AddFlashcardsData {
  name: string;
  description: string;
  icon: string;
  isPublic: boolean;
  pages: Page[]
}

export interface Page {
  question: string;
  questionImage: string;
  answer: string;
  answerImage: string;
}
