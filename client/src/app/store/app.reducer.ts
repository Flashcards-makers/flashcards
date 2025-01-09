import { AuthState } from '../modules/auth/store/auth.reducer';
import { FlashcardState } from '../modules/core/store/flashcard.reducer';

export interface AppState {
  auth: AuthState;
  flashcard: FlashcardState;
}
