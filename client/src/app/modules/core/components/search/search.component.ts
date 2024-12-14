import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FlashcardsEntity } from '../../../../types/Flashcard';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent {
  searchQuery: string = '';
  flashcards: FlashcardsEntity[] = [];

  constructor(private http: HttpClient) {}

  onSearch(): void {
    if (this.searchQuery.trim()) {
      this.http
        .get<FlashcardsEntity[]>(`/api/list`, {
          params: { name: this.searchQuery },
        })
        .subscribe({
          next: (data) => {
            this.flashcards = data;
          },
          error: (err) => {
            console.error('Error while searching:', err);
          },
        });
    }
  }
}
