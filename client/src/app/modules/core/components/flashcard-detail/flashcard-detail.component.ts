import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FlashcardService } from '../../../auth/services/flashcard.service';

@Component({
  selector: 'app-flashcard-detail',
  templateUrl: './flashcard-detail.component.html',
  styleUrls: ['./flashcard-detail.component.scss'],
})
export class FlashcardDetailComponent implements OnInit {
  flashcardId: string | null = null;
  flashcardName: string | null = null;
  flashcardDescription: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private flashcardService: FlashcardService,
  ) {}

  ngOnInit(): void {
    this.flashcardId = this.route.snapshot.paramMap.get('id');

    if (this.flashcardId) {
      this.flashcardService.getFlashcardById(this.flashcardId).subscribe({
        next: (flashcard) => {
          this.flashcardName = flashcard.name;
          this.flashcardDescription = flashcard.description;
        },
        error: (err) => console.error('Error fetching flashcard:', err),
      });
    }
  }
}
