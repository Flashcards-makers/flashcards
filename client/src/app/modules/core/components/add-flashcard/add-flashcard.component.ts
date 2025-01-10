import { Component } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import * as FlashcardActions from '../../store/flashcard.actions';
import { FormService } from '../../services/form.service';

import { AddFlashcardsForm } from '../../models/flashcard.form.model';
import { Store } from '@ngrx/store';
import { AppState } from '../../../../store/app.reducer';
import { Observable } from 'rxjs';
import { selectFlashcardError, selectFlashcardLoading } from '../../store/flashcard.selectors';
import { AddFlashcardsData } from '../../models/flashcard.model';

@Component({
  selector: 'app-add-flashcard',
  templateUrl: './add-flashcard.component.html',
  styleUrls: ['./add-flashcard.component.scss']
})
export class AddFlashcardComponent {
  flashcardForm: FormGroup<AddFlashcardsForm> = this.formService.initAddFlashcardForm();
  errorMessage$: Observable<string | null> = this.store.select(selectFlashcardError);
  loading$: Observable<boolean> = this.store.select(selectFlashcardLoading);

  constructor(
    private formService: FormService,
    private store: Store<AppState>,
    private fb: FormBuilder
  ) {
  }

  get controls() {
    return this.flashcardForm.controls;
  }

  getErrorMessage(control: FormControl) {
    return this.formService.getErrorMessage(control);
  }

  onSubmit(): void {
    if (this.flashcardForm.invalid) {
      return;
    }
    const flashcardData: AddFlashcardsData = {
      name: this.flashcardForm.get('name')?.value || '',
      description: this.flashcardForm.get('description')?.value || '',
      icon: this.flashcardForm.get('icon')?.value || '',
      isPublic: this.flashcardForm.get('isPublic')?.value || true,
      pages: (this.flashcardForm.get('pages') as FormArray).controls.map((pageGroup: AbstractControl) => {
        return {
          question: pageGroup.get('question')?.value || '',
          questionImage: pageGroup.get('questionImage')?.value || '',
          answer: pageGroup.get('answer')?.value || '',
          answerImage: pageGroup.get('answerImage')?.value || ''
        };
      })
    };

    this.store.dispatch(
      FlashcardActions.addFlashcard({ flashcard: flashcardData })
    );
  }

  ngOnDestroy(): void {
    this.store.dispatch(FlashcardActions.clearError());
  }

  onMainFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file = input.files ? input.files[0] : null;
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const base64Image = reader.result as string;
        (this.flashcardForm.get('icon') as FormControl)
          .setValue(base64Image);
      };
      reader.readAsDataURL(file);
    }
  }

  onFileChange(event: Event, index: number, field: string): void {
    const input = event.target as HTMLInputElement;
    const file = input.files ? input.files[0] : null;
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const base64Image = reader.result as string;
        (this.flashcardForm.get('pages') as FormArray)
          .at(index)
          .get(field)?.setValue(base64Image);
      };
      reader.readAsDataURL(file);
    }
  }

  get pages(): FormArray {
    return this.flashcardForm.get('pages') as FormArray;
  }

  addPage(): void {
    this.pages.push(this.createPage());
  }

  removePage(index: number): void {
    this.pages.removeAt(index);
  }

  createPage(): FormGroup {
    return this.fb.group({
      question: ['', Validators.required],
      questionImage: ['', Validators.required],
      answer: ['', Validators.required],
      answerImage: ['', Validators.required]
    });
  }

  protected readonly FormControl = FormControl;
}
