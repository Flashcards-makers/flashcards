import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FlashcardService } from '../../../auth/services/flashcard.service';

@Component({
  selector: 'app-add-flashcard',
  templateUrl: './add-flashcard.component.html',
  styleUrls: ['./add-flashcard.component.scss'],
})
export class AddFlashcardComponent {
  flashcardForm: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private flashcardService: FlashcardService,
  ) {
    this.flashcardForm = this.fb.group({
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
    });
  }

  onSubmit(): void {
    if (this.flashcardForm.valid) {
      const flashcardData = this.flashcardForm.value;

      this.flashcardService.addFlashcard(flashcardData).subscribe({
        next: () => {
          this.successMessage = 'Flashcard added successfully!';
          this.errorMessage = null;
          this.flashcardForm.reset();
        },
        error: (err: unknown) => {
          console.error('Error adding flashcard:', err);
          this.errorMessage = 'Failed to add flashcard.';
          this.successMessage = null;
        },
      });
    }
  }
}
