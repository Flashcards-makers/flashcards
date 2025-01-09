import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AddFlashcardsData } from '../models/flashcard.model';
import { LoginResponse } from '../models/auth.model';
import { catchError, Observable, throwError } from 'rxjs';
import { FlashcardsEntity } from '../../../types/Flashcard';
import { PageDetailsData, ResolvePageData } from '../models/pages.model';


@Injectable({
  providedIn: 'root'
})
export class FlashcardService {
  apiUrl = `${environment.apiUrl}/flashcards`;

  constructor(private http: HttpClient) {}

  addFlashcard(body: AddFlashcardsData) {
    const jwtToken = localStorage.getItem('JWT_TOKEN');
    const headers = new HttpHeaders({
      'Authorization': `${jwtToken}`,
    });
    return this.http.post<void>(`${this.apiUrl}/`, body, {headers});
  }

  getFlashcardById(id: string): Observable<FlashcardsEntity> {
    return this.http
      .get<FlashcardsEntity>(`${this.apiUrl}/flashcard/${id}`)
      .pipe(
        catchError((error) => {
          console.error('Error fetching flashcard:', error);
          return throwError(() => new Error('Failed to fetch flashcard.'));
        }),
      );
  }

  getFlashcardDetails(id: string): Observable<PageDetailsData> {
    const token = localStorage.getItem('JWT_TOKEN');

    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `${token}`,
      });
      return this.http.get<PageDetailsData>(`${this.apiUrl}/pages/${id}`, { headers });
    } else {
      console.error('Brak tokenu JWT!');
      return new Observable();
    }
  }

  getNextPage(flashcard: number, page: number): Observable<PageDetailsData> {
    const token = localStorage.getItem('JWT_TOKEN');

    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `${token}`,
      });
      return this.http.get<PageDetailsData>(`${this.apiUrl}/pages/${flashcard}/${page}`, { headers });
    } else {
      console.error('Brak tokenu JWT!');
      return new Observable();
    }
  }

  saveResolvedPage(body: ResolvePageData) {
    const token = localStorage.getItem('JWT_TOKEN');

    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `${token}`
      });
      return this.http.post<void>(`${this.apiUrl}/resolve`, body, { headers });
    } else {
      console.error('Brak tokenu JWT!');
      return new Observable();
    }
  }

  searchFlashcard(body: ResolvePageData) {
    const token = localStorage.getItem('JWT_TOKEN');

    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `${token}`
      });
      return this.http.post<void>(`${this.apiUrl}/resolve`, body, { headers });
    } else {
      console.error('Brak tokenu JWT!');
      return new Observable();
    }
  }
}
