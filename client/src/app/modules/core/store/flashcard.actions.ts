import { createAction, props } from '@ngrx/store';
import { AddFlashcardsData } from '../models/flashcard.model';

const ADD_FLASHCARD_TYPE = '[Flashcard] Add flashcard';
const ADD_FLASHCARD_SUCCESS_TYPE = '[Auth] Add flashcard success';
const ADD_FLASHCARD_FAILURE_TYPE = '[Auth] Add flashcard failure';

const CLEAR_ERROR_TYPE = '[Auth] Clear Error';

export const addFlashcard = createAction(
  ADD_FLASHCARD_TYPE,
  props<{ flashcard: AddFlashcardsData }>()
);

export const addFlashcardSuccess = createAction(
  ADD_FLASHCARD_SUCCESS_TYPE,
);

export const addFlashcardFailure = createAction(
  ADD_FLASHCARD_FAILURE_TYPE,
  props<{ error: string }>()
);
export const clearError = createAction(CLEAR_ERROR_TYPE);
