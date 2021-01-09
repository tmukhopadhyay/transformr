import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { Observable } from 'rxjs';
import { DataStore } from '../store';

@Injectable({
  providedIn: 'root',
})
export class DataLoadedGuard implements CanActivate {
  constructor(private dataStore: DataStore) {}

  canActivate(): boolean | Observable<boolean> {
    if (this.dataStore.isDataLoaded()) {
      return true;
    } else {
      return this.dataStore.loadData();
    }
  }
}
