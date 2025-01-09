import { NgModule } from '@angular/core';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { HomeComponent } from './home/home.component';
import { SharedModule } from '../../shared/shared.module';
import { CoreModule } from '../core.module';
import { HeaderComponent } from './header/header.component';
import { SearchComponent } from './search/search.component';
import { FormsModule } from '@angular/forms';
import { FlashcardDetailComponent } from './flashcard-detail/flashcard-detail.component';
import { AddFlashcardComponent } from './add-flashcard/add-flashcard.component';
import { ResolvePageComponent } from './resolve.page/resolve.page.component';

@NgModule({
  declarations: [
    HomeComponent,
    HeaderComponent,
    SearchComponent,
    FlashcardDetailComponent,
    ResolvePageComponent,
    AddFlashcardComponent
  ],
  imports: [SharedModule, DashboardRoutingModule, CoreModule, FormsModule]
})
export class DashboardModule {}
