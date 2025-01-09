import { FormControl } from '@angular/forms';

export interface AddFlashcardsForm {
  name: FormControl<string>;
  description: FormControl<string>;
  icon: FormControl<string>;
  isPublic: FormControl<boolean>;
  pages: FormControl<Page[]>
}

export interface Page {
  question: FormControl<string>;
  questionImage: FormControl<string>;
  answer: FormControl<string>;
  answerImage: FormControl<string>;
}
