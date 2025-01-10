import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { FlashcardsListResponse } from '../../models/auth.model';
import { environment } from '../../../../../environments/environment.development';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
  apiUrl = `${environment.apiUrl}/flashcards`;
  flashcardsList: FlashcardsListResponse[] | undefined;
  searchQuery: string = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    const headers = new HttpHeaders({
      Authorization: '' + localStorage.getItem('JWT_TOKEN'),
      'Content-Type': 'application/json',
    });

    this.http
      .get<FlashcardsListResponse[]>(`${this.apiUrl}/list`, {
        headers,
      })
      .subscribe((data) => {
        this.flashcardsList = data;
      });
  }


  onSearch(searchQuery: string): void {
    console.log(`onSearch method in home.component invoked with searchQuery: ${searchQuery}`)
    if (searchQuery) {
      this.searchQuery = searchQuery;
      const headers = new HttpHeaders({
        Authorization: '' + localStorage.getItem('JWT_TOKEN'),
        'Content-Type': 'application/json',
      });
      const params = new HttpParams().set('name', this.searchQuery.trim());
      this.http.get<FlashcardsListResponse[]>(`${this.apiUrl}/list`, {
          headers,params
        })
        .subscribe((data) => {
          this.flashcardsList = data;
        });
    }
    else {
      const headers = new HttpHeaders({
        Authorization: '' + localStorage.getItem('JWT_TOKEN'),
        'Content-Type': 'application/json',
      });

      this.http
        .get<FlashcardsListResponse[]>(`${this.apiUrl}/list`, {
          headers,
        })
        .subscribe((data) => {
          this.flashcardsList = data;
        });
    }
  }
}
