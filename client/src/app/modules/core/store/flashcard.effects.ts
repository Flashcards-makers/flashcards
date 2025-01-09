import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as FlashcardActions from './flashcard.actions';
import { catchError, map, of, switchMap } from 'rxjs';
import { Router } from '@angular/router';
import { FlashcardService } from '../services/flashcard.service';

@Injectable()
export class FlashcardsEffects {
  constructor(
    private actions$: Actions,
    private flashcardsService: FlashcardService,
    private router: Router,
  ) {}

  login$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(FlashcardActions.addFlashcard),
      switchMap((action) => {
        return this.flashcardsService.addFlashcard(action.flashcard).pipe(
          map((user) => {
            this.router.navigate(['/home']);
            return FlashcardActions.addFlashcardSuccess();
          }),
          catchError(() =>
            of(FlashcardActions.addFlashcardFailure({ error: 'Wystapił błąd.' })),
          ),
        );
      }),
    );
  });
}
