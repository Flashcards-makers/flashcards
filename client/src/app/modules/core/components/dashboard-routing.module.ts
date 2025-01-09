import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AddFlashcardComponent } from './add-flashcard/add-flashcard.component';
import { FlashcardDetailComponent } from './flashcard-detail/flashcard-detail.component';
import { ResolvePageComponent } from './resolve.page/resolve.page.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'add-flashcard', component: AddFlashcardComponent },
  {
    path: 'page/:id',
    component: ResolvePageComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
