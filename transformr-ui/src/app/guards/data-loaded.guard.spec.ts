import { TestBed } from '@angular/core/testing';

import { DataLoadedGuard } from './data-loaded.guard';

describe('DataLoadedGuard', () => {
  let guard: DataLoadedGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(DataLoadedGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
