import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent {
  searchQuery: string = '';
  @Output() search = new EventEmitter<string>();

  constructor(private http: HttpClient) {}

  onSearch(): void {
    console.log(`Search query: ${this.searchQuery}`)
    this.search.emit(this.searchQuery.trim());
  }
}
