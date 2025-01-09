import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StoreModule } from '@ngrx/store';
import { CoreModule } from './modules/core/core.module';
import { EffectsModule } from '@ngrx/effects';
import { AuthModule } from './modules/auth/auth.module';
import { authReducer } from './modules/auth/store/auth.reducer';
import { flashcardReducer } from './modules/core/store/flashcard.reducer';
import { AuthEffects } from './modules/auth/store/auth.effects';
import { DashboardModule } from './modules/core/components/dashboard.module';
import { FlashcardsEffects } from './modules/core/store/flashcard.effects';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    AuthModule,
    DashboardModule,
    BrowserAnimationsModule,
    StoreModule.forRoot({ auth: authReducer, flashcard: flashcardReducer }),
    EffectsModule.forRoot([AuthEffects, FlashcardsEffects]),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
