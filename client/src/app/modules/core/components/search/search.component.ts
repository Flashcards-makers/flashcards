import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent {
  searchQuery: string = '';
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  flashcards: any[] = [];

  constructor(private http: HttpClient) {}

  onSearch(): void {
    if (this.searchQuery.trim()) {
      this.http
        .get<unknown[]>(`/api/list`, { params: { name: this.searchQuery } })
        .subscribe({
          next: (data) => {
            this.flashcards = data;
          },
          error: (err) => {
            console.error('Błąd podczas wyszukiwania:', err);
          },
        });
    }
  }
}
