import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { FlashcardsEntity } from '../../../types/Flashcard';

@Injectable({
  providedIn: 'root',
})
export class FlashcardService {
  private baseUrl = 'http://localhost:8082';

  constructor(private http: HttpClient) {}

  addFlashcard(flashcard: {
    name: string;
    description: string;
  }): Observable<unknown> {
    return this.http.post(`${this.baseUrl}/flashcards`, flashcard);
  }

  getFlashcardById(id: string): Observable<FlashcardsEntity> {
    return this.http
      .get<FlashcardsEntity>(`${this.baseUrl}/flashcard/${id}`)
      .pipe(
        catchError((error) => {
          console.error('Error fetching flashcard:', error);
          return throwError(() => new Error('Failed to fetch flashcard.'));
        }),
      );
  }
}
