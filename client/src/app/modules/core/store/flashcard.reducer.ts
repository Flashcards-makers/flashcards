import { Action, createReducer, on } from '@ngrx/store';
import { addFlashcard, addFlashcardFailure, addFlashcardSuccess } from './flashcard.actions';
import { AuthState } from '../../auth/store/auth.reducer';

export interface FlashcardState {
  loading: boolean;
  error: any | null;
}

export const initialState: FlashcardState = {
  loading: false,
  error: null
};

const _flashcardReducer = createReducer(
  initialState,
  on(addFlashcard, (state) => ({
    loading: true,
    error: null
  })),
  on(addFlashcardSuccess, (state) => ({
    ...state,
    loading: false
  })),
  on(addFlashcardFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error
  })),
  on(addFlashcardFailure, (state, action) => ({
    ...state,
    error: null
  }))
);

export function flashcardReducer(state: FlashcardState | undefined, action: Action) {
  return _flashcardReducer(state, action);
}
