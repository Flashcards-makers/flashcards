import { AppState } from '../../../store/app.reducer';
import { createSelector } from '@ngrx/store';
import { FlashcardState } from './flashcard.reducer';


export const selectFlashcard = (state: AppState) => state.flashcard;
export const selectFlashcardError = createSelector(
  selectFlashcard,
  (state: FlashcardState) => state.error,
);
export const selectFlashcardLoading = createSelector(
  selectFlashcard,
  (state: FlashcardState) => state.loading,
);
