export interface PageDetailsData {
  id: number;
  flashcardId: number;
  question: string;
  questionImage: string;
  answer: string;
  answerImage: string;
  createdAt: string;
}

export interface ResolvePageData {
  pageId: number;
  answer: string;
  isCorrect: boolean;
}
