import { TestBed } from '@angular/core/testing';

import { DataStore } from './data.store';

describe('DataService', () => {
  let service: DataStore;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataStore);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
